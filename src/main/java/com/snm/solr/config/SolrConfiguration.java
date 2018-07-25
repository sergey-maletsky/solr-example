package com.snm.solr.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

import java.io.IOException;
import java.util.UUID;

@Configuration
@EnableSolrRepositories(basePackages={"com.snm.solr"})
@ComponentScan
public class SolrConfiguration {

    private HttpSolrClient client;

    @Bean
    public SolrClient solrClient(@Value("${solr.host}") String solrHost) {

        return new HttpSolrClient.Builder(solrHost).build();
    }

    @Bean
    public SolrTemplate solrTemplate(SolrClient client) {
        return new SolrTemplate(client);
    }

    private void init() {
        final SolrInputDocument document = new SolrInputDocument();
        document.addField("id", UUID.randomUUID().toString());
        document.addField("name", "Robert De Niro");
        document.addField("age", 20);
        try {
            final UpdateResponse updateResponse = client.add("users", document);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
