package HospitalSystem.Patient;

import java.time.LocalDate;
import HospitalSystem.Staff.Doctor;
import HospitalSystem.Labs.Test;

public class Record {
    private final LocalDate date;
    private final String diagnosis;
    private final String prescription;
    private final String notes;
    private final Doctor doctor;
    private final Test[] tests;

    public Record(LocalDate date, String diagnosis, String prescription, String notes, Doctor doctor, Test[] tests) {
        this.date = date;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.notes = notes;
        this.doctor = doctor;
        this.tests = tests;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "HospitalSystem.Patient.Record{" + "Date: " + date + "\n" +
                "Diagnosis: " + diagnosis + "\n" +
                "Prescription: " + prescription + "\n" +
                "Notes: " + notes + "\n" +
                "Doctor: " + doctor + "\n" +
                "Tests: " + tests + "\n" +
                "}";
    }
    
}


