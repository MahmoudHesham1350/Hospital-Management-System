package HospitalSystem.Staff;

import java.time.LocalDate;

public class Nurse extends Staff {
    private String role;

    public Nurse(int ssid, String name, int contactInfo, String address, LocalDate hireDate, int salary, String role) {
        super(ssid, name, contactInfo, address, hireDate, salary);
        this.role = role != null ? role : "General Nurse";
    }

    public Nurse(int ssid, String name, int contactInfo, String address, LocalDate hireDate, int salary, String role, Schedule schedule) {
        super(ssid, name, contactInfo, address, hireDate, salary, schedule);
        this.role = role != null ? role : "General Nurse";
    }

    @Override
    public String toString() {
        return "[Nurse] " + getName() + " (SSN: " + getSSN() + ")";
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
