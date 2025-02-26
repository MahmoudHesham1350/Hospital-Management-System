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

    public void setContactInfo(int contactInfo) {
        this.contactInfo = contactInfo;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public String getInfo() {
        return "Name: " + name + "\n" +
                "SSID: " + ssn + "\n" +
                "Contact Info: " + contactInfo + "\n" +
                "Address: " + address + "\n" +
                "Hire Date: " + hireDate + "\n" +
                "Salary: " + salary + "\n";
    }

    public boolean checkSSN(int ssn) {
        return ssn == this.ssn;
    }
}


