package com.dev.usersapi.service;

import com.dev.usersapi.entity.Contact;
import com.dev.usersapi.repository.ContactRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired
    private ContactRepository repository;

    public List<Contact> findByPersonId(Long personId) {
        return repository.findByPersonId(personId);
    }

    public void create(Contact entity) {
        entity.setId(null);
        repository.save(entity);
    }

    public Optional<Contact> findById(Long id) {
        return repository.findById(id);
    }

    public void update(Contact entity) {
        repository.save(entity);
    }

    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
