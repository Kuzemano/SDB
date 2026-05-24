package r.real.drivers.domain;

import jakarta.persistence.*;
import r.real.common.domain.base.AbstractEntity;
import r.real.common.domain.valueObjects.Money;
import r.real.drivers.domain.ids.DriverId;
import r.real.drivers.domain.valueObjects.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.apache.commons.lang3.Validate.notNull;
import static org.apache.commons.lang3.Validate.validState;

@Entity
@Table(name = "drivers")
public class Driver extends AbstractEntity<DriverId> {

    @Embedded
    private DriverName name;

    @Embedded
    private DriverState state;

    @Embedded
    private TeamName teamName;

    @Embedded
    private Nationality nationality;

    @Embedded
    private RacingNumber racingNumber;

    @Embedded
    private Money salary;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "championship_points"))
    private ChampionshipPoints championshipPoints;

    @Column(name = "contract_start_date")
    private ZonedDateTime contractStartDate;

    @Column(name = "contract_end_date")
    private ZonedDateTime contractEndDate;

    @ElementCollection
    @CollectionTable(name = "driver_tags", joinColumns = @JoinColumn(name = "driver_id"))
    private List<DriverTag> tags;

    public Driver(DriverName name,
                  TeamName teamName,
                  Nationality nationality,
                  RacingNumber racingNumber,
                  Money salary,
                  ChampionshipPoints championshipPoints) {
        super(new DriverId());
        this.name = notNull(name, "name must not be null");
        this.teamName = notNull(teamName, "teamName must not be null");
        this.nationality = notNull(nationality, "nationality must not be null");
        this.racingNumber = notNull(racingNumber, "racingNumber must not be null");
        this.salary = notNull(salary, "salary must not be null");
        this.championshipPoints = notNull(championshipPoints, "championshipPoints must not be null");
        this.tags = new ArrayList<>();
        this.state = DriverState.signed();
        this.state.activate();
    }

    protected Driver() {
        super();
        this.tags = new ArrayList<>();
    }

    public DriverName getName() {
        return name;
    }

    public TeamName getTeamName() {
        return teamName;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public RacingNumber getRacingNumber() {
        return racingNumber;
    }

    public DriverStatus getStatus() { return state.getStatus(); }

    public Money getSalary() {
        return salary;
    }

    public ChampionshipPoints getChampionshipPoints() {
        return championshipPoints;
    }

    public ZonedDateTime getContractStartDate() {
        return contractStartDate;
    }

    public ZonedDateTime getContractEndDate() {
        return contractEndDate;
    }

    public List<DriverTag> getTags() {
        return tags;
    }

    public List<DriverTag> tags() {
        return Collections.unmodifiableList(tags);
    }

    public void updateDriverName(DriverName name) {
        state.ensureCanUpdateContractData();
        this.name = notNull(name, "name must not be null");
        checkInvariants();
    }

    public void updateTeamName(TeamName teamName) {
        state.ensureCanUpdateContractData();
        this.teamName = notNull(teamName, "teamName must not be null");
        checkInvariants();
    }

    public void updateNationality(Nationality nationality) {
        state.ensureCanUpdateContractData();
        this.nationality = notNull(nationality, "nationality must not be null");
        checkInvariants();
    }

    public void updateRacingNumber(RacingNumber racingNumber) {
        state.ensureCanUpdateContractData();
        this.racingNumber = notNull(racingNumber, "racingNumber must not be null");
        checkInvariants();
    }

    public void updateSalary(Money salary) {
        state.ensureCanUpdateContractData();
        this.salary = notNull(salary, "salary must not be null");
        checkInvariants();
    }

    public void updateChampionshipPoints(ChampionshipPoints championshipPoints) {
        validState(state.getStatus() == DriverStatus.ACTIVE,
                "Championship points can only be updated for ACTIVE drivers");
        this.championshipPoints = notNull(championshipPoints, "championshipPoints must not be null");
        checkInvariants();
    }

    public void addPoints(ChampionshipPoints points) {
        validState(state.getStatus() == DriverStatus.ACTIVE,
                "Points can only be added to ACTIVE drivers");
        this.championshipPoints = this.championshipPoints.add(points);
        checkInvariants();
    }









    public void activate() {
        state.activate();
        checkInvariants();
    }

    public void suspend() {
        state.suspend();
        checkInvariants();
    }

    public void retire() {
        state.retire();
        checkInvariants();
    }







    public void updateContractStartDate(ZonedDateTime contractStartDate) {
        state.ensureCanUpdateContractData();
        notNull(contractStartDate, "contractStartDate must not be null");
        validState(contractStartDate.isBefore(ZonedDateTime.now().plusYears(10)),
                "contractStartDate must be realistic");
        this.contractStartDate = contractStartDate;
        checkInvariants();
    }


    public void clearContractStartDate() {
        this.contractStartDate = null;
        this.contractEndDate = null;
        checkInvariants();
    }

    public void updateContractEndDate(ZonedDateTime contractEndDate) {
        state.ensureCanUpdateContractData();
        notNull(contractEndDate, "contractEndDate must not be null");
        this.contractEndDate = contractEndDate;
        checkInvariants();
    }

    public void clearContractEndDate() {
        this.contractEndDate = null;
        checkInvariants();
    }

    public void addTag(DriverTag tag) {
        this.tags.add(notNull(tag, "tag must not be null"));
        checkInvariants();
    }

    public void removeTag(DriverTag tag) {
        this.tags.remove(tag);
        checkInvariants();
    }

    private void checkInvariants() {
        validState(
                (contractStartDate == null && contractEndDate == null) ||
                        (contractStartDate != null && contractEndDate == null) ||
                        (contractStartDate != null && contractEndDate != null && contractStartDate.isBefore(contractEndDate)),
                "contractStartDate must be before contractEndDate"
        );
    }
    public static Driver fromSignedCandidate(DriverCandidate candidate,
                                             TeamName teamName,
                                             RacingNumber racingNumber,
                                             Money salary,
                                             ZonedDateTime contractStartDate,
                                             ZonedDateTime contractEndDate) {
        Driver driver = new Driver(
                candidate.getName(),
                teamName,
                candidate.getNationality(),
                racingNumber,
                salary,
                new ChampionshipPoints(0)
        );
        driver.contractStartDate = contractStartDate;
        driver.contractEndDate = contractEndDate;
        driver.checkInvariants();
        return driver;
    }

    public static class Builder {
        private Driver driver;

        public Builder(DriverName name,
                       TeamName teamName,
                       Nationality nationality,
                       RacingNumber racingNumber,
                       Money salary,
                       ChampionshipPoints championshipPoints) {
            this.driver = new Driver(name, teamName, nationality, racingNumber, salary, championshipPoints);
        }

        public Builder withContractStartDate(ZonedDateTime contractStartDate) {
            if (contractStartDate != null) {
                this.driver.contractStartDate = contractStartDate;
            }
            return this;
        }

        public Builder withContractEndDate(ZonedDateTime contractEndDate) {
            if (contractEndDate != null) {
                this.driver.contractEndDate = contractEndDate;
            }
            return this;
        }

        public Builder withTag(DriverTag tag) {
            if (tag != null) {
                this.driver.tags.add(tag);
            }
            return this;
        }

        public Driver build() {
            validState(driver != null, "driver must not be null");
            driver.checkInvariants();
            Driver result = driver;
            driver = null;
            return result;
        }
    }
}