package HospitalSystem.Staff;

import java.time.LocalDate;

public class Doctor extends Staff {
    public enum SPECIALIZATION {
        CARDIOLOGIST,
        DERMATOLOGIST,
        GYNECOLOGIST,
        NEUROLOGIST,
        OPHTHALMOLOGIST,
        PEDIATRICIAN,
        PSYCHIATRIST,
        SURGEON,
        UROLOGIST
    }

    private SPECIALIZATION specialization;

    public Doctor(int ssn, String name, int contactInfo, String address, LocalDate hireDate, int salary, SPECIALIZATION specialization, Schedule schedule) {
        super(ssn, name, contactInfo, address, hireDate, salary, schedule);
        this.specialization = specialization;
    }

    
    public void setSpecialization(SPECIALIZATION specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "[Doctor - " + specialization + "] " + getName() + " (SSN: " + getSSN() + ")";
    }
}