/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.project.app.producer.resource;

import java.time.ZoneOffset;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.github.javafaker.Faker;
import io.project.app.producer.model.PatientEvent;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

public class PatientStateDataGenerator {

    private static final Faker faker = new Faker();

    public static List<PatientEvent> generateData(int numberOfRecords) {
        List<PatientEvent> patientStates = new CopyOnWriteArrayList<>();

        ForkJoinPool forkJoinPool = new ForkJoinPool(100); // Adjust the parallelism level as needed

        forkJoinPool.submit(()
                -> IntStream.range(0, numberOfRecords)
                        .parallel()
                        .forEach(i -> {

                            PatientEvent patientState = new PatientEvent();
                            LocalDateTime time = generateRandomTime();
                            patientState.setTime(time);
                            patientState.setEventTime(time.toInstant(ZoneOffset.UTC).toEpochMilli());
                            patientState.setPatient(UUID.randomUUID().toString());
                            patientState.setDoctor(UUID.randomUUID().toString());
                            patientState.setLocation(faker.medical().hospitalName());
                            patientState.setOperation(faker.medical().diseaseName());
                            patientState.setPast(faker.medical().diseaseName());
                            patientState.setNext(faker.medical().diseaseName());
                            patientState.setReason(faker.medical().medicineName());
                            patientState.setCondition(faker.medical().symptoms());
                            patientState.setStatus(faker.lorem().word());
                            patientState.setData(generateRandomJsonData());

                            patientStates.add(patientState);
                        })
        ).join();

        return patientStates;
    }

    public static LocalDateTime generateRandomTime() {
        long tenYearsInSeconds = 10L * 365 * 24 * 60 * 60; // 10 years in seconds
        long randomSeconds = new Random().nextLong() % tenYearsInSeconds;

        // Ensure the randomSeconds is positive
        randomSeconds = Math.abs(randomSeconds);

        // Calculate the LocalDateTime
        LocalDateTime now = LocalDateTime.now();
        return now.minusSeconds(randomSeconds);
    }

    private static String generateRandomJsonData() {
        return "{\"key\": \"" + faker.lorem().word() + "\", \"value\": \"" + faker.lorem().sentence() + "\"}";
    }

}
