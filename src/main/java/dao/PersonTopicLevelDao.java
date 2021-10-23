package dao;

import models.Person;
import models.PersonTopicLevel;
import models.Topic;
import utils.EntityManagerFactoryUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class PersonTopicLevelDao {

    public PersonTopicLevel save(PersonTopicLevel personTopicLevel) {
        EntityManager entityManager = EntityManagerFactoryUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(personTopicLevel);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
        return personTopicLevel;
    }

    public void update(PersonTopicLevel personTopicLevel) {
        EntityManager entityManager = EntityManagerFactoryUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(personTopicLevel);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Optional<PersonTopicLevel> getByComposedKey(Person person, Topic topic) {
        TypedQuery<PersonTopicLevel> query = EntityManagerFactoryUtil
                .getEntityManager()
                .createNamedQuery("PersonTopicLevel.getByComposedKey", PersonTopicLevel.class)
                .setParameter("person", person)
                .setParameter("topic", topic);
        return query.getResultList().stream().findAny();
    }
}
