package io.project.app.producer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableKafka
@Configuration
@EnableTransactionManagement
public class KafkaProducerConfig {

    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name("alert")
                .partitions(3)
                .replicas(2)//broker count
                .build();
    }

    @Bean
    public KafkaAdmin admin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return new KafkaAdmin(configs);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(KafkaProperties properties) {
        Map<String, Object> props = properties.buildProducerProperties();

        // Customize producer properties
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.RETRIES_CONFIG, Integer.MAX_VALUE); // Retry indefinitely
        props.put(ProducerConfig.RETRIES_CONFIG, 10); // Retry 10 times
        props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 15000); // Adjust based on your network conditions
    //    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class.getName());
      //  props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class.getName());
        // If using Avro or other custom serializers, set them accordingly
        // props.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(props));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(
            ConsumerFactory<String, String> consumerFactory) {

        ConcurrentKafkaListenerContainerFactory<String, String> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);

        return factory;
    }
}
