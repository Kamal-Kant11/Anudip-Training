package ui;

import dao.DoctorData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;

public class DoctorForm extends JFrame {
    private JTextField nameField, specialtyField, contactField;
    private JTextArea doctorListArea;
    private DoctorData doctorData;

    private Dashboard dashboard;

    public DoctorForm(Dashboard dashboard) {
        this.dashboard = dashboard;
        doctorData = new DoctorData();

        setTitle("Hospital Management - Doctor Management");
        setSize(500, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Specialty:"));
        specialtyField = new JTextField();
        formPanel.add(specialtyField);

        formPanel.add(new JLabel("Contact:"));
        contactField = new JTextField();
        formPanel.add(contactField);

        JButton addButton = new JButton("Add Doctor");
        addButton.addActionListener(this::addDoctor);
        formPanel.add(addButton);

        add(formPanel, BorderLayout.NORTH);

        doctorListArea = new JTextArea();
        doctorListArea.setEditable(false);

        add(new JScrollPane(doctorListArea), BorderLayout.CENTER);

        JButton backBtn = new JButton("⬅ Back to Dashboard");
        backBtn.addActionListener(e -> {
            this.dispose();
            dashboard.setVisible(true);
        });
        add(backBtn, BorderLayout.SOUTH);

        refreshDoctorList();
    }

    private void addDoctor(ActionEvent e) {
        try {
            String name = nameField.getText().trim();
            String specialty = specialtyField.getText().trim();
            String contact = contactField.getText().trim();

            if (name.isEmpty() || specialty.isEmpty() || contact.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fill all fields.");
                return;
            }

            doctorData.addDoctor(name, specialty, contact);
            JOptionPane.showMessageDialog(this, "Doctor added!");

            nameField.setText("");
            specialtyField.setText("");
            contactField.setText("");

            refreshDoctorList();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void refreshDoctorList() {
        try {
            List<String> list = doctorData.getAllDoctors();
            doctorListArea.setText("");

            for (String d : list) {
                doctorListArea.append(d + "\n");
            }
        } catch (Exception e) {
            doctorListArea.setText("Error loading doctor list.");
        }
    }
}
