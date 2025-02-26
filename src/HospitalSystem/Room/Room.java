package HospitalSystem.Room;

import java.util.Set;

public class Room {
    private static Set<Integer> roomNumbers;
    private final int roomNumber;
    private String roomDescription;
    private int pricePerDay;
    private boolean isAllocated;

    public Room(int room_number, String roomDescription, int pricePerDay) {
        if (roomNumbers.contains(room_number)) {
            throw new IllegalArgumentException("Room number already exists");
        }
        this.roomNumber = room_number;
        this.roomDescription = roomDescription;
        this.pricePerDay = pricePerDay;
        this.isAllocated = false;
        roomNumbers.add(room_number);
    }

    @Override
    protected void finalize() {
        roomNumbers.remove(roomNumber);
    }

    public void updateRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public void updatePricePerDay(int pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public int getRoomExpense(int numberOfDays) {
        return this.pricePerDay * numberOfDays;
    }

    public void setAllocation(boolean isAllocated) {
        this.isAllocated = isAllocated;
    }

    public boolean getAllocationStatus() {
        return this.isAllocated;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomDetails() {
        return  "HospitalSystem.Labs.Room.Room{ \nnumber: " + roomNumber + "\n" +
                "HospitalSystem.Labs.Room.Room description: " + roomDescription + "\n" +
                "Price per day: " + pricePerDay + "\n" +
                "}";
    }

    @Override
    public String toString() {
        return "HospitalSystem.Labs.Room.Room number: " + roomNumber;
    }
}
