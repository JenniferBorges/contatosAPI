package com.dev.usersapi.api.v1;

import com.dev.usersapi.dto.PersonDto;
import com.dev.usersapi.entity.Person;
import com.dev.usersapi.exception.ResourceNotFoundException;
import com.dev.usersapi.parser.PersonParser;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.dev.usersapi.service.PersonService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/v1/persons")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping
    public @ResponseBody
    List<PersonDto> list() {
        return service.findAll().stream()
                .map(PersonParser::entityToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public @ResponseBody
    PersonDto getById(@PathVariable Long id) {
        return service.findById(id).map(PersonParser::entityToDto).orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping
    public ResponseEntity<PersonDto> create(@RequestBody @Valid PersonDto user) {
        final Person entity = PersonParser.dtoToEntity(user);
        service.create(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(PersonParser.entityToDto(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDto> update(@PathVariable Long id, @Valid @RequestBody PersonDto user) {
        user.setId(id);
        Person entity = PersonParser.dtoToEntity(user);
        service.update(entity);
        return ResponseEntity.ok(PersonParser.entityToDto(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if(!service.existsById(id)){
            throw new ResourceNotFoundException();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
