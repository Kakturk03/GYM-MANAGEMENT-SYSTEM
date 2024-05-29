package CMPE232;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Main2 {
    public static void main(String[] args) throws SQLException {
    	DatabaseConnection connection = new DatabaseConnection();
		connection.getConnection();
    	SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gym Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);

            CardLayout cardLayout = new CardLayout();
            JPanel mainPanel = new JPanel(cardLayout);

            MemberDAO memberDAO = new MemberDAO();
            TrainerDAO trainerDAO = new TrainerDAO();
            ClassDAO classDAO = new ClassDAO();
            EquipmentDAO equipmentDAO = new EquipmentDAO();
            PaymentDAO paymentDAO = new PaymentDAO();
            EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
            AssignmentDAO assignmentDAO = new AssignmentDAO();

            LoginPanel loginPanel = new LoginPanel(mainPanel, cardLayout);
            StartPagePanel startPagePanel = new StartPagePanel(mainPanel, cardLayout);
            ManageAccountPanel manageAccountPanel = new ManageAccountPanel(mainPanel, cardLayout, memberDAO);
            ManageClassesPanel manageClassesPanel = new ManageClassesPanel(mainPanel, cardLayout, classDAO, enrollmentDAO, assignmentDAO);
            ManageEquipmentPanel manageEquipmentPanel = new ManageEquipmentPanel(mainPanel, cardLayout, equipmentDAO);

            mainPanel.add(loginPanel, "Login");
            mainPanel.add(startPagePanel, "StartPage");
            mainPanel.add(manageAccountPanel, "ManageAccount");
            mainPanel.add(manageClassesPanel, "ManageClasses");
            mainPanel.add(manageEquipmentPanel, "ManageEquipment");

            cardLayout.show(mainPanel, "Login");

            frame.add(mainPanel);
            frame.setVisible(true);
        });
    }
}

