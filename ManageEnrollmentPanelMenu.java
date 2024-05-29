package CMPE232;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class ManageEnrollmentPanelMenu extends JPanel {
    private EnrollmentDAO enrollmentDAO;

    public ManageEnrollmentPanelMenu(JPanel mainPanel, CardLayout cardLayout, EnrollmentDAO enrollmentDAO) {
        this.enrollmentDAO = enrollmentDAO;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton addButton = new JButton("Add Enrollment");
        JButton deleteButton = new JButton("Delete Enrollment");
        JButton listButton = new JButton("List Enrollments");
        JButton homeButton = new JButton("Home");

        addButton.addActionListener(e -> handleAddEnrollment());
        deleteButton.addActionListener(e -> handleDeleteEnrollment());
        listButton.addActionListener(e -> handleListEnrollments());
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

    private void handleAddEnrollment() {
        JTextField memberIDField = new JTextField();
        JTextField classIDField = new JTextField();

        Object[] message = {
            "Member ID:", memberIDField,
            "Class ID:", classIDField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Enrollment", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int memberID = Integer.parseInt(memberIDField.getText());
                int classID = Integer.parseInt(classIDField.getText());

                Enrollment enrollment = new Enrollment(memberID, classID);
                enrollmentDAO.addEnrollment(enrollment);
                JOptionPane.showMessageDialog(this, "Enrollment added successfully.");
            } catch (SQLException | NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Error adding enrollment: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void handleDeleteEnrollment() {
        JTextField memberIDField = new JTextField();
        JTextField classIDField = new JTextField();

        Object[] message = {
            "Member ID:", memberIDField,
            "Class ID:", classIDField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Delete Enrollment", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int memberID = Integer.parseInt(memberIDField.getText());
                int classID = Integer.parseInt(classIDField.getText());

                enrollmentDAO.deleteEnrollment(memberID, classID);
                JOptionPane.showMessageDialog(this, "Enrollment deleted successfully.");
            } catch (SQLException | NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Error deleting enrollment: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void handleListEnrollments() {
        try {
            List<Enrollment> enrollments = enrollmentDAO.getAllEnrollments();
            StringBuilder sb = new StringBuilder("Enrollments:\n");
            for (Enrollment enrollment : enrollments) {
                sb.append("MemberID: ").append(enrollment.getMemberID()).append(", ClassID: ").append(enrollment.getClassID()).append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString(), "List Enrollments", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error listing enrollments: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

