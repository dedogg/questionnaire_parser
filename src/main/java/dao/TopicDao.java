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
    @Override
    public Optional<Topic> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Topic> getAll() {
        TypedQuery<Topic> query = EntityManagerFactoryUtil.getEntityManager().createQuery("SELECT a FROM Topic a", Topic.class);
        return query.getResultList();
    }

    @Override
    public int save(Topic topic) {
        EntityManager entityManager = EntityManagerFactoryUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(topic);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
        return topic.getId();
    }

    @Override
    public void update(Topic topic) {

    }

    @Override
    public void delete(Topic topic) {

    }
}