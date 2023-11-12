package io.project.app.producer;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author armen
 */
@Configuration(proxyBeanMethods = false)
public class RestTemplateConfiguration {

    @Bean
    public RestTemplate getRestTemplate(RestTemplateBuilder builder) {

        return builder.build();
    }

    @Bean
    public RestTemplate restTemplate() {
        int timeout = 5000; // Timeout in milliseconds
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectionRequestTimeout(timeout);
        requestFactory.setConnectTimeout(timeout);
        return new RestTemplate(requestFactory);
    }
}
