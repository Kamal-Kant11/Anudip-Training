package ui;

import dao.AppointmentData;
import dao.DoctorData;
import dao.PatientData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

public class AppointmentForm extends JFrame {

    private JComboBox<String> patientCombo, doctorCombo;
    private JTextField dateField, timeField, statusField;
    private JTextArea appointmentListArea;

    private AppointmentData appointmentData;
    private PatientData patientData;
    private DoctorData doctorData;

    private Dashboard dashboard;

    public AppointmentForm(Dashboard dashboard) {
        this.dashboard = dashboard;

        appointmentData = new AppointmentData();
        patientData = new PatientData();
        doctorData = new DoctorData();

        setTitle("Hospital Management - Appointment Management");
        setSize(600, 450);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));

        formPanel.add(new JLabel("Patient:"));
        patientCombo = new JComboBox<>();
        formPanel.add(patientCombo);

        formPanel.add(new JLabel("Doctor:"));
        doctorCombo = new JComboBox<>();
        formPanel.add(doctorCombo);

        formPanel.add(new JLabel("Date (YYYY-MM-DD):"));
        dateField = new JTextField();
        formPanel.add(dateField);

        formPanel.add(new JLabel("Time (HH:MM:SS):"));
        timeField = new JTextField();
        formPanel.add(timeField);

        formPanel.add(new JLabel("Status:"));
        statusField = new JTextField("Scheduled");
        formPanel.add(statusField);

        JButton addButton = new JButton("Add Appointment");
        addButton.addActionListener(this::addAppointment);
        formPanel.add(addButton);

        add(formPanel, BorderLayout.NORTH);

        appointmentListArea = new JTextArea();
        appointmentListArea.setEditable(false);
        add(new JScrollPane(appointmentListArea), BorderLayout.CENTER);

        JButton backButton = new JButton("⬅ Back to Dashboard");
        backButton.addActionListener(e -> {
            this.dispose();
            dashboard.setVisible(true);
        });
        add(backButton, BorderLayout.SOUTH);

        loadPatients();
        loadDoctors();
        refreshAppointmentList();
    }

    private void loadPatients() {
        try {
            patientCombo.removeAllItems();
            List<String> list = patientData.getAllPatients();

            for (String p : list) {
                String id = p.split(",")[0].split(":")[1].trim();
                String name = p.split(",")[1].split(":")[1].trim();
                patientCombo.addItem(id + " - " + name);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading patients:\n" + e.getMessage());
            e.printStackTrace();
        }
    }

    private void loadDoctors() {
        try {
            doctorCombo.removeAllItems();
            List<String> list = doctorData.getAllDoctors();

            for (String d : list) {
                String id = d.split(",")[0].split(":")[1].trim();
                String name = d.split(",")[1].split(":")[1].trim();
                doctorCombo.addItem(id + " - " + name);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading doctors:\n" + e.getMessage());
            e.printStackTrace();
        }
    }

    private void addAppointment(ActionEvent e) {
        try {
            if (patientCombo.getSelectedItem() == null || doctorCombo.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Please select a patient and a doctor.");
                return;
            }

            int patientId = Integer.parseInt(patientCombo.getSelectedItem().toString().split("-")[0].trim());
            int doctorId = Integer.parseInt(doctorCombo.getSelectedItem().toString().split("-")[0].trim());

            String dateStr = dateField.getText().trim();
            String timeStr = timeField.getText().trim();
            String status = statusField.getText().trim();

            if (dateStr.isEmpty() || timeStr.isEmpty() || status.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
                return;
            }

            Date date = Date.valueOf(dateStr);  // expects YYYY-MM-DD
            Time time = Time.valueOf(timeStr);  // expects HH:MM:SS

            appointmentData.addAppointment(patientId, doctorId, date, time, status);
            JOptionPane.showMessageDialog(this, "Appointment added successfully!");

            clearForm();
            refreshAppointmentList();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Date or Time format is invalid.\nDate: YYYY-MM-DD\nTime: HH:MM:SS");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error adding appointment:\n" + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void clearForm() {
        dateField.setText("");
        timeField.setText("");
        statusField.setText("Scheduled");
    }

    private void refreshAppointmentList() {
        try {
            List<String> list = appointmentData.getAllAppointments();
            appointmentListArea.setText("");
            for (String a : list) {
                appointmentListArea.append(a + "\n");
            }
        } catch (SQLException ex) {
            appointmentListArea.setText("Error loading appointments:\n" + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
