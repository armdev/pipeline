/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.project.app.eventcare.resources;

import io.project.app.eventcare.services.PatientStateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author armen
 */
@Slf4j
@RestController
@RequestMapping("/api/v2/data")
public class DataGenerator {
    
    @Autowired
    private PatientStateService patientStateService;
    
    @GetMapping("/run")    
    public void run(){
        patientStateService.generateAndSave();
    }
    
    
    @GetMapping("/run/id")    
    public void push(@RequestParam String firstId,@RequestParam String secondId){
        patientStateService.generateAndSaveWithId(firstId, secondId);
    }
    
}
