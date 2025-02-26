package HospitalSystem.Staff;

import java.time.LocalDate;
import java.util.List;

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

    public Doctor(int ssid, String name, int contactInfo, String address, LocalDate hireDate, int salary, SPECIALIZATION specialization, List<String>[] schedule) {
        super(ssid, name, contactInfo, address, hireDate, salary);
        this.specialization = specialization;
    }


    public void setSpecialization(SPECIALIZATION specialization) {
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return this.specialization.toString();
    }
}