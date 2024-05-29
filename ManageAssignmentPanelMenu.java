package CMPE232;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class ManageAssignmentPanelMenu extends JPanel {
    private AssignmentDAO assignmentDAO;

    public ManageAssignmentPanelMenu(JPanel mainPanel, CardLayout cardLayout, AssignmentDAO assignmentDAO) {
        this.assignmentDAO = assignmentDAO;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton addButton = new JButton("Add Assignment");
        JButton deleteButton = new JButton("Delete Assignment");
        JButton listButton = new JButton("List Assignments");
        JButton homeButton = new JButton("Home");

        addButton.addActionListener(e -> handleAddAssignment());
        deleteButton.addActionListener(e -> handleDeleteAssignment());
        listButton.addActionListener(e -> handleListAssignments());
        homeButton.addActionListener(e -> cardLayout.show(mainPanel, "StartPage"));

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(addButton, gbc);

        gbc.gridy = 1;
        add(deleteButton, gbc);

        gbc.gridy = 2;
        add(listButton, gbc);

        gbc.gridy = 3;
        add(homeButton, gbc);
    }

    private void handleAddAssignment() {
        JTextField trainerIDField = new JTextField();
        JTextField classIDField = new JTextField();

        Object[] message = {
            "Trainer ID:", trainerIDField,
            "Class ID:", classIDField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Assignment", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int trainerID = Integer.parseInt(trainerIDField.getText());
                int classID = Integer.parseInt(classIDField.getText());

                Assignment assignment = new Assignment(trainerID, classID);
                assignmentDAO.addAssignment(assignment);
                JOptionPane.showMessageDialog(this, "Assignment added successfully.");
            } catch (SQLException | NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Error adding assignment: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void handleDeleteAssignment() {
        JTextField trainerIDField = new JTextField();
        JTextField classIDField = new JTextField();

        Object[] message = {
            "Trainer ID:", trainerIDField,
            "Class ID:", classIDField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Delete Assignment", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int trainerID = Integer.parseInt(trainerIDField.getText());
                int classID = Integer.parseInt(classIDField.getText());

                assignmentDAO.deleteAssignment(trainerID, classID);
                JOptionPane.showMessageDialog(this, "Assignment deleted successfully.");
            } catch (SQLException | NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Error deleting assignment: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void handleListAssignments() {
        try {
            List<Assignment> assignments = assignmentDAO.getAllAssignments();
            StringBuilder sb = new StringBuilder("Assignments:\n");
            for (Assignment assignment : assignments) {
                sb.append("TrainerID: ").append(assignment.getTrainerID()).append(", ClassID: ").append(assignment.getClassID()).append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString(), "List Assignments", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error listing assignments: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

