package io.project.app.producer.publishers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PatientEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    public PatientEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publishEventBeforeAndAfterDataChange(String event) {//can be patient class
        eventPublisher.publishEvent(event);

    }

}
