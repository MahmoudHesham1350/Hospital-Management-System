package HospitalSystem.Staff;

import HospitalSystem.User.User;
import java.time.LocalDate;

public abstract class Staff extends User {
    private final int ssn;
    private final String name;
    private int contactInfo;
    private String address;
    private final LocalDate hireDate;
    private int salary;
    private final Schedule schedule;

    public Staff(int ssn, String name, int contactInfo, String address, LocalDate hireDate, int salary) {
        this.ssn = ssn;
        this.name = name;
        this.contactInfo = contactInfo;
        this.address = address;
        this.hireDate = hireDate;
        this.salary = salary;
        this.schedule = new Schedule();
    }

    public Staff(int ssn, String name, int contactInfo, String address, LocalDate hireDate, int salary, Schedule schedule) {
        this.ssn = ssn;
        this.name = name;
        this.contactInfo = contactInfo;
        this.address = address;
        this.hireDate = hireDate;
        this.salary = salary;
        this.schedule = (schedule != null) ? schedule : new Schedule();
    }

    public void setContactInfo(int contactInfo) {
        this.contactInfo = contactInfo;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getSSN() {
        return ssn;
    }

    public String getName() {
        return name;
    }

    public int getContactInfo() {
        return contactInfo;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public Schedule getSchedule() {
        return schedule;
    }

    public void getInfo() {
        System.out.println("Name: " + name);
        System.out.println("SSN: " + ssn);
        System.out.println("Contact Info: " + contactInfo);
        System.out.println("Address: " + address);
        System.out.println("Hire Date: " + hireDate);
        System.out.println("Salary: $" + salary);
        System.out.println("Role: " + getClass().getSimpleName());
    }

    public boolean checkSSN(int ssn) {
        return ssn == this.ssn;
    }

    @Override
    public String toString() {
        return "[" + getClass().getSimpleName() + "] " + name + " (SSN: " + ssn + ")";
    }
}


