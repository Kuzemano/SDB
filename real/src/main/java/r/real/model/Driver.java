package r.real.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "drivers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String teamName;
    private String nationality;
    private Integer racingNumber;
    private Double salary;
    private String salaryCurrency;
    private Integer championshipPoints;


    public Driver(String name, String teamName, String nationality, Integer racingNumber, Double salary, String salaryCurrency, Integer championshipPoints) {
        this.name = name;
        this.teamName = teamName;
        this.nationality = nationality;
        this.racingNumber = racingNumber;
        this.salary = salary;
        this.salaryCurrency = salaryCurrency;
        this.championshipPoints = championshipPoints;


    }
}