package CMPE232;
import javax.swing.*;
import java.awt.*;

public class MenuMain {
    public static void main(String[] args) {
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

            LoginPanelMenu loginPanel = new LoginPanelMenu(mainPanel, cardLayout);
            StartPagePanelMenu startPagePanel = new StartPagePanelMenu(mainPanel, cardLayout);
            ManageAccountPanelMenu manageAccountPanel = new ManageAccountPanelMenu(mainPanel, cardLayout, memberDAO);
            ManageClassesPanelMenu manageClassesPanel = new ManageClassesPanelMenu(mainPanel, cardLayout, classDAO, enrollmentDAO, assignmentDAO);
            ManageEquipmentPanelMenu manageEquipmentPanel = new ManageEquipmentPanelMenu(mainPanel, cardLayout, equipmentDAO);
            ManagePaymentPanelMenu managePaymentPanel = new ManagePaymentPanelMenu(mainPanel, cardLayout, paymentDAO);
            ManageEnrollmentPanelMenu manageEnrollmentPanel = new ManageEnrollmentPanelMenu(mainPanel, cardLayout, enrollmentDAO);
            ManageAssignmentPanelMenu manageAssignmentPanel = new ManageAssignmentPanelMenu(mainPanel, cardLayout, assignmentDAO);

            mainPanel.add(loginPanel, "Login");
            mainPanel.add(startPagePanel, "StartPage");
            mainPanel.add(manageAccountPanel, "ManageAccount");
            mainPanel.add(manageClassesPanel, "ManageClasses");
            mainPanel.add(manageEquipmentPanel, "ManageEquipment");
            mainPanel.add(managePaymentPanel, "ManagePayments");
            mainPanel.add(manageEnrollmentPanel, "ManageEnrollment");
            mainPanel.add(manageAssignmentPanel, "ManageAssignment");

            cardLayout.show(mainPanel, "Login");

            frame.add(mainPanel);
            frame.setVisible(true);
        });
    }
}
