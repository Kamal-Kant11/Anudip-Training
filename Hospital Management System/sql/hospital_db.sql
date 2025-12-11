-- Create database
CREATE DATABASE IF NOT EXISTS hospital_db;
USE hospital_db;

-- Drop tables if they exist (for clean setup)
DROP TABLE IF EXISTS appointments;
DROP TABLE IF EXISTS patients;
DROP TABLE IF EXISTS doctors;

-- Create patients table
CREATE TABLE patients (
    patient_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    gender VARCHAR(10) NOT NULL,
    contact VARCHAR(20) NOT NULL
);

-- Create doctors table
CREATE TABLE doctors (
    doctor_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    specialty VARCHAR(100) NOT NULL,
    contact VARCHAR(20) NOT NULL
);

-- Create appointments table
CREATE TABLE appointments (
    appointment_id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT NOT NULL,
    doctor_id INT NOT NULL,
    appointment_date DATE NOT NULL,
    appointment_time TIME NOT NULL,
    status VARCHAR(50) NOT NULL,

    CONSTRAINT fk_patient FOREIGN KEY (patient_id) REFERENCES patients(patient_id) ON DELETE CASCADE,
    CONSTRAINT fk_doctor FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id) ON DELETE CASCADE
);

-- Insert sample patients
INSERT INTO patients (name, age, gender, contact) VALUES
('John Doe', 35, 'Male', '555-1111'),
('Jane Smith', 28, 'Female', '555-2222'),
('Michael Brown', 40, 'Male', '555-3333'),
('Emily Davis', 30, 'Female', '555-4444'),
('Anurag Kumar', 60, 'Male', '555-5555'),
('Divyanshu Singh', 20, 'Male', '555-4565');

-- Insert sample doctors
INSERT INTO doctors (name, specialty, contact) VALUES
('Dr. Alice Johnson', 'Cardiology', '555-1234'),
('Dr. Bob Smith', 'Neurology', '555-5678'),
('Dr. Carol Lee', 'Pediatrics', '555-8765'),
('Dr. David Kim', 'Orthopedics', '555-4321');

-- Insert sample appointments
INSERT INTO appointments (patient_id, doctor_id, appointment_date, appointment_time, status) VALUES
(1, 1, '2025-12-15', '10:00:00', 'Scheduled'),
(2, 2, '2025-12-16', '11:30:00', 'Scheduled'),
(3, 3, '2025-12-17', '14:00:00', 'Scheduled'),
(4, 1, '2025-12-18', '09:00:00', 'Scheduled');
