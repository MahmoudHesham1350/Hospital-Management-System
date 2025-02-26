package HospitalSystem.User;

import HospitalSystem.Staff.Schedule;

public class Admin extends User {
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    public Admin() {
    }

    public static boolean login(String username, String password) {
        return USERNAME.equals(username) && PASSWORD.equals(password);
    }

    @Override
    public Schedule getSchedule() {
        return new Schedule(); // Admins don't have schedules, but return empty one to satisfy interface
    }
}
