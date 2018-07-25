package com.snm.solr.service.impl;

import com.snm.solr.model.Amount;
import com.snm.solr.repository.AmountRepository;
import com.snm.solr.service.AmountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@Transactional
public class AmountServiceImpl implements AmountService {

    private static final Logger log = LoggerFactory.getLogger(AmountServiceImpl.class);

    @Autowired
    private AmountRepository amountRepository;

    @Override
    public Amount findOne() {

        Amount amount = amountRepository.findById(1L).orElse(null);
        if (Objects.isNull(amount)) {
            amount = new Amount(1L, 0);
            amountRepository.save(amount);
        }

        return amount;
    }

    @Override
    public Integer update(int usersTotalAmount) {

        Amount amount = amountRepository.findById(1L).orElseGet(null);
        amount.setUsersTotalAmount(usersTotalAmount);
        return amountRepository.save(amount).getUsersTotalAmount();
    }
}
