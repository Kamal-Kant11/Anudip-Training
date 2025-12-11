package dao;

import database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorData {

    // Add a new doctor to the database
    public void addDoctor(String name, String specialty, String contact) throws SQLException {
        String sql = "INSERT INTO doctors (name, specialty, contact) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, specialty);
            stmt.setString(3, contact);
            stmt.executeUpdate();
        }
    }

    // Get all doctors as a list of formatted strings
    public List<String> getAllDoctors() throws SQLException {
        String sql = "SELECT * FROM doctors";
        List<String> doctors = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String doctor = "ID: " + rs.getInt("doctor_id") +
                        ", Name: " + rs.getString("name") +
                        ", Specialty: " + rs.getString("specialty") +
                        ", Contact: " + rs.getString("contact");
                doctors.add(doctor);
            }
        }
        return doctors;
    }
}
