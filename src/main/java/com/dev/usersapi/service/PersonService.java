package com.dev.usersapi.service;

import com.dev.usersapi.entity.Person;
import com.dev.usersapi.repository.PersonRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;
    
    public List<Person> findAll(){
        return repository.findAll();
    }
    
    public Optional<Person> findById(Long id){
        return repository.findById(id);
    }

    public void create(Person entity) {
        entity.setId(null);
        repository.save(entity);
    }

    public void update(Person entity) {
        repository.save(entity);
    }

    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
