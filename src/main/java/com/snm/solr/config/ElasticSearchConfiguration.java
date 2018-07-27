package com.snm.solr.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.snm.solr.repository")
@ComponentScan(basePackages = {"com.snm.solr.service"})
public class ElasticSearchConfiguration {

    @Value("${elasticsearch.host}")
    private String EsHost;

    @Value("${elasticsearch.port}")
    private int EsPort;

    @Value("${elasticsearch.clustername}")
    private String EsClusterName;

    @Value("${elasticsearch.home:/home/cybertron/Work/my/elasticsearch-5.6.10}")
    private String elasticsearchHome;

    @Bean
    public Client client() throws Exception {

        try {
            final Path tmpDir = Files.createTempDirectory(Paths.get(System.getProperty("java.io.tmpdir")), "elasticsearch_data");

            final Settings esSettings =
                    Settings.builder()
                            .put("http.enabled", "false")
                            .put("cluster.name", EsClusterName)
                            .put("path.data", tmpDir.toAbsolutePath().toString())
                            .put("path.home", elasticsearchHome).build();

            return new PreBuiltTransportClient(esSettings)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(EsHost), EsPort))
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(EsHost), 9300));

        /*    return new NodeBuilder()
                    .local(true)
                    .settings(elasticsearchSettings)
                    .node()
                    .client();*/
        } catch (final IOException ioex) {
            throw new RuntimeException();
        }
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
        return new ElasticsearchTemplate(client());
    }
}
