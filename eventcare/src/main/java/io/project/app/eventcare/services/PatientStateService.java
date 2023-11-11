/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.project.app.eventcare.services;

import io.project.app.eventcare.domain.PatientState;
import io.project.app.eventcare.repositories.PatientStateJpaRepository;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author armen
 */
@Service
@Slf4j
public class PatientStateService {

    @Autowired
    private PatientStateJpaRepository patientStateJpaRepository;

    //@Async(value = "eventAsyncExecutor")
    @Transactional
    public void generateAndSave() {
        long startTime = System.currentTimeMillis();
        log.info("Start Generation ");
        List<PatientState> patientStatesSet = PatientStateDataGenerator.generateData(100000);

        log.info("Start Saving ");
        patientStateJpaRepository.saveAllAndFlush(patientStatesSet);
        long finishTime = System.currentTimeMillis();
        long elapsedTime = finishTime - startTime;
        log.info("Generated and saved 100000 patient states in " + elapsedTime + " milliseconds.");
    }

}
