package HospitalSystem.User;

import HospitalSystem.Staff.Schedule;

public class Admin extends User {
    public static boolean login(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }

    public Schedule getSchedule() {
        return null;
    }
}
