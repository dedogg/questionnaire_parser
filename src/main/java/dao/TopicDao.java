package dao;

import models.Topic;
import utils.EntityManagerFactoryUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

/* Изначальный вариант через HibernateSessionFactory. ПРоблема с возвращением типа RAW при попытке минимизировать вызовы кода */
//public class TopicDao implements DaoImpl<Topic> {
//    private final HibernateSessionFactoryUtil hibernateSessionFactoryUtil = new HibernateSessionFactoryUtil();
//    @Override
//    public Optional<Topic> get(long id) {
//        return Optional.empty();
//    }
//
//    @Override
//    public List<Topic> getAll() {
//        return (List<Topic>) hibernateSessionFactoryUtil.query("SELECT a FROM Topic a");
//    }
//
//    @Override
//    public void save(Topic topic) {
//        hibernateSessionFactoryUtil.save(topic);
//    }
//
//    @Override
//    public void update(Topic topic, String[] params) {
//        hibernateSessionFactoryUtil.update(topic);
//    }
//
//    @Override
//    public void delete(Topic topic) {
//        hibernateSessionFactoryUtil.delete(topic);
//    }
//}
public class TopicDao implements DaoImpl<Topic> {
    public Topic getTest(int id) {
        TypedQuery<Topic> query =
                EntityManagerFactoryUtil
                        .getEntityManager()
                        .createNamedQuery("Topic.byId", Topic.class)
                        .setParameter("id", id);
        return query.getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public Optional<Topic> get(int id) {
        TypedQuery<Topic> query =
                EntityManagerFactoryUtil
                        .getEntityManager()
                        .createNamedQuery("Topic.byId", Topic.class)
                        .setParameter("id", id);
        return query.getResultList().stream().findFirst();
    }

    @Override
    public List<Topic> getAll() {
        TypedQuery<Topic> query = EntityManagerFactoryUtil.getEntityManager().createQuery("SELECT a FROM Topic a", Topic.class);
        return query.getResultList();
    }

    @Override
    public Topic save(Topic topic) {
        EntityManager entityManager = EntityManagerFactoryUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(topic);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
        return topic;
    }

    @Override
    public void update(Topic topic) {
        EntityManager entityManager = EntityManagerFactoryUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(topic);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void delete(Topic topic) {

    }

    public Optional<Topic> findByName(String name) {
        TypedQuery<Topic> query =
                EntityManagerFactoryUtil
                        .getEntityManager()
                        .createNamedQuery("Topic.findByName", Topic.class)
                        .setParameter("name", name);
        return query.getResultList().stream().findFirst();
    }
}