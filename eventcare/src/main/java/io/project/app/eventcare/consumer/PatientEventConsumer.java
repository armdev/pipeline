package io.project.app.eventcare.consumer;

import com.google.gson.Gson;
import io.project.app.eventcare.domain.PatientState;
import io.project.app.eventcare.repositories.PatientStateJpaRepository;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

/**
 *
 * @author armena
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PatientEventConsumer {

    @Autowired
    private PatientStateJpaRepository patientStateJpaRepository;

    @KafkaListener(topics = "alert", groupId = "event-duty", concurrency = "3")
    public void receive(@Payload String payload,
            @Header(KafkaHeaders.KEY) String key,
            @Header(KafkaHeaders.TOPIC) String topic,
            @Header("X-Producer-Header") String header
    ) {

        log.info("New Event  KEY '{}' ", key);
        log.info("New Event Payload '{}' ", payload);

        Gson gson = new Gson();
        PatientState alert = gson.fromJson(payload, PatientState.class);

        PatientState save = patientStateJpaRepository.save(alert); //done
    }

}
