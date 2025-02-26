# Hospital Management System

## Project Overview

This Hospital Management System is a Java-based console application designed to manage various aspects of hospital operations.<br>
The system provides different interfaces for different staff roles and allows for management of
- patients, rooms, patientRecords, and staff scheduling.

## System Architecture

The application follows an object-oriented design approach with the following key components:

### Core Components
- **Hospital**: The central class that manages all entities (staff, patients, rooms).
- **CLI (Command Line Interface)**: Handles user interaction through a text-based menu system.

### User Management
- **User**: Base class for all system users.
- **Admin**: Administrator with system-wide privileges.
- **Staff**: Employees of the hospital with different roles and permissions.

### Staff Types
- **Doctor**: Medical professional with specialization.
- **Nurse**: Staff responsible for patient care.
- **Receptionist**: Manages patient admissions and room allocations.
- **Management**: Administrative staff with different management roles.

### Patient Management
- **Patient**: Stores patient information and medical patientRecords.
- **Record**: Contains medical diagnosis, prescriptions, and notes.

### Room Management
- **Room**: Represents hospital rooms with allocation status.
- **RoomAllocation**: Links rooms to patients with date tracking.

### Scheduling
- **Schedule**: Manages weekly tasks for staff members.

## Features

### For All Staff
- Personal schedule management
- Task management by day of the week

### For Admin
- Staff management (add, delete, view)
- Management staff creation

### For Receptionist
- Patient registration and discharge
- Room allocation and discharge
- Invoice generation
- View all patients and rooms

### For Nurse
- View patients
- View allocated rooms

### For Doctor
- View patients
- Create and manage patient medical patientRecords

### For Management
Based on role (General Manager, HR Manager, CEO):
- Staff management
- Room management
- Overall hospital resource management

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 17 or higher
- Java IDE (IntelliJ IDEA, Eclipse, etc.) or command line compiler

### Running the Application
1. Compile all Java files
2. Run the Main class
3. Use the login credentials:
   - Admin: username `admin`, password `admin`
   - Staff: Use their Social Security Number (SSN)

## System Navigation

### Main Menu
- Admin access
- Staff access
- Exit

### Default Login Credentials
- Admin: Username - `admin`, Password - `admin`
- Staff: Use SSN (9-digit number)

## System Workflow

1. **Login**: Users log in as either admin or staff member
2. **Role-based Menu**: Different menu options appear based on user role
3. **Functionality**: Users can access features related to their role
4. **Data Management**: Changes are maintained during program runtime

## Code Structure

- `src/Main.java` - Application entry point
- `src/HospitalSystem/` - Core system package
  - `CLI.java` - Command-line interface and menu handling
  - `Hospital.java` - Central management of all hospital entities
- `src/HospitalSystem/User/` - User classes
- `src/HospitalSystem/Staff/` - Staff-related classes
- `src/HospitalSystem/Patient/` - Patient-related classes
- `src/HospitalSystem/Room/` - Room management
- `src/HospitalSystem/Mangement/` - Management staff

## Future Enhancements

- Database integration for persistent storage
- GUI implementation for better user experience
- Better login system
- Implementing tests
- Staff shift management and scheduling
