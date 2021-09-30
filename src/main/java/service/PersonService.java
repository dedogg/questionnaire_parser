package service;

import dao.PersonDao;
import models.Person;

import java.util.List;
import java.util.Optional;

public class PersonService implements EntityServiceImpl<Person>{
    private final PersonDao personDao = new PersonDao();

    @Override
    public int create(String name) {
        Person person = new Person();
        person.setName(name);

        return personDao.save(person);
    }

    @Override
    public void delete(Person person) {

    }

    @Override
    public void update(Person person) {
        personDao.update(person);
    }

    @Override
    public List<Person> getAll() {
        return personDao.getAll();
    }

    public Optional<Person> findByName(String file) {
        return personDao.findByName(file);
    }
}
