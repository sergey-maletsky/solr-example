package com.snm.solr.repository;

import com.snm.solr.model.User;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends SolrCrudRepository<User, String> {
    List<User> findByName(String name);
}
