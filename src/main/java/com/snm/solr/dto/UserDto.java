package com.snm.solr.dto;

import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class UserDto implements Serializable {

    @NotBlank
    private String name;

    @NotNull
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
