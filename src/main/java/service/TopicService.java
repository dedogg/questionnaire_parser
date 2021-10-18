package service;

import dao.TopicDao;
import models.Topic;

import java.util.List;
import java.util.Optional;

public class TopicService implements EntityServiceImpl<Topic>{
    private final TopicDao topicDao = new TopicDao();

    public Topic getTest(int id) {
        return topicDao.getTest(id);
    }

    public Optional<Topic> get(int id) {
        return topicDao.get(id);
    }

    public Topic create(String name)
    {
        Topic topic = new Topic();
        topic.setName(name);
        return topicDao.save(topic);
    }

    public void delete(Topic topic) {
        topicDao.delete(topic);
    }

    @Override
    public void update(Topic topic) {
        topicDao.update(topic);
    }

    public List<Topic> getAll() {
        return topicDao.getAll();
    }

    public Optional<Topic> findByName(String name) { return topicDao.findByName(name); }

}
