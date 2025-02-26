package HospitalSystem.Patient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Patient {
    private final int ssn;
    private final String name;
    private int contact_info;
    private String address;
    private int age;
    private final Map<LocalDate, PatientRecord> records = new java.util.HashMap<>();

    public Patient(int ssn, String name, int contact_info, String address, int age) {
        this.ssn = ssn;
        this.name = name;
        this.contact_info = contact_info;
        this.address = address;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nSSN: " + ssn + "\n";
    }

    public String getInfo() {
        return "Name: " + name + "\nSSN: " + ssn + "\nContact Info: " + contact_info + "\nAddress: " + address + "\nAge: " + age + "\n";
    }

    public void addRecord(PatientRecord patientRecord) {
        if (records.containsKey(patientRecord.getDate())) {
            throw new IllegalArgumentException("Record already exists for this date");
        }
        records.put(patientRecord.getDate(), patientRecord);
    }

    public PatientRecord getRecord(LocalDate date) {
        return records.get(date);
    }
    public List<PatientRecord> getRecords() {
        List<PatientRecord> recordsList = new ArrayList<>();
        for (PatientRecord record : records.values()) {
            recordsList.add(record);
        }
        return recordsList;
    }
    
    public boolean checkSSN(int ssn) {
        return ssn == this.ssn;
    }

}
