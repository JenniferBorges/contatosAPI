package com.dev.usersapi.api.v1;

import com.dev.usersapi.dto.ContactDto;
import com.dev.usersapi.entity.Contact;
import com.dev.usersapi.entity.Person;
import com.dev.usersapi.exception.ResourceNotFoundException;
import com.dev.usersapi.parser.ContactParser;
import com.dev.usersapi.service.ContactService;
import com.dev.usersapi.service.PersonService;
import com.dev.usersapi.validator.ContactValidator;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/v1/persons/{personId}/contacts")
public class ContactController {

    @Autowired
    private ContactService service;

    @Autowired
    private PersonService personService;

    @Autowired
    private ContactValidator validator;

    @GetMapping
    public @ResponseBody
    List<ContactDto> getContacts(@PathVariable Long personId) {
        validatePerson(personId);
        return this.service.findByPersonId(personId).stream()
                .map(ContactParser::entityToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<ContactDto> create(@PathVariable Long personId, @RequestBody @Valid ContactDto contact, BindingResult result) throws MethodArgumentNotValidException {
        validatePerson(personId);

        validator.validate(contact, result);
        if (result.hasErrors()) {
            throw new MethodArgumentNotValidException(null, result);
        }
        
        Contact entity = ContactParser.dtoToEntity(contact);
        entity.setPerson(new Person(personId));
        service.create(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(ContactParser.entityToDto(entity));
    }

    @GetMapping("/{id}")
    public @ResponseBody
    ContactDto getById(@PathVariable Long personId, @PathVariable Long id) {
        validatePerson(personId);
        return service.findById(id).map(ContactParser::entityToDto).orElseThrow(ResourceNotFoundException::new);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactDto> update(@PathVariable Long personId, @PathVariable Long id, @RequestBody @Valid ContactDto contact, BindingResult result) throws MethodArgumentNotValidException {
        validatePerson(personId);

        validator.validate(contact, result);
        if (result.hasErrors()) {
            throw new MethodArgumentNotValidException(null, result);
        }
        
        Contact entity = ContactParser.dtoToEntity(contact);
        entity.setId(id);
        entity.setPerson(new Person(personId));
        service.update(entity);
        return ResponseEntity.ok(ContactParser.entityToDto(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long personId, @PathVariable Long id) {
        validatePerson(personId);

        if (!service.existsById(id)) {
            throw new ResourceNotFoundException();
        }

        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private void validatePerson(Long personId) {
        if (!personService.existsById(personId)) {
            throw new ResourceNotFoundException("User " + personId + " doesn't exists.");
        }
    }
}
