package HospitalSystem.Patient;

import HospitalSystem.Labs.Test;
import HospitalSystem.Staff.Doctor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PatientRecord {
    private final LocalDate date;
    private final String diagnosis;
    private final String prescription;
    private final String notes;
    private final Doctor doctor;
    private final List<Test> tests;

    public PatientRecord(LocalDate date, String diagnosis, String prescription, String notes, Doctor doctor, Test[] tests) {
        this.date = date;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.notes = notes;
        this.doctor = doctor;
        this.tests = new ArrayList<>();
        if (tests != null) {
            this.tests.addAll(Arrays.asList(tests));
        }
    }
    
    public PatientRecord(LocalDate date, String diagnosis, String prescription, String notes, Doctor doctor) {
        this(date, diagnosis, prescription, notes, doctor, null);
    }

    public LocalDate getDate() {
        return date;
    }
    
    public void addTest(Test test) {
        this.tests.add(test);
    }
    
    public List<Test> getTests() {
        return new ArrayList<>(tests);
    }

    @Override
    public String toString() {
        StringBuilder testsStr = new StringBuilder();
        for (Test test : tests) {
            testsStr.append(test).append(", ");
        }
        String testsString = tests.isEmpty() ? "None" : testsStr.toString().trim();
        if (testsString.endsWith(",")) {
            testsString = testsString.substring(0, testsString.length() - 1);
        }
        
        return "Record{\n" + "Date: " + date + "\n" +
                "Diagnosis: " + diagnosis + "\n" +
                "Prescription: " + prescription + "\n" +
                "Notes: " + notes + "\n" +
                "Doctor: " + doctor + "\n" +
                "Tests: " + testsString + "\n" +
                "}";
    }
}


