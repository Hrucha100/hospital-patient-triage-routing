# Hospital Patient Triage & Routing System

A console-based Hospital Patient Triage & Routing System developed using
Core Java, JDBC, and MySQL.

The system reads pending patient records from the hospital intake table,
validates the patient information, determines the appropriate care unit,
and routes the patient to either Critical Care or General Care.

## Technologies Used

- Java
- Core Java
- JDBC
- MySQL
- SQL
- Maven
- Eclipse IDE

## Features

- Fetch pending patient records
- Validate patient information
- Route patients based on triage rules
- Critical Care routing
- General Care routing
- Duplicate transfer prevention
- JDBC PreparedStatement
- Database transactions
- Commit and rollback handling
- Processing summary

## Routing Rules

A patient is routed to Critical Care when:

- Condition status is `Critical`
- OR admission type is `Emergency` and triage score is `7` or higher
- OR condition status is `Moderate` and triage score is `8` or higher

Otherwise, the patient is routed to General Care.

## Database Tables

The project uses the following tables:

### 1. hospital_patient_intake

Stores the original patient intake records.

### 2. critical_care_patients

Stores patients routed to Critical Care.

### 3. general_care_patients

Stores patients routed to General Care.

## Project Structure

```text
src/main/java/com/kiranacademy/hospital/

├── DBConnection.java
├── HospitalRoutingApp.java
├── Patient.java
├── PatientDAO.java
├── PatientRoutingService.java
├── PatientValidator.java
└── ProcessingSummary.java
