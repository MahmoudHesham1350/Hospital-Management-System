package HospitalSystem.Mangement;

import HospitalSystem.Staff.Schedule;
import HospitalSystem.Staff.Staff;
import java.time.LocalDate;

public class Management extends Staff {
    private final String role;

    public Management(int SSN, String name, int contact_info, String address, LocalDate join_date, int salary, String role, Schedule schedule) {
        super(SSN, name, contact_info, address, join_date, salary, schedule);
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    @Override
    public void getInfo() {
        super.getInfo();
        System.out.println("Management Role: " + role);
    }

    @Override
    public String toString() {
        return "[Management - " + role + "] " + getName() + " (SSN: " + getSSN() + ")";
    }
}