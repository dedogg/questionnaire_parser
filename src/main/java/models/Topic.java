package models;

import javax.persistence.*;

@Entity
@Table(name = "topic")
@NamedQueries(
        {
                @NamedQuery(
                        name = "Topic.byId",
                        query = "from Topic e where e.id = :id"
                ),
                @NamedQuery(
                        name = "Topic.findByName",
                        query = "from Topic e where e.name = :name"
                )
        }
)
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "weight")
    private int weight;

    public Topic() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setWeight(int id) {
        this.id = id;
    }

    public int getWeight() {
        return weight;
    }

}
