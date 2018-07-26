package com.snm.solr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({
        "classpath:spring/application.properties"
})
public class SolrExampleConfiguration {
}
