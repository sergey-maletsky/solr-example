package com.snm.solr.repository;

import com.snm.solr.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Boost;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends SolrCrudRepository<User, Long> {

    @Query("name_t:?0")
    Page<User> findByName(String name_t, Pageable pageable);

    @Query("name_t:?0")
    List<User> findByName(String name_t);

    @Query("age:?0")
    List<User> findByAge(Integer age);

    @Query("name_t:?0 AND age:?1")
    List<User> findByNameAndAge(@Boost(1) String name_t, Integer age);
}
