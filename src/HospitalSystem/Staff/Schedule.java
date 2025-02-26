package HospitalSystem.Staff;

import java.util.ArrayList;
import java.util.List;

public class Schedule {

    public enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    private final List<String>[] schedule;

    @SuppressWarnings("unchecked")
    public Schedule() {
        // Use suppressed warning for this specific initialization
        schedule = new List[7];
        // Initialize each element with a new ArrayList<String>
        for (int i = 0; i < 7; i++) {
            schedule[i] = new ArrayList<>();
        }
    }

    public void addSchedule(String task, Day day) {
        schedule[day.ordinal()].add(task);
    }

    public void removeSchedule(String task, Day day) {
        schedule[day.ordinal()].remove(task);
    }

    public List<String> getSchedule(Day day) {
        return new ArrayList<>(schedule[day.ordinal()]);
    }

    public void clearSchedule(Day day) {
        schedule[day.ordinal()].clear();
    }

    public void clearSchedule() {
        for (Day day : Day.values()) {
            clearSchedule(day);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Schedule:\n");
        for (Day day : Day.values()) {
            sb.append(day).append(":\n");
            List<String> tasks = schedule[day.ordinal()];
            if (tasks.isEmpty()) {
                sb.append("\tNo tasks\n");
            } else {
                for (String task : tasks) {
                    sb.append("\t").append(task).append("\n");
                }
            }
        }
        return sb.toString();
    }
}