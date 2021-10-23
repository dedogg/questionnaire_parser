package service;

import dao.PersonTopicLevelDao;
import models.Person;
import models.PersonTopicLevel;
import models.Topic;

import java.util.Date;
import java.util.Optional;

public class PersonTopicLevelService {
    PersonTopicLevelDao personTopicLevelDao = new PersonTopicLevelDao();
    public Optional<PersonTopicLevel> getByComposedKey(Person person, Topic topic) {
        return personTopicLevelDao.getByComposedKey(person, topic);
    }

    public PersonTopicLevel create(Person person, Topic topic, Integer level, String comment, Date date) {
        PersonTopicLevel personTopicLevel = new PersonTopicLevel();
        personTopicLevel.setPerson(person);
        personTopicLevel.setTopic(topic);
        personTopicLevel.setLevel(level);
        personTopicLevel.setComment(comment);
        personTopicLevel.setDate(date);
        return personTopicLevelDao.save(personTopicLevel);
    }

    public void update(PersonTopicLevel personTopicLevel) {
        personTopicLevelDao.update(personTopicLevel);
    }
}
