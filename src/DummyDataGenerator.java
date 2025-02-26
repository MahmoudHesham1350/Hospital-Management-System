import HospitalSystem.*;
import HospitalSystem.Mangement.Management;
import HospitalSystem.Patient.Patient;
import HospitalSystem.Patient.PatientRecord;
import HospitalSystem.Room.Room;
import HospitalSystem.Staff.Doctor;
import HospitalSystem.Staff.Nurse;
import HospitalSystem.Staff.Receptionist;
import HospitalSystem.Staff.Schedule;
import java.time.LocalDate;
import java.util.Random;


/**
 * Utility class to generate dummy data for the hospital system
 */
public class DummyDataGenerator {
    private static final Random random = new Random();
    
    /**
     * Generates dummy data for the hospital system
     * @param hospital The hospital instance to populate with data
     */
    public static void generateDummyData(Hospital hospital) {
        System.out.println("Generating dummy data for the hospital system...");
        
        // Generate doctors
        generateDoctors(hospital);
        
        // Generate nurses
        generateNurses(hospital);
        
        // Generate receptionists
        generateReceptionists(hospital);
        
        // Generate management
        generateManagement(hospital);
        
        // Generate patients
        generatePatients(hospital);
        
        // Generate rooms
        generateRooms(hospital);
        
        // Allocate some rooms
        allocateRooms(hospital);
        
        System.out.println("Dummy data generated successfully!");
    }
    
    private static void generateDoctors(Hospital hospital) {
        Doctor[] doctors = {
            new Doctor(100100100, "Dr. John Smith", 5551234, "123 Medical Ave", LocalDate.now().minusYears(5), 150000, Doctor.SPECIALIZATION.CARDIOLOGIST, createSchedule()),
            new Doctor(100100101, "Dr. Sarah Johnson", 5552345, "456 Health St", LocalDate.now().minusYears(3), 145000, Doctor.SPECIALIZATION.NEUROLOGIST, createSchedule()),
            new Doctor(100100102, "Dr. Michael Chen", 5553456, "789 Doctor Rd", LocalDate.now().minusYears(7), 160000, Doctor.SPECIALIZATION.PEDIATRICIAN, createSchedule()),
            new Doctor(100100103, "Dr. Emily Wilson", 5554567, "101 Hospital Ln", LocalDate.now().minusYears(2), 130000, Doctor.SPECIALIZATION.OPHTHALMOLOGIST, createSchedule())
        };
        
        for (Doctor doctor : doctors) {
            hospital.addStaff(doctor);
        }
    }
    
    private static void generateNurses(Hospital hospital) {
        Nurse[] nurses = {
            new Nurse(200200200, "Nancy White", 5565678, "202 Nurse Ave", LocalDate.now().minusYears(3), 75000, "ICU Nurse", createSchedule()),
            new Nurse(200200201, "Robert Brown", 5566789, "303 Care St", LocalDate.now().minusYears(1), 70000, "ER Nurse", createSchedule()),
            new Nurse(200200202, "Lisa Taylor", 5567890, "404 Health Rd", LocalDate.now().minusYears(4), 78000, "Pediatric Nurse", createSchedule())
        };
        
        for (Nurse nurse : nurses) {
            hospital.addStaff(nurse);
        }
    }
    
    private static void generateReceptionists(Hospital hospital) {
        Receptionist[] receptionists = {
            new Receptionist(300300300, "James Anderson", 5578901, "505 Front Desk Ave", LocalDate.now().minusYears(2), 55000, createSchedule()),
            new Receptionist(300300301, "Jennifer Martin", 5579012, "606 Welcome St", LocalDate.now().minusMonths(8), 52000, createSchedule())
        };
        
        for (Receptionist receptionist : receptionists) {
            hospital.addStaff(receptionist);
        }
    }
    
    private static void generateManagement(Hospital hospital) {
        Management[] management = {
            new Management(400400400, "William Davis", 5580123, "707 Executive Ave", LocalDate.now().minusYears(8), 200000, "CEO", createSchedule()),
            new Management(400400401, "Maria Rodriguez", 5581234, "808 Manager St", LocalDate.now().minusYears(4), 120000, "HR", createSchedule()),
            new Management(400400402, "Thomas Wilson", 5582345, "909 Admin Rd", LocalDate.now().minusYears(5), 130000, "GENERAL", createSchedule())
        };
        
        for (Management manager : management) {
            hospital.addStaff(manager);
        }
    }
    
    private static void generatePatients(Hospital hospital) {
        Patient[] patients = {
            new Patient(500500500, "Alice Johnson", 5593456, "111 Patient Ave", 35),
            new Patient(500500501, "Bob Miller", 5594567, "222 Sick St", 62),
            new Patient(500500502, "Carol Williams", 5595678, "333 Recovery Rd", 28),
            new Patient(500500503, "David Garcia", 5596789, "444 Treatment Ln", 45),
            new Patient(500500504, "Eva Martinez", 5597890, "555 Health Blvd", 8),
            new Patient(500500505, "Frank Thompson", 5598901, "666 Care Ct", 73),
            new Patient(500500506, "Grace Lee", 5599012, "777 Healing Way", 31)
        };
        
        for (Patient patient : patients) {
            hospital.addPatient(patient);
            
            // Add some records to patients
            if (random.nextBoolean()) {
                Doctor doctor = (Doctor) hospital.getStaff(100100100 + random.nextInt(4));
                addPatientRecords(patient, doctor);
            }
        }
    }
    
    private static void addPatientRecords(Patient patient, Doctor doctor) {
        String[] diagnoses = {
            "Common Cold", "Influenza", "Hypertension", "Diabetes Type 2", 
            "Asthma", "Bronchitis", "Migraine", "Gastritis"
        };
        
        String[] prescriptions = {
            "Acetaminophen 500mg twice daily", 
            "Amoxicillin 250mg three times daily for 7 days",
            "Lisinopril 10mg once daily",
            "Metformin 500mg twice daily",
            "Albuterol inhaler as needed",
            "Ibuprofen 400mg every 6 hours as needed for pain"
        };
        
        String[] notes = {
            "Patient responding well to treatment.",
            "Follow-up appointment in two weeks.",
            "Recommend increasing fluid intake and rest.",
            "Patient should monitor blood pressure daily.",
            "Consider referral to specialist if symptoms persist.",
            "Patient education provided on medication use."
        };
        
        // Add 1-3 records
        int recordCount = 1 + random.nextInt(3);
        for (int i = 0; i < recordCount; i++) {
            LocalDate recordDate = LocalDate.now().minusDays(i * 7 + random.nextInt(7));
            
            PatientRecord record = new PatientRecord(
                recordDate,
                diagnoses[random.nextInt(diagnoses.length)],
                prescriptions[random.nextInt(prescriptions.length)],
                notes[random.nextInt(notes.length)],
                doctor,
                null  
            );
            
            patient.addRecord(record);
        }
    }
    
    private static void generateRooms(Hospital hospital) {
        String[] roomDescriptions = {
            "Standard Single Room", 
            "Deluxe Single Room",
            "Double Room",
            "ICU Room",
            "Maternity Room",
            "Pediatric Room",
            "Surgical Recovery Room"
        };
        
        int[] roomPrices = {200, 350, 150, 500, 300, 250, 400};
        
        for (int i = 101; i <= 120; i++) {
            int descIndex = random.nextInt(roomDescriptions.length);
            Room room = new Room(i, roomDescriptions[descIndex], roomPrices[descIndex]);
            hospital.addRoom(room);
        }
    }
    
    private static void allocateRooms(Hospital hospital) {
        // Allocate rooms to about half of the patients
        for (Patient patient : hospital.getPatients()) {
            if (random.nextBoolean()) {
                // Find an unallocated room
                for (Room room : hospital.getRooms()) {
                    if (!room.getAllocationStatus()) {
                        hospital.allocateRoom(patient, room);
                        break;
                    }
                }
            }
        }
    }
    
private static Schedule createSchedule() {
    Schedule schedule = new Schedule();
    String[] tasks = {
        "Patient rounds", "Team meeting", "Consultation hours",
        "Surgery", "Administrative work", "Training session",
        "Research", "Patient follow-ups", "Emergency on-call"
    };
    
    // Add 0-3 tasks for each day of the week
    for (Schedule.Day day : Schedule.Day.values()) {
        int taskCount = random.nextInt(4); // 0 to 3 tasks per day
        for (int i = 0; i < taskCount; i++) {
            String task = tasks[random.nextInt(tasks.length)];
            schedule.addSchedule(task, day);
        }
    }
    
    return schedule;
}
}
