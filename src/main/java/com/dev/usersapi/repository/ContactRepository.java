package com.dev.usersapi.repository;

import com.dev.usersapi.entity.Contact;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{
    
    List<Contact> findByPersonId(Long id);
    
}
