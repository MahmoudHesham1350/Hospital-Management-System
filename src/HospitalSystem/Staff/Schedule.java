package HospitalSystem.Staff;

import java.util.ArrayList;
import java.util.List;

public class Schedule {
    public static enum Day {
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
    }

    private final List<String>[] schedule;

    public Schedule() {
        this.schedule = new ArrayList[7];
        for (int i = 0; i < 7; i++) {
            this.schedule[i] = new ArrayList<>();
        }
    }

    public void addSchedule(String task, Day day) {
        this.schedule[day.ordinal()].add(task);
    }

    public void removeSchedule(String task, Day day) {
        this.schedule[day.ordinal()].remove(task);
    }

    public List<String> getSchedule(Day day) {
        return new ArrayList<>(this.schedule[day.ordinal()]);
    }

    public void clearSchedule() {
        for (int i = 0; i < 7; i++) {
            this.schedule[i].clear();
        }
    }

    public void clearSchedule(Day day) {
        this.schedule[day.ordinal()].clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Schedule:\n");
        for (Day day : Day.values()) {
            sb.append(day).append(": ").append(schedule[day.ordinal()]).append("\n");
        }
        return sb.toString();
    }
}