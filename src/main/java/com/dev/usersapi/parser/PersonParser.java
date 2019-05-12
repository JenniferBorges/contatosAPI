package com.dev.usersapi.parser;

import com.dev.usersapi.dto.PersonDto;
import com.dev.usersapi.entity.Person;

public final class PersonParser {
    private PersonParser(){}
    
    public static Person dtoToEntity(PersonDto dto){
        Person entity = new Person();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }
    
    public static PersonDto entityToDto(Person person){
        PersonDto dto = new PersonDto();
        dto.setId(person.getId());
        dto.setName(person.getName());
        return dto;
    }
}
