package ui;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    public Dashboard() {
        setTitle("Hospital Management System - Dashboard");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1, 10, 10));

        JButton patientBtn = new JButton("Manage Patients");
        patientBtn.addActionListener(e -> {
            new PatientForm(this).setVisible(true);
            this.setVisible(false);
        });

        JButton doctorBtn = new JButton("Manage Doctors");
        doctorBtn.addActionListener(e -> {
            new DoctorForm(this).setVisible(true);
            this.setVisible(false);
        });

        JButton appointmentBtn = new JButton("Manage Appointments");
        appointmentBtn.addActionListener(e -> {
            new AppointmentForm(this).setVisible(true);
            this.setVisible(false);
        });

        add(patientBtn);
        add(doctorBtn);
        add(appointmentBtn);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Dashboard().setVisible(true);
        });
    }
}
