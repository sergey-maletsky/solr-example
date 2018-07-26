package com.snm.solr.dto;

import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class UserDto implements Serializable {

    @NotBlank
    private String name_t;

    @NotNull
    private Integer age;

    public String getName_t() {
        return name_t;
    }

    public void setName_t(String name_t) {
        this.name_t = name_t;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
