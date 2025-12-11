package ui;

import dao.PatientData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;

public class PatientForm extends JFrame {
    private JTextField nameField, ageField, genderField, contactField;
    private JList<String> patientJList;
    private DefaultListModel<String> patientListModel;
    private int selectedPatientId = -1;

    private Dashboard dashboard;  // reference to dashboard

    public PatientForm(Dashboard dashboard) {
        this.dashboard = dashboard;

        setTitle("Hospital Management - Patient Management");
        setSize(500, 480);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));

        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        formPanel.add(ageField);

        formPanel.add(new JLabel("Gender:"));
        genderField = new JTextField();
        formPanel.add(genderField);

        formPanel.add(new JLabel("Contact:"));
        contactField = new JTextField();
        formPanel.add(contactField);

        JButton addButton = new JButton("Add Patient");
        addButton.addActionListener(this::addPatient);
        formPanel.add(addButton);

        JButton updateButton = new JButton("Update Patient");
        updateButton.addActionListener(e -> updatePatient());
        formPanel.add(updateButton);

        JButton deleteButton = new JButton("Delete Patient");
        deleteButton.addActionListener(e -> deletePatient());
        formPanel.add(deleteButton);

        add(formPanel, BorderLayout.NORTH);

        patientListModel = new DefaultListModel<>();
        patientJList = new JList<>(patientListModel);
        patientJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        patientJList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                loadSelectedPatient();
            }
        });

        add(new JScrollPane(patientJList), BorderLayout.CENTER);

        // Back Button
        JButton backButton = new JButton("⬅ Back to Dashboard");
        backButton.addActionListener(e -> {
            this.dispose();
            dashboard.setVisible(true);
        });
        add(backButton, BorderLayout.SOUTH);

        refreshPatientList();
    }

    private void addPatient(ActionEvent e) {
        try {
            String name = nameField.getText().trim();
            int age = Integer.parseInt(ageField.getText().trim());
            String gender = genderField.getText().trim();
            String contact = contactField.getText().trim();

            if (name.isEmpty() || gender.isEmpty() || contact.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
                return;
            }

            PatientData patientData = new PatientData();
            patientData.addPatient(name, age, gender, contact);

            JOptionPane.showMessageDialog(this, "Patient added successfully!");

            clearForm();
            refreshPatientList();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Age must be a number.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error adding patient: " + ex.getMessage());
        }
    }

    private void loadSelectedPatient() {
        String selected = patientJList.getSelectedValue();
        if (selected == null) {
            clearForm();
            selectedPatientId = -1;
            return;
        }

        try {
            String[] parts = selected.split(",");
            selectedPatientId = Integer.parseInt(parts[0].split(":")[1].trim());
            String name = parts[1].split(":")[1].trim();
            int age = Integer.parseInt(parts[2].split(":")[1].trim());
            String gender = parts[3].split(":")[1].trim();
            String contact = parts[4].split(":")[1].trim();

            nameField.setText(name);
            ageField.setText(String.valueOf(age));
            genderField.setText(gender);
            contactField.setText(contact);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading selected patient.");
        }
    }

    private void updatePatient() {
        if (selectedPatientId == -1) {
            JOptionPane.showMessageDialog(this, "Select a patient first.");
            return;
        }
        try {
            PatientData patientData = new PatientData();
            String name = nameField.getText().trim();
            int age = Integer.parseInt(ageField.getText().trim());
            String gender = genderField.getText().trim();
            String contact = contactField.getText().trim();

            patientData.updatePatient(selectedPatientId, name, age, gender, contact);

            JOptionPane.showMessageDialog(this, "Updated successfully!");
            clearForm();
            refreshPatientList();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error updating: " + ex.getMessage());
        }
    }

    private void deletePatient() {
        if (selectedPatientId == -1) {
            JOptionPane.showMessageDialog(this, "Select a patient first.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Delete this patient?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        try {
            PatientData patientData = new PatientData();
            patientData.deletePatient(selectedPatientId);

            JOptionPane.showMessageDialog(this, "Deleted!");
            clearForm();
            refreshPatientList();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error deleting: " + ex.getMessage());
        }
    }

    private void clearForm() {
        nameField.setText("");
        ageField.setText("");
        genderField.setText("");
        contactField.setText("");
    }

    private void refreshPatientList() {
        try {
            PatientData patientData = new PatientData();
            List<String> patients = patientData.getAllPatients();
            patientListModel.clear();
            for (String p : patients) {
                patientListModel.addElement(p);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading patients.");
        }
    }
}
