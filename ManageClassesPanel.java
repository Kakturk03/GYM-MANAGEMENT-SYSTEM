package CMPE232;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class ManageClassesPanel extends JPanel {
    private ClassDAO classDAO;
    private EnrollmentDAO enrollmentDAO;
    private AssignmentDAO assignmentDAO;

    public ManageClassesPanel(JPanel mainPanel, CardLayout cardLayout, ClassDAO classDAO, EnrollmentDAO enrollmentDAO, AssignmentDAO assignmentDAO) {
        this.classDAO = classDAO;
        this.enrollmentDAO = enrollmentDAO;
        this.assignmentDAO = assignmentDAO;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton viewClassesButton = new JButton("View Classes");
        JButton enrollClassesButton = new JButton("Enroll in Classes");
        JButton changePasswordButton = new JButton("Change Password");
        JButton homeButton = new JButton("Home");

        homeButton.addActionListener(e -> cardLayout.show(mainPanel, "StartPage"));

        viewClassesButton.addActionListener(e -> handleViewClasses());
        enrollClassesButton.addActionListener(e -> handleEnrollInClasses());

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(viewClassesButton, gbc);

        gbc.gridy = 1;
        add(enrollClassesButton, gbc);

        gbc.gridy = 2;
        add(changePasswordButton, gbc);

        gbc.gridy = 3;
        add(homeButton, gbc);
    }

    private void handleViewClasses() {
        try {
            List<Class> classes = classDAO.getAllClasses();
            StringBuilder sb = new StringBuilder();
            for (Class cls : classes) {
                sb.append(cls.getClassID()).append(": ").append(cls.getName()).append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString(), "Classes", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleEnrollInClasses() {
        int memberID = Integer.parseInt(JOptionPane.showInputDialog("Enter Member ID:"));
        int classID = Integer.parseInt(JOptionPane.showInputDialog("Enter Class ID:"));

        try {
            Enrollment enrollment = new Enrollment(memberID, classID);
            enrollmentDAO.addEnrollment(enrollment);
            JOptionPane.showMessageDialog(this, "Enrolled in class successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
