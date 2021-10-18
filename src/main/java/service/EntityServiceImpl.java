package service;

import models.Topic;

import java.util.List;

public interface EntityServiceImpl<T> {

    Object create(String name);

    void delete(T t);

    void update(T t);

    List<T> getAll();

}
