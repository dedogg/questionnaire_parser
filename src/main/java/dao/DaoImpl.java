package dao;

import models.Person;

import java.util.List;
import java.util.Optional;

public interface DaoImpl<T> {

    Optional<T> get(int id);

    List<T> getAll();

    Object save(T t);

    void update(T t);

    void delete(T t);
}
