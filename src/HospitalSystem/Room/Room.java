package HospitalSystem.Room;

import java.util.Set;

public class Room {
    private static Set<Integer> roomNumbers;
    private final int roomNumber;
    private String roomDescription;
    private int pricePerDay;
    private boolean isAllocated;

    public Room(int roomNumber, String roomDescription, int pricePerDay) {
        if (roomNumbers == null) {
            roomNumbers = new java.util.HashSet<>();
        }
        if (roomNumbers.contains(roomNumber)) {
            throw new IllegalArgumentException("Room number already exists");
        }
        this.roomNumber = roomNumber;
        this.roomDescription = roomDescription;
        this.pricePerDay = pricePerDay;
        this.isAllocated = false;
        roomNumbers.add(roomNumber);
    }

    public static void removeNumber(int roomNumber) {
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
        return  "Room { \nnumber: " + roomNumber + "\n" +
                "Room description: " + roomDescription + "\n" +
                "Price per day: " + pricePerDay + "\n" +
                "}";
    }

    @Override
    public String toString() {
        return "Room number: " + roomNumber;
    }
}
