package utils;

import models.Topic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Topic.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                System.out.println("Пиздос" + e);
            }
        }
        return sessionFactory;
    }

    public void save(Object object) {
        this.perform(object, "save");
    }

    public void update(Object object) {
        this.perform(object, "update");
    }

    public void delete(Object object) {
        this.perform(object, "delete");
    }

    public List<?> query(String query) {
        Session session = getSessionFactory().openSession();
        List<?> result = session.createQuery(query).getResultList();
        session.close();
        return result;
    }
    

    private void perform(Object object, String type) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        switch (type) {
            case "save":
                session.save(object);
                break;
            case "update":
                session.update(object);
                break;
            case "delete":
                session.delete(object);
                break;
        }
        transaction.commit();
        session.close();
    }
}
