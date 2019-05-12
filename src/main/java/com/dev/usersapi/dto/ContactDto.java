package com.dev.usersapi.dto;

import com.dev.usersapi.enums.ContactType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ContactDto {

    private Long id;

    @NotBlank
    private String contact;

    @NotNull
    private ContactType type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public ContactType getType() {
        return type;
    }

    public void setType(ContactType type) {
        this.type = type;
    }

}
