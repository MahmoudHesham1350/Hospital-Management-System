
package HospitalSystem.Mangement;
import HospitalSystem.Staff.Staff;

import java.time.LocalDate;

public class Management extends Staff {
    private String role;

    public Management(String role, int ssid, String name, int contactInfo,
                      String address, LocalDate hireDate, int salary) {
        super(ssid, name, contactInfo, address, hireDate, salary);
        this.role = role;
    }

    public void updateRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}