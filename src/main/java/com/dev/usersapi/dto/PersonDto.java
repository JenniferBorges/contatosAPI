package com.dev.usersapi.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PersonDto {

    private Long id;

    @NotBlank
    @Size(min = 5, max = 255)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
