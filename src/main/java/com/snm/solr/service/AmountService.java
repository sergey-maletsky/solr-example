package com.snm.solr.service;

import com.snm.solr.model.Amount;

public interface AmountService {
    Amount findOne();

    Integer update(int i);
}
