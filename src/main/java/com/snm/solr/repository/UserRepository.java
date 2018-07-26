package com.snm.solr.repository;

import com.snm.solr.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends SolrCrudRepository<User, Long> {

    Page<User> findByName(String name, Pageable pageable);

    List<User> findByName(String name);

    List<User> findByAge(Integer age);

    List<User> findByNameAndAge(String name, Integer age);
}
