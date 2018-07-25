package com.snm.solr.repository;

import com.snm.solr.model.Amount;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmountRepository extends SolrCrudRepository<Amount, Long> {

}
