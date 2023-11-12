/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.project.app.producer.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    private LocalDateTime time;

    private long eventTime;

    private String patient;

    private String doctor;

    private String operation;

    private String location;

    private String past;

    private String next;

    private String reason;

    private String condition;

    private String status;

    private String data;

}
