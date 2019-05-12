package com.dev.usersapi.parser;

import com.dev.usersapi.dto.ContactDto;
import com.dev.usersapi.entity.Contact;

public final class ContactParser {

    private ContactParser() {
    }

    public static ContactDto entityToDto(Contact contact) {
        ContactDto dto = new ContactDto();
        dto.setContact(contact.getContact());
        dto.setId(contact.getId());
        dto.setType(contact.getType());
        return dto;
    }

    public static Contact dtoToEntity(ContactDto dto) {
        Contact entity = new Contact();
        entity.setContact(dto.getContact());
        entity.setId(dto.getId());
        entity.setType(dto.getType());
        return entity;
    }

}
