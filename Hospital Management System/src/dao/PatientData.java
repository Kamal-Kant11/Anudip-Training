package dao;

import database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientData {

    // Add a new patient to the database
    public void addPatient(String name, int age, String gender, String contact) throws SQLException {
        String sql = "INSERT INTO patients (name, age, gender, contact) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.setString(3, gender);
            stmt.setString(4, contact);
            stmt.executeUpdate();
        }
    }

    public void updatePatient(int patientId, String name, int age, String gender, String contact) throws SQLException {
        String sql = "UPDATE patients SET name = ?, age = ?, gender = ?, contact = ? WHERE patient_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.setString(3, gender);
            stmt.setString(4, contact);
            stmt.setInt(5, patientId);
            stmt.executeUpdate();
        }
    }

    public void deletePatient(int patientId) throws SQLException {
        String sql = "DELETE FROM patients WHERE patient_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, patientId);
            stmt.executeUpdate();
        }
    }


    // Get all patients as a list of formatted strings
    public List<String> getAllPatients() throws SQLException {
        String sql = "SELECT * FROM patients";
        List<String> patients = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String patient = "ID: " + rs.getInt("patient_id") +
                        ", Name: " + rs.getString("name") +
                        ", Age: " + rs.getInt("age") +
                        ", Gender: " + rs.getString("gender") +
                        ", Contact: " + rs.getString("contact");
                patients.add(patient);
            }
        }
        return patients;
    }
}
