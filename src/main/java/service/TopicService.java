package service;

import dao.TopicDao;
import models.Topic;

import java.util.List;

public class TopicService {
    private final TopicDao topicDao = new TopicDao();

    public TopicService() {}

    public void saveTopic(Topic topic) {
        topicDao.save(topic);
    }

    public void deleteTopic(Topic topic) {
        topicDao.delete(topic);
    }

    public List<Topic> getAll() {
        return topicDao.getAll();
    }

    public void createTopic(String name)
    {
        Topic topic = new Topic();
        topic.setName(name);
        saveTopic(topic);
    }
}
