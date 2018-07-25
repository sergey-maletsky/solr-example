package com.snm.solr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SolrExampleStarter extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SolrExampleStarter.class, args);
    }
}
