package HospitalSystem;
import HospitalSystem.Mangement.Management;
import HospitalSystem.Patient.Patient;
import HospitalSystem.Patient.Record;
import HospitalSystem.Room.Room;
import HospitalSystem.Room.RoomAllocation;
import HospitalSystem.Staff.*;
import HospitalSystem.User.Admin;
import HospitalSystem.User.User;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;



public class CLI {
    private static User user;
    private final static Hospital hospital = new Hospital();

    private static Scanner scanner = new Scanner(System.in);

    private static int getChoice(int range) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= range) {
                    return choice;
                }
                System.out.println("Invalid choice. Please enter a number between 1 and " + range);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static <T> void displayEnumOptions(T[] options) {
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }

    private static <T> void displayNumberedList(List<T> items) {
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i));
        }
    }

    private static void mainMenu() {
        System.out.println("Welcome to the Hospital System");
        System.out.println("1. Enter as an Admin");
        System.out.println("2. Enter as Staff Member");
        System.out.println("3. Exit");
    }

    private static int receptionistMenu() {
        System.out.println("1. View Schedule");
        System.out.println("2. Create Patient");
        System.out.println("3. View Patients");
        System.out.println("4. Discharge Patient");
        System.out.println("5. View Rooms");
        System.out.println("6. View Allocated Rooms");
        System.out.println("6. Allocate Room");
        System.out.println("7. Discharge Room");
        System.out.println("8. Invoice");
        System.out.println("9. Exit");
        return getChoice(9);
    }

    private static int nurseMenu() {
        System.out.println("1. View Schedule");
        System.out.println("2. View Patients");
        System.out.println("3. View Patient");
        System.out.println("4. View Allocated Rooms");
        System.out.println("5. Exit");
        return getChoice(5);
    }

    private static int doctorMenu() {
        System.out.println("1. View Schedule");
        System.out.println("2. View Patients");
        System.out.println("3. View Patient");
        System.out.println("4. Patient Records");
        System.out.println("5. Exit");
        return getChoice(5);
    }

    private static int generalMangerMenu() {
        System.out.println("1. View Schedule");
        System.out.println("2. View Staff");
        System.out.println("3. View Staff Member");
        System.out.println("4. Add Staff");
        System.out.println("5. Delete Staff");
        System.out.println("6. View Rooms");
        System.out.println("7. Add Room");
        System.out.println("8. Delete Room");
        System.out.println("9. Exit");
        return getChoice(9);
    }

    private static int hrManagerMenu() {
        System.out.println("1. View Schedule");
        System.out.println("2. View Staff");
        System.out.println("3. View Staff Member");
        System.out.println("4. Exit");
        return getChoice(4);
    }

    private static int ceoMenu() {
        System.out.println("1. View Schedule");
        System.out.println("2. View Management");
        System.out.println("3. Exit");
        return getChoice(3);
    }

    private static void adminLogin() {
        System.out.println("Enter Username: ");
        String username = scanner.nextLine();
        System.out.println("Enter Password: ");
        String password = scanner.nextLine();
        if (Admin.login(username, password)) {
            System.out.println("Login Successful");
            System.out.println("Welcome Admin");
            user = new Admin();
        } else {
            throw new IllegalArgumentException("Invalid Credentials");
        }
    }

    private static int get_ssn() {
        System.out.println("Enter SSN: ");
        try {
            int ssn = Integer.parseInt(scanner.nextLine());
            if (ssn < 100000000 || ssn > 999999999) {
                System.out.println("Invalid SSN. Please try again.");
                return 0;
            } else {
                return ssn;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid SSN. Please try again.");
            return 0;
        }
    }

    private static void staffLogin() {
        System.out.println("Enter SSN: ");
        Staff staff = hospital.getStaff(get_ssn());
        if (staff != null) {
            System.out.println("Login Successful");
            System.out.println("Welcome " + staff.toString());
            user = staff;
        } else {
            throw new IllegalArgumentException("Invalid Credentials");
        }
    }

    private static void scheduleMenu() {
        while (true) {
            Schedule schedule = user.getSchedule();
            System.out.println(schedule);
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. Clear Day Schedule");
            System.out.println("4. Clear All Schedule");
            System.out.println("5. Return to Main Menu");
            int choice = getChoice(5);
            switch (choice) {
                case 1:
                    addTask(schedule);
                    break;
                case 2:
                    removeTask(schedule);
                    break;
                case 3:
                    clearDaySchedule(schedule);
                    break;
                case 4:
                    schedule.clearSchedule();
                    System.out.println("Schedule cleared");
                    break;
                case 5:
                    break;
                default:
                    throw new IllegalArgumentException(
                            "Error at" + CLI.class.getName()
                                    + "scheduleMenu() got invalid choice" + choice
                    );
            }
        }
    }

    private static void addTask(Schedule schedule) {
        System.out.println("Enter task description:");
        String task = scanner.nextLine();
        Schedule.Day day = selectDay("Select day:");
        schedule.addSchedule(task, day);
        System.out.println("Task added successfully");
    }

    private static void removeTask(Schedule schedule) {
        Schedule.Day day = selectDay("Select day:");
        List<String> tasks = schedule.getSchedule(day);
        if (tasks.isEmpty()) {
            System.out.println("No tasks found for " + day);
            return;
        }

        System.out.println("\nSelect task to remove:");
        displayNumberedList(tasks);

        int taskChoice = getChoice(tasks.size());
        String selectedTask = tasks.get(taskChoice - 1);
        schedule.removeSchedule(selectedTask, day);
        System.out.println("Task removed successfully");
    }

    private static void clearDaySchedule(Schedule schedule) {
        Schedule.Day day = selectDay("Select day to clear:");
        schedule.clearSchedule(day);
        System.out.println("Schedule cleared for " + day);
    }

    private static Schedule.Day selectDay(String prompt) {
        System.out.println("\n" + prompt);
        displayEnumOptions(Schedule.Day.values());
        int dayChoice = getChoice(Schedule.Day.values().length);
        return Schedule.Day.values()[dayChoice - 1];
    }

    private static void createPatient() {
        System.out.println("Enter Patient Name: ");
        String name = scanner.nextLine();
        System.out.println("Enter Patient SSN: ");
        int ssn = get_ssn();
        System.out.println("Enter Patient Contact Info: ");
        int contact_info = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter Patient Address: ");
        String address = scanner.nextLine();
        System.out.println("Enter Patient Age: ");
        int age = Integer.parseInt(scanner.nextLine());
        try {
            Patient patient = new Patient(ssn, name, contact_info, address, age);
            hospital.addPatient(patient);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void viewPatients() {
        List<Patient> patients = hospital.getPatients();
        if (patients.isEmpty()) {
            System.out.println("No patients found");
            return;
        }
        System.out.println("Patients:");
        displayNumberedList(patients);
    }

    private static Patient getPatient() {
        System.out.println("Enter Patient SSN: ");
        int ssn = get_ssn();
        Patient patient = hospital.getPatient(ssn);
        if (patient == null) {
            throw new IllegalArgumentException("Patient not found");
        }
        return patient;
    }

    private static void dischargePatient() {
        try {
            Patient patient = getPatient();
            hospital.dischargePatient(patient);
            System.out.println("Patient discharged successfully");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Room getRoom() {
        System.out.println("Enter Room Number: ");
        int roomNumber = Integer.parseInt(scanner.nextLine());
        Room room = hospital.getRoom(roomNumber);
        if (room == null) {
            throw new IllegalArgumentException("Room not found");
        }
        return room;
    }

    private static void viewRooms() {
        List<Room> rooms = hospital.getRooms();
        if (rooms.isEmpty()) {
            System.out.println("No rooms found");
            return;
        }
        System.out.println("Rooms:");
        displayNumberedList(rooms);
    }

    private static void viewAllocatedRooms() {
        List<Room> rooms = hospital.getAllocatedRooms();
        if (rooms.isEmpty()) {
            System.out.println("No allocated rooms found");
            return;
        }
        System.out.println("Allocated Rooms:");
        displayNumberedList(rooms);
    }

    private static void allocateRoom() {
        try {
            Room room = getRoom();
            if (room.getAllocationStatus()) {
                throw new IllegalArgumentException("Room is already allocated");
            }
            hospital.allocateRoom(getPatient(), room);
            System.out.println("Room allocated successfully");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void dischargeRoom() {
        try {
            Room room = getRoom();
            if (!room.getAllocationStatus()) {
                throw new IllegalArgumentException("Room is not allocated");
            }
            hospital.dischargeRoom(room);
            System.out.println("Room discharged successfully");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createInvoice() {
        try {
            Room room = getRoom();
            RoomAllocation allocation = hospital.getAllocation(room);
            LocalDate date = getDate();
            String invoice = hospital.createInvoice(allocation, date);
            System.out.println(invoice);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void receptionist() {
        while (true) {
            int choice = receptionistMenu();
            try {
                switch (choice) {
                    case 1: {
                        scheduleMenu();
                        break;
                    }
                    case 2: {
                        createPatient();
                        break;
                    }
                    case 3: {
                        viewPatients();
                        break;
                    }
                    case 4: {
                        dischargePatient();
                        break;
                    }
                    case 5: {
                        viewRooms();
                        break;
                    }
                    case 6: {
                        viewAllocatedRooms();
                        break;
                    }
                    case 7: {
                        allocateRoom();
                        break;
                    }
                    case 8: {
                        dischargeRoom();
                        break;
                    }
                    case 9: {
                        createInvoice();
                        break;
                    }
                    case 10: {
                        return;
                    }
                    default:
                        throw new IllegalArgumentException("Error at" + CLI.class.getName() + "receptionistMenu() got invalid choice" + choice);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void nurse() {
        while (true) {
            int choice = nurseMenu();
            try {
                switch (choice) {
                    case 1: {
                        scheduleMenu();
                        break;
                    }
                    case 2: {
                        viewPatients();
                        break;
                    }
                    case 3: {
                        getPatient();
                        break;
                    }
                    case 4: {
                        viewAllocatedRooms();
                        break;
                    }
                    case 5: {
                        return;
                    }
                    default:
                        throw new IllegalArgumentException("Error at" + CLI.class.getName() + "nurseMenu() got invalid choice" + choice);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void viewAllRecords() {
        Patient patient = getPatient();
        List<Record> records = patient.getRecords();
        if (records.isEmpty()) {
            System.out.println("No records found");
            return;
        }
        System.out.println("Records:");
        displayNumberedList(records);
    }

    private static LocalDate getDate() {
        System.out.println("Enter Record Date (yyyy-MM-dd): ");
        String dateStr = scanner.nextLine();
        try {
            return LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd");
            return getDate();
        }
    }

    private static void viewRecord() {
        Patient patient = getPatient();
        Record record = patient.getRecord(getDate());
        if (record == null) {
            System.out.println("Record not found");
        } else {
            System.out.println(record);
        }
    }

    private static void addRecord() {
        Patient patient = getPatient();
        System.out.println("Enter Diagnosis: ");
        String diagnosis = scanner.nextLine();
        System.out.println("Enter Prescription: ");
        String prescription = scanner.nextLine();
        System.out.println("Enter Notes: ");
        String notes = scanner.nextLine();
        System.out.println("Enter Doctor SSN: ");
        Record record = new Record(getDate(), diagnosis, prescription, notes, (Doctor) user, null);
        patient.addRecord(record);
        System.out.println("Record added successfully");
    }

    private static void patientRecordsMenu() {
        System.out.println("1. Add Record");
        System.out.println("2. View Record");
        System.out.println("3. View All Records");
        System.out.println("4. Exit");
        int choice = getChoice(4);
        switch (choice) {
            case 1:
                addRecord();
                break;
            case 2:
                viewRecord();
                break;
            case 3:
                viewAllRecords();
                break;
            case 4:
                return;
            default:
                throw new IllegalArgumentException(
                        "Error at" + CLI.class.getName()
                                + "patientRecordsMenu() got invalid choice" + choice
                );
        }
    }

    private static void doctor() {
        while (true) {
            int choice = doctorMenu();
            try {
                switch (choice) {
                    case 1 -> scheduleMenu();
                    case 2 -> viewPatients();
                    case 3 -> getPatient();
                    case 4 -> patientRecordsMenu();
                    case 5 -> { return; }
                    default -> throw new IllegalArgumentException("Error at" + CLI.class.getName() + "doctorMenu() got invalid choice" + choice);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static Doctor.SPECIALIZATION getDoctorSpecialization() {
        System.out.println("Enter Doctor Specialization: ");
        displayEnumOptions(Doctor.SPECIALIZATION.values());
        try {
            int choice = getChoice(Doctor.SPECIALIZATION.values().length);
            return Doctor.SPECIALIZATION.values()[choice - 1];
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getDoctorSpecialization();
        }

    }

    private static void viewStaff() {
        List<Staff> staff = hospital.getStaff();
        if (staff.isEmpty()) {
            System.out.println("No staff found");
            return;
        }
        System.out.println("Staff:");
        displayNumberedList(staff);
    }

    private static void addStaff() {
        System.out.println("Enter Staff Name: ");
        String name = scanner.nextLine();
        System.out.println("Enter Staff SSN: ");
        int ssn = get_ssn();
        System.out.println("Enter Staff Contact Info: ");
        int contact_info = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter Staff Address: ");
        String address = scanner.nextLine();
        System.out.println("Enter Staff Age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter Staff Salary: ");
        int salary = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter Staff Department: ");
        String department = scanner.nextLine();
        System.out.println("Enter Staff Type: ");
        String type = scanner.nextLine();
        try {
            switch (type) {
                case "Receptionist" -> hospital.addStaff(new Receptionist(ssn, name, contact_info, address, LocalDate.now(), salary, null));
                case "Nurse" -> hospital.addStaff(new Nurse(ssn, name, contact_info, address, LocalDate.now(), salary, null));
                case "Doctor" -> hospital.addStaff(new Doctor(ssn, name, contact_info, address, LocalDate.now(), salary, getDoctorSpecialization(), null));
                default -> throw new IllegalArgumentException("Invalid Staff Type");
            }

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void removeStaff() {
        try {
            Staff staff = hospital.getStaff(get_ssn());
            hospital.removeStaff(staff);
            System.out.println("Staff deleted successfully");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addRoom() {
        System.out.println("Enter Room Number: ");
        int roomNumber = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter Room Description: ");
        String Description = scanner.nextLine();
        System.out.println("Enter Room Price: ");
        int price = Integer.parseInt(scanner.nextLine());
        try {
            hospital.addRoom(new Room(roomNumber, Description, price));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deleteRoom() {
        try {
            Room room = hospital.getRoom(Integer.parseInt(scanner.nextLine()));
            hospital.removeRoom(room);
            System.out.println("Room deleted successfully");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input Number. Please try again.");
            deleteRoom();
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void generalManager() {
        while (true) {
            int choice = generalMangerMenu();
            try {
                switch (choice) {
                    case 1 -> scheduleMenu();
                    case 2 -> viewStaff();
                    case 3 -> hospital.getStaff(get_ssn()).getInfo();
                    case 4 -> addStaff();
                    case 5 -> removeStaff();
                    case 6 -> viewRooms();
                    case 7 -> addRoom();
                    case 8 -> deleteRoom();
                    case 9 -> { return; }
                    default -> throw new IllegalArgumentException("Error at" + CLI.class.getName() + "generalManager() got invalid choice" + choice);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void hrManager() {
        while (true) {
            int choice = hrManagerMenu();
            try {
                switch (choice) {
                    case 1 -> scheduleMenu();
                    case 2 -> viewStaff();
                    case 3 -> hospital.getStaff(get_ssn()).getInfo();
                    case 4 -> { return; }
                    default -> throw new IllegalArgumentException("Error at" + CLI.class.getName() + "hrManager() got invalid choice" + choice);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void ceo() {
        while (true) {
            int choice = ceoMenu();
            try {
                switch (choice) {
                    case 1 -> scheduleMenu();
                    case 2 -> viewStaff();
                    case 3 -> { return; }
                    default -> throw new IllegalArgumentException("Error at" + CLI.class.getName() + "ceo() got invalid choice" + choice);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void staffMenu() {
            switch (user.getClass().getSimpleName()) {
                case "Receptionist" -> receptionist();
                case "Nurse" -> nurse();
                case "Doctor" -> doctor();
                case "Management" -> {
                    switch (((Management) user).getRole()) {
                        case "GENERAL" -> generalManager();
                        case "HR" -> hrManager();
                        case "CEO" -> ceo();
                        default -> throw new IllegalArgumentException("Invalid Role: " + ((Management) user).getRole());
                    }
                }
                default -> throw new IllegalArgumentException("Invalid Staff Member: " + user.getClass().getSimpleName());
            }
        }

    private static void adminMenu() {
        while (true){
            System.out.println("Welcome Admin");
            viewStaff();
            System.out.println("1. Add Staff");
            System.out.println("2. Delete Staff");
            System.out.println("3. Exit");
            int choice = getChoice(3);
            switch (choice) {
                case 1:
                    addStaff();
                    break;
                case 2:
                    removeStaff();
                    break;
                case 3:
                    return;
                default:
                    throw new IllegalArgumentException("Error at" + CLI.class.getName() + "adminMenu() got invalid choice" + choice);
            }
        }
    }

    public static void run() {
        while (true) {
            user = null;
            mainMenu();
            int choice = getChoice(3);
            try {
            switch (choice) {
                case 1:
                    adminLogin();
                    adminMenu();
                    break;
                case 2:
                    staffLogin();
                    staffMenu();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

