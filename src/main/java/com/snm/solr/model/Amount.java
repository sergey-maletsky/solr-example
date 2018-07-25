package com.snm.solr.model;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

import javax.persistence.Id;

@SolrDocument(solrCoreName = "amounts")
public class Amount {

    @Id
    @Field
    private Long id;

    @Field
    private Integer usersTotalAmount;

    public Amount() {
    }

    public Amount(Long id, Integer usersTotalAmount) {
        this.id = id;
        this.usersTotalAmount = usersTotalAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUsersTotalAmount() {
        return usersTotalAmount;
    }

    public void setUsersTotalAmount(Integer usersTotalAmount) {
        this.usersTotalAmount = usersTotalAmount;
    }
}
