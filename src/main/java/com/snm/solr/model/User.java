package com.snm.solr.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(solrCoreName = "users")
public class User {

    @Id
    @Indexed
    private String id;

    @Indexed
    private String name_t;

    @Indexed
    private Integer age;

    public User() {
    }

    public User(String id, String name_t, Integer age) {
        this.id = id;
        this.name_t = name_t;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "Customer [id=" + this.id + ", name=" + this.name_t + ", age=" + this.age + "]";
    }
}
