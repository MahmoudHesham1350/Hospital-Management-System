package HospitalSystem.Patient;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Patient {
    private final int ssn;
    private final String name;
    private int contact_info;
    private String address;
    private int age;
    private Map<LocalDate, Record> records;

    public Patient(int ssn, String name, int contact_info, String address, int age) {
        this.ssn = ssn;
        this.name = name;
        this.contact_info = contact_info;
        this.address = address;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n";
    }

    public void addRecord(Record record) {
        records.put(record.getDate(), record);
    }

    public Record getRecord(LocalDate date) {
        return records.get(date);
    }

    public List<Record> getRecords() {
        return (List<Record>) records.values();
    }
    public boolean checkSSN(int ssn) {
        return ssn == this.ssn;
    }

}
