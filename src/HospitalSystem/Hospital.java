package HospitalSystem;

import HospitalSystem.Patient.Patient;
import HospitalSystem.Room.Room;
import HospitalSystem.Room.RoomAllocation;
import HospitalSystem.Staff.Staff;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Hospital {
    private String name = "Hospital";
    private final List<Staff> staff;
    private final List<Patient> patients;
    private final List<Room> rooms;
    private final List<RoomAllocation> roomAllocations;
    private final List<String> invoices;

    Hospital() {
        this.staff = new ArrayList<>();
        this.patients = new ArrayList<>();
        this.rooms = new ArrayList<>();
        this.roomAllocations = new ArrayList<>();
        this.invoices = new ArrayList<>();
    }

    // ================================================================================================================
    public void addStaff(Staff staff) {
        this.staff.add(staff);
    }

    public List<Staff> getStaff() {
        return this.staff;
    }

    public Staff getStaff(int ssn) {
        for (Staff staff : this.staff) {
            if (staff.checkSSN(ssn)) {
                return staff;
            }
        }
        return null;
    }

    public void removeStaff(Staff staff) {
        if (!this.staff.contains(staff)) {
            throw new IllegalArgumentException("Staff not found");
        }
        this.staff.remove(staff);
    }

    // ================================================================================================================
    public void addPatient(Patient patient) {
        this.patients.add(patient);
    }

    public Patient getPatient(int ssn) {
        for (Patient patient : this.patients) {
            if (patient.checkSSN(ssn)) {
                return patient;
            }
        }
        return null;
    }

    public List<Patient> getPatients() {
        return this.patients;
    }

    public void dischargePatient(Patient patient) {
        this.patients.remove(patient);
    }

    // Room Methods
    public void addRoom(Room room) {
        this.rooms.add(room);
    }

    public Room getRoom(int roomNumber) {
        for (Room room : this.rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }

    public List<Room> getRooms() {
        return this.rooms;
    }

    public void removeRoom(Room room) {
        this.rooms.remove(room);
    }

    // ================================================================================================================
    public void allocateRoom(Patient patient, Room room) {
        if (room.getAllocationStatus()) {
            throw new IllegalArgumentException("Room already allocated");
        }
        this.roomAllocations.add(new RoomAllocation(room, patient));
        room.setAllocation(true);
    }

    public void dischargeRoom(Room room) {
        if (!room.getAllocationStatus()) {
            throw new IllegalArgumentException("Room not allocated");
        }
        for (RoomAllocation roomAllocation : this.roomAllocations) {
            if (roomAllocation.getRoom().equals(room)) {
                this.roomAllocations.remove(roomAllocation);
                room.setAllocation(false);
                return;
            }
        }
        throw new IllegalArgumentException("Room not allocated");
    }

    public List<Room> getAllocatedRooms() {
        List<Room> allocatedRooms = new ArrayList<>();
        for (RoomAllocation roomAllocation : this.roomAllocations) {
            allocatedRooms.add(roomAllocation.getRoom());
        }
        return allocatedRooms;
    }

    public RoomAllocation getAllocation(Room room) {
        for (RoomAllocation roomAllocation : this.roomAllocations) {
            if (roomAllocation.getRoom().equals(room)) {
                return roomAllocation;
            }
        }
        throw new IllegalArgumentException("Room not allocated");
    }

    // ================================================================================================================
    public String createInvoice(RoomAllocation allocation, LocalDate endDate) {
        String invoice = (allocation.getInvoice(endDate));
        allocation.getRoom().setAllocation(false);
        roomAllocations.remove(allocation);
        this.invoices.add(invoice);
        return invoice;
    }

    public List<String> getInvoices() {
        return this.invoices;
    }
}
