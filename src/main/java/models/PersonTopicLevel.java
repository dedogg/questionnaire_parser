package models;

import javax.persistence.*;
import java.util.Date;

@Table(name = "person_topic_level")
@Entity
@NamedQueries(
        {
                @NamedQuery(
                        name = "PersonTopicLevel.getByComposedKey",
                        query = "from PersonTopicLevel ptl where ptl.person = :person AND ptl.topic = :topic"
                )
        }
)
public class PersonTopicLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "level")
    private Integer level;

    @Column(name = "comment")
    private String comment;

    @Column(name = "date")
    private Date date;

    @ManyToOne(optional = false)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @ManyToOne(optional = false)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}