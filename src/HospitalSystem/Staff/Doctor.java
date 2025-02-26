package HospitalSystem.Staff;

import java.time.LocalDate;
import java.util.List;

public class Doctor extends Staff {
    public enum SPECIALIZATION {
        CARDIOLOGY,
        DERMATOLOGY,
        ENDOCRINOLOGY,
        GASTROENTEROLOGY,
        NEUROLOGY,
        ONCOLOGY,
        PEDIATRICS,
        PSYCHIATRY,
        RADIOLOGY,
        SURGERY
    }

    private final SPECIALIZATION specialization;

    public Doctor(int ssid, String name, int contactInfo, String address, LocalDate hireDate, int salary, SPECIALIZATION specialization, List<String>[] schedule) {
        super(ssid, name, contactInfo, address, hireDate, salary);
        this.specialization = specialization;
    }

    @Override
    public void getInfo() {
        super.getInfo();
        System.out.println("Specialization: " + specialization);
    }

    @Override
    public String toString() {
        return "[Doctor - " + specialization + "] " + getName() + " (SSN: " + getSSN() + ")";
    }
}