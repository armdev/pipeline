package io.project.app.producer.listeners;

import com.google.gson.Gson;
import io.project.app.producer.model.PatientEvent;
import io.project.app.producer.producer.KafkaEventProducer;
import io.project.app.producer.resource.PatientStateDataGenerator;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author armen
 */
@Component
@Slf4j
public class SystemEventListener {

    @Autowired
    private KafkaEventProducer kafkaSender;

    @EventListener
    public void handleSystemEvents(String event) {
        // Process the account save event here
      ///  SystemEvent systemEvent = (SystemEvent) event.getSource();
        log.info("SystemEventCreationEvent " + event);
        
        List<PatientEvent> generateData = PatientStateDataGenerator.generateData(100); //100 changes
          Gson gson = new Gson();
        for(PatientEvent convertTojson : generateData){
            
        String toJson = gson.toJson(convertTojson);
        kafkaSender.sendMessage(toJson);
        }
      
        
    }
}
