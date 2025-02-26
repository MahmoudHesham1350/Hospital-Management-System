package HospitalSystem.Staff;

import java.time.LocalDate;
import java.util.List;

public class Receptionist extends Staff {
    private final List<String> responsibilities;

    public Receptionist(int ssid, String name, int contactInfo, String address, LocalDate hireDate, int salary, List<String> responsibilities) {
        super(ssid, name, contactInfo, address, hireDate, salary);
        this.responsibilities = responsibilities;
    }

    public void addResponsibility(String responsibility) {
        this.responsibilities.add(responsibility);
    }

    public void removeResponsibility(String responsibility) {
        this.responsibilities.remove(responsibility);
    }

    public String getResponsibilities() {
        return "Responsibilities:\n" + String.join("\n", this.responsibilities);
    }
}
