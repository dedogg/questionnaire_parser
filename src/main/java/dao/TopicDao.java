package dao;

import models.Topic;
import org.hibernate.Session;
import utils.HibernateSessionFactoryUtil;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TopicDao implements DaoImpl<Topic> {
    private final HibernateSessionFactoryUtil hibernateSessionFactoryUtil = new HibernateSessionFactoryUtil();
    @Override
    public Optional<Topic> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Topic> getAll() {
//        List<Topic> topics = null;
//        topics = hibernateSessionFactoryUtil.query("SELECT a FROM Topic a");
        return (List<Topic>) hibernateSessionFactoryUtil.query("SELECT a FROM Topic a");

//        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
//        List<Topic> result = session.createQuery("SELECT a FROM Topic a", Topic.class).getResultList();
//        session.close();
//        return result;

//        return HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("SELECT a FROM Topic a", Topic.class).getResultList();
    }

    @Override
    public void save(Topic topic) {
        hibernateSessionFactoryUtil.save(topic);
    }

    @Override
    public void update(Topic topic, String[] params) {
        hibernateSessionFactoryUtil.update(topic);
    }

    @Override
    public void delete(Topic topic) {
        hibernateSessionFactoryUtil.delete(topic);
    }
}
