package HospitalSystem.Room;

import HospitalSystem.Patient.Patient;

import java.time.LocalDate;

public class RoomAllocation {
    private static int nextAllocationId = 1;
    private final int allocationId;
    private final Room room;
    private final Patient patient;
    private final Expenses additionalExpenses;
    private final LocalDate startDate;

    public RoomAllocation(Room room, Patient patient) {
        this.allocationId = nextAllocationId++;
        this.room = room;
        this.patient = patient;
        this.startDate = LocalDate.now();
        this.additionalExpenses = new Expenses();
    }

    public Patient getPatient() {
        return patient;
    }

    public void addExpense(String name, int amount) {
        additionalExpenses.addExpense(name, amount);
    }

    public int getTotalExpenses(LocalDate date) {
        int numberOfDays = (int) (java.time.temporal.ChronoUnit.DAYS.between(startDate, date));
        return additionalExpenses.getTotalExpenses() + (room.getRoomExpense(numberOfDays));
    }

    public Room getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return  "Room Allocation{" + "Allocation ID: " + allocationId + "\n" +
                room + "\n" +
                patient + "\n" +
                "Start date: " + startDate + "\n" +
                additionalExpenses + "\n" +
                "}";
    }

    public String getInvoice(LocalDate endDate) {
        return "Invoice for patient: " + patient + "\n" +
                "Total expenses: " + getTotalExpenses(endDate) + "\n" +
                "Date: " + endDate + "\n" +
                "HospitalSystem.Labs.Room.Room: " + room + "\n" +
                "Start date: " + startDate + "\n" +
                "end date: " + endDate + "\n" +
                "room expenses: " + room.getRoomExpense((int) (java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate))) + "\n" +
                additionalExpenses + "\n" +
                "}";
    }

}

