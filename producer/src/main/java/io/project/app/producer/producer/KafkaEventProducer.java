package io.project.app.producer.producer;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @SneakyThrows
    public void sendMessage(String message) {
        log.info("Sending message: {} to Kafka.", message);
        String transactionId = UUID.randomUUID().toString();
        ProducerRecord<String, String> producerRecord
                = new ProducerRecord<>("alert", transactionId, message);
        producerRecord.headers().add(KafkaHeaders.TOPIC, "alert".getBytes(StandardCharsets.UTF_8));
        producerRecord.headers().add(KafkaHeaders.KEY, transactionId.getBytes(StandardCharsets.UTF_8));
        producerRecord.headers().add("X-Producer-Header", "alert".getBytes(StandardCharsets.UTF_8));
        kafkaTemplate.send(producerRecord);
    }
}
