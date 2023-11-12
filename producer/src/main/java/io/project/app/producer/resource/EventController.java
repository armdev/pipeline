package io.project.app.producer.resource;

import io.project.app.producer.publishers.PatientEventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author armena
 */
@RestController
@Slf4j
@RequestMapping("/api/v2/events")
public class EventController {
    
    @Autowired    
    private PatientEventPublisher patientEventPublisher;
    
    
    @GetMapping("/publish")
    public void publishEvent(){
        patientEventPublisher.publishEventBeforeAndAfterDataChange("New change!");
    }

}
