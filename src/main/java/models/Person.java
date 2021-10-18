package models;

import javax.persistence.*;

@Entity
@Table(name = "person")
@NamedQueries(
        {
                @NamedQuery(
                        name = "Person.byId",
                        query = "from Person e where e.id = :id"
                ),
                @NamedQuery(
                        name = "Person.findByName",
                        query = "from Person e where e.name = :name"
                )
        }
)
public class Person {

    @GeneratedValue
    @Id
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "points")
    private int points;

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Column(name = "salary")
    private Double salary;

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Column(name = "reading")
    private int reading;

    public Integer getReading() {
        return reading;
    }

    public void setReading(Integer reading) {
        this.reading = reading;
    }

    @Column(name = "level")
    private String level;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
