/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.project.app.eventcare.domain;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

/**
 *
 * @author armen
 */
@Entity
@Table(name = "patient", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientState implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime time;
    @Basic(optional = false)
    @Column(name = "event_time")
    private long eventTime;
    @Basic(optional = false)
    @Column(name = "patient")
    private String patient;
    @Basic(optional = false)
    @Column(name = "doctor")
    private String doctor;
    @Basic(optional = false)
    @Column(name = "operation")
    private String operation;
    @Basic(optional = false)
    @Column(name = "location")
    private String location;
    @Basic(optional = false)
    @Column(name = "past")
    private String past;
    @Basic(optional = false)
    @Column(name = "next")
    private String next;
    @Basic(optional = false)
    @Column(name = "reason")
    private String reason;
    @Basic(optional = false)
    @Column(name = "condition")
    private String condition;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "data")
    private String data;

  

}
