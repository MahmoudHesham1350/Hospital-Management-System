package HospitalSystem;
import HospitalSystem.Mangement.Management;
import HospitalSystem.Patient.Patient;
import HospitalSystem.Patient.PatientRecord;
import HospitalSystem.Room.Room;
import HospitalSystem.Room.RoomAllocation;
import HospitalSystem.Staff.*;
import HospitalSystem.User.Admin;
import HospitalSystem.User.User;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class CLI {
    private static User user;
    private final static Hospital hospital = new Hospital();
    private static Scanner scanner = new Scanner(System.in);

    public static Hospital getHospital() {
        return hospital;
    }

    
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

    private static int getIntInput(String prompt, int min, int max) {
        while (true) {
            System.out.println(prompt);
            try {
                String input = scanner.nextLine().trim();
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("Please enter a number between " + min + " and " + max);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
    
    private static int getIntInput(String prompt) {
        while (true) {
            System.out.println(prompt);
            try {
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
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

    private static int get_ssn() {
        while (true) {
            System.out.println("Enter SSN (9 digits): ");
            try {
                int ssn = Integer.parseInt(scanner.nextLine().trim());
                if (ssn >= 100000000 && ssn <= 999999999) {
                    return ssn;
                }
                System.out.println("SSN must be 9 digits. Please try again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric SSN.");
            }
        }
    }

        private static LocalDate getDate() {
            System.out.println("Enter Record Date (yyyy-M-d): ");
            String dateStr = scanner.nextLine().trim();
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
                return LocalDate.parse(dateStr, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use yyyy-M-d");
                return getDate();
            }
        }
        
        private static void mainMenu() {
        System.out.println("===== Hospital System =====");
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
        System.out.println("7. Allocate Room");
        System.out.println("8. Discharge Room");
        System.out.println("9. Invoice");
        System.out.println("10. Exit");
        return getChoice(10);
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
                    return;
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
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty");
            return;
        }
        
        int ssn = get_ssn();
        
        int contact_info;
        try {
            contact_info = getIntInput("Enter Patient Contact Info: ");
            if (contact_info <= 0) {
                System.out.println("Contact info must be a positive number");
                return;
            }
        } catch (Exception e) {
            System.out.println("Invalid contact info");
            return;
        }
        
        System.out.println("Enter Patient Address: ");
        String address = scanner.nextLine().trim();
        if (address.isEmpty()) {
            System.out.println("Address cannot be empty");
            return;
        }
        
        int age;
        try {
            age = getIntInput("Enter Patient Age: ", 0, 150);
        } catch (Exception e) {
            System.out.println("Invalid age");
            return;
        }
        
        try {
            Patient patient = new Patient(ssn, name, contact_info, address, age);
            hospital.addPatient(patient);
            System.out.println("Patient created successfully");
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
        int roomNumber;
        try {
            roomNumber = getIntInput("Enter Room Number: ", 1, Integer.MAX_VALUE);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid room number");
        }
        
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

    // ================================================================================================================
    // Patient Record Methods
    
    private static void viewAllRecords(Patient patient) {
        List<PatientRecord> records = patient.getRecords();
        if (records.isEmpty()) {
            System.out.println("No records found");
            return;
        }
        System.out.println("Records:");
        displayNumberedList(records);
    }

    private static void viewRecord(Patient patient) {
        PatientRecord record = patient.getRecord(getDate());
        if (record == null) {
            System.out.println("Record not found");
        } else {
            System.out.println(record);
        }
    }

    private static void addRecord(Patient patient) {
        System.out.println("Enter Diagnosis: ");
        String diagnosis = scanner.nextLine();
        System.out.println("Enter Prescription: ");
        String prescription = scanner.nextLine();
        System.out.println("Enter Notes: ");
        String notes = scanner.nextLine();
        System.out.println("Enter Doctor SSN: ");
        PatientRecord record = new PatientRecord(getDate(), diagnosis, prescription, notes, (Doctor) user, null);
        patient.addRecord(record);
        System.out.println("Record added successfully");
    }

    private static void patientRecordsMenu() {
        Patient patient = getPatient();
        System.out.println("1. Add Record");
        System.out.println("2. View Record");
        System.out.println("3. View All Records");
        System.out.println("4. Exit");
        int choice = getChoice(4);
        switch (choice) {
            case 1:
                addRecord(patient);
                break;
            case 2:
                viewRecord(patient);
                break;
            case 3:
                viewAllRecords(patient);
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

    // ================================================================================================================
    // Staff Management Methods
    
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
    
    private static String getManagementRole() {
        System.out.println("Select Management Role:");
        System.out.println("1. General Manager");
        System.out.println("2. HR Manager");
        System.out.println("3. CEO");
        
        int choice = getChoice(3);
        return switch (choice) {
            case 1 -> "GENERAL";
            case 2 -> "HR";
            case 3 -> "CEO";
            default -> throw new IllegalArgumentException("Invalid choice");
        };
    }
    
    private static void addManagementStaff() {
        System.out.println("Enter Staff Name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty");
            return;
        }
        
        int ssn = get_ssn();
        
        int contactInfo;
        try {
            contactInfo = getIntInput("Enter Staff Contact Info: ");
        } catch (Exception e) {
            System.out.println("Invalid contact info");
            return;
        }
        
        System.out.println("Enter Staff Address: ");
        String address = scanner.nextLine().trim();
        
        int salary;
        try {
            salary = getIntInput("Enter Staff Salary: ", 0, Integer.MAX_VALUE);
        } catch (Exception e) {
            System.out.println("Invalid salary");
            return;
        }
        
        String role = getManagementRole();
        
        try {
            hospital.addStaff(new Management(ssn, name, contactInfo, address, LocalDate.now(), salary, role, null));
            System.out.println("Management staff added successfully");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
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
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty");
            return;
        }
        int ssn = get_ssn();
        int contact_info;
        try {
            contact_info = getIntInput("Enter Staff Contact Info: ");
        } catch (Exception e) {
            System.out.println("Invalid contact info");
            return;
        }
        System.out.println("Enter Staff Address: ");
        String address = scanner.nextLine().trim();
        int age;
        try {
            age = getIntInput("Enter Staff Age: ", 18, 100);
        } catch (Exception e) {
            System.out.println("Invalid age");
            return;
        }
        int salary;
        try {
            salary = getIntInput("Enter Staff Salary: ", 0, Integer.MAX_VALUE);
        } catch (Exception e) {
            System.out.println("Invalid salary");
            return;
        }
        System.out.println("Enter Staff Department: ");
        String department = scanner.nextLine().trim();
        
        System.out.println("Enter Staff Type (Receptionist/Nurse/Doctor/Management): ");
        String type = scanner.nextLine().trim().toLowerCase(); // Convert to lowercase
        
        try {
            switch (type) {
                case "receptionist" -> hospital.addStaff(new Receptionist(ssn, name, contact_info, address, LocalDate.now(), salary)); 
                case "nurse" -> hospital.addStaff(new Nurse(ssn, name, contact_info, address, LocalDate.now(), salary, "General Nurse")); 
                case "doctor" -> hospital.addStaff(new Doctor(ssn, name, contact_info, address, LocalDate.now(), salary, getDoctorSpecialization(), null));
                case "management" -> hospital.addStaff(new Management(ssn, name, contact_info, address, LocalDate.now(), salary, getManagementRole(), null));
                default -> throw new IllegalArgumentException("Invalid Staff Type. Must be Receptionist, Nurse, Doctor, or Management.");
            }
            System.out.println("Staff added successfully");
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
        try {
            int roomNumber = getIntInput("Enter Room Number: ", 1, Integer.MAX_VALUE);
            System.out.println("Enter Room Description: ");
            String description = scanner.nextLine().trim();
            int price = getIntInput("Enter Room Price: ", 0, Integer.MAX_VALUE);
            
            hospital.addRoom(new Room(roomNumber, description, price));
            System.out.println("Room added successfully");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deleteRoom() {
        try {
            int roomNumber = getIntInput("Enter Room Number: ", 1, Integer.MAX_VALUE);
            Room room = hospital.getRoom(roomNumber);
            if (room == null) {
                System.out.println("Room not found");
                return;
            }
            hospital.removeRoom(room);
            Room.removeNumber(roomNumber);
            System.out.println("Room deleted successfully");
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
                        System.out.println(getPatient().getInfo());
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

    private static void doctor() {
        while (true) {
            int choice = doctorMenu();
            try {
                switch (choice) {
                    case 1 -> scheduleMenu();
                    case 2 -> viewPatients();
                    case 3 -> System.out.println(getPatient().getInfo());
                    case 4 -> patientRecordsMenu();
                    case 5 -> { return; }
                    default -> throw new IllegalArgumentException("Error at" + CLI.class.getName() + "doctorMenu() got invalid choice" + choice);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
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
        String staffType = user.getClass().getSimpleName();
        
        switch (staffType) {
            case "Receptionist" -> receptionist();
            case "Nurse" -> nurse();
            case "Doctor" -> doctor();
            case "Management" -> {
                String role = ((Management) user).getRole().toUpperCase(); // Convert to uppercase
                switch (role) {
                    case "GENERAL" -> generalManager();
                    case "HR" -> hrManager();
                    case "CEO" -> ceo();
                    default -> throw new IllegalArgumentException("Invalid Role: " + ((Management) user).getRole());
                }
            }
            default -> throw new IllegalArgumentException("Invalid Staff Member: " + staffType);
        }
    }

    private static void adminMenu() {
        while (true){
            System.out.println("\n===== Admin Menu =====");
            System.out.println("1. View Staff");
            System.out.println("2. Add Staff");
            System.out.println("3. Delete Staff");
            System.out.println("4. Add Management Staff");
            System.out.println("5. Exit");
            int choice = getChoice(5);
            switch (choice) {
                case 1:
                    viewStaff();
                    break;
                case 2:
                    addStaff();
                    break;
                case 3:
                    removeStaff();
                    break;
                case 4:
                    addManagementStaff();
                    break;
                case 5:
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
