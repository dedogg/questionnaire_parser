package dao;

import models.Person;
import utils.EntityManagerFactoryUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class PersonDao implements DaoImpl<Person> {

    @Override
    public Optional<Person> get(int id) {
        TypedQuery <Person> query =
                EntityManagerFactoryUtil
                        .getEntityManager()
                        .createNamedQuery("Person.byId", Person.class)
                        .setParameter("id", id);
        return query.getResultList().stream().findFirst();
    }

    @Override
    public List<Person> getAll() {
        TypedQuery<Person> query = EntityManagerFactoryUtil.getEntityManager().createQuery("SELECT a FROM Person a", Person.class);
        return query.getResultList();
    }

    @Override
    public Person save(Person person) {
        EntityManager entityManager = EntityManagerFactoryUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(person);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
        return person;
    }

    @Override
    public void update(Person person) {
        EntityManager entityManager = EntityManagerFactoryUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(person);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void delete(Person person) {

    }

    public Optional<Person> findByName(String name) {
        TypedQuery<Person> query =
                EntityManagerFactoryUtil
                        .getEntityManager()
                        .createNamedQuery("Person.findByName", Person.class)
                        .setParameter("name", name);
        return query.getResultList().stream().findFirst();
    }
}
