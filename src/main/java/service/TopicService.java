package service;

import dao.TopicDao;
import models.Topic;

import java.util.List;

public class TopicService implements EntityServiceImpl<Topic>{
    private final TopicDao topicDao = new TopicDao();

    public int create(String name)
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

    }

    public List<Topic> getAll() {
        return topicDao.getAll();
    }

}
