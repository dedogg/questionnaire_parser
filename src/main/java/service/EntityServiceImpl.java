package service;

import java.util.List;

public interface EntityServiceImpl<T> {

    int create(String name);

    void delete(T t);

    void update(T t);

    List<T> getAll();

}
