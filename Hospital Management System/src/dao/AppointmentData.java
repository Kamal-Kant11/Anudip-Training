package dao;

import database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentData {

    // Add a new appointment
    public void addAppointment(int patientId, int doctorId, Date date, Time time, String status) throws SQLException {
        String sql = "INSERT INTO appointments (patient_id, doctor_id, appointment_date, appointment_time, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, patientId);
            stmt.setInt(2, doctorId);
            stmt.setDate(3, date);
            stmt.setTime(4, time);
            stmt.setString(5, status);
            stmt.executeUpdate();
        }
    }

    // Get all appointments as a list of formatted strings
    public List<String> getAllAppointments() throws SQLException {
        String sql = "SELECT a.appointment_id, p.name AS patient_name, d.name AS doctor_name, a.appointment_date, a.appointment_time, a.status " +
                "FROM appointments a " +
                "JOIN patients p ON a.patient_id = p.patient_id " +
                "JOIN doctors d ON a.doctor_id = d.doctor_id";
        List<String> appointments = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String appointment = "ID: " + rs.getInt("appointment_id") +
                        ", Patient: " + rs.getString("patient_name") +
                        ", Doctor: " + rs.getString("doctor_name") +
                        ", Date: " + rs.getDate("appointment_date") +
                        ", Time: " + rs.getTime("appointment_time") +
                        ", Status: " + rs.getString("status");
                appointments.add(appointment);
            }
        }
        return appointments;
    }
}
