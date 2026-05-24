package r.real.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


import r.real.model.valueObjects.*;
import r.real.model.base.*;


import java.math.BigDecimal;

import static org.apache.commons.lang3.Validate.notNull;

@Entity
@Table(name = "drivers")
public class Driver extends AbstractEntity<DriverId> {

    @Embedded
    private DriverName name;

    @Embedded
    private TeamName teamName;

    @Embedded
    private Nationality nationality;

    @Embedded
    private RacingNumber racingNumber;

    @Embedded
    private Money salary;

    @Embedded
    @AttributeOverride(name = "value", column = @jakarta.persistence.Column(name = "championship_points"))
    private ChampionshipPoints championshipPoints;

    public Driver(DriverName name,
                  TeamName teamName,
                  Nationality nationality,
                  RacingNumber racingNumber,
                  Money salary,
                  ChampionshipPoints championshipPoints) {
        super(new DriverId());
        this.name = notNull(name, "name must not be null");
        this.teamName = notNull(teamName, "team name must not be null");
        this.nationality = notNull(nationality, "nationality must not be null");
        this.racingNumber = notNull(racingNumber, "racing number must not be null");
        this.salary = notNull(salary, "salary must not be null");
        this.championshipPoints = notNull(championshipPoints, "championship points must not be null");
    }

    protected Driver() {
        super();
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

    public Money getSalary() {
        return salary;
    }

    public ChampionshipPoints getChampionshipPoints() {
        return championshipPoints;
    }

    public void rename(DriverName newName) {
        this.name = notNull(newName, "name must not be null");
    }

    public void changeTeam(TeamName newTeamName) {
        this.teamName = notNull(newTeamName, "team name must not be null");
    }

    public void changeNationality(Nationality newNationality) {
        this.nationality = notNull(newNationality, "nationality must not be null");
    }

    public void changeRacingNumber(RacingNumber newNumber) {
        this.racingNumber = notNull(newNumber, "racing number must not be null");
    }

    public void changeSalary(Money newSalary) {
        this.salary = notNull(newSalary, "salary must not be null");
    }

    public void addPoints(ChampionshipPoints points) {
        this.championshipPoints = this.championshipPoints.add(points);
    }

    public void updateProfile(DriverName name,
                              TeamName teamName,
                              Nationality nationality,
                              RacingNumber racingNumber,
                              Money salary,
                              ChampionshipPoints championshipPoints) {
        this.name = notNull(name, "name must not be null");
        this.teamName = notNull(teamName, "team name must not be null");
        this.nationality = notNull(nationality, "nationality must not be null");
        this.racingNumber = notNull(racingNumber, "racing number must not be null");
        this.salary = notNull(salary, "salary must not be null");
        this.championshipPoints = notNull(championshipPoints, "championship points must not be null");
    }
}