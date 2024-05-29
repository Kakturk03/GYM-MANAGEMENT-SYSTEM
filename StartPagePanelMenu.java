package CMPE232;

import javax.swing.*;
import java.awt.*;

public class StartPagePanelMenu extends JPanel {
    public StartPagePanelMenu(JPanel mainPanel, CardLayout cardLayout) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton manageAccountButton = new JButton("Manage Account");
        JButton manageClassesButton = new JButton("Manage Classes");
        JButton manageEquipmentButton = new JButton("Manage Equipment");
        JButton managePaymentsButton = new JButton("Manage Payments");
        JButton manageEnrollmentButton = new JButton("Manage Enrollment");
        JButton manageAssignmentButton = new JButton("Manage Assignment");
        JButton logoutButton = new JButton("Logout");

        manageAccountButton.addActionListener(e -> cardLayout.show(mainPanel, "ManageAccount"));
        manageClassesButton.addActionListener(e -> cardLayout.show(mainPanel, "ManageClasses"));
        manageEquipmentButton.addActionListener(e -> cardLayout.show(mainPanel, "ManageEquipment"));
        managePaymentsButton.addActionListener(e -> cardLayout.show(mainPanel, "ManagePayments"));
        manageEnrollmentButton.addActionListener(e -> cardLayout.show(mainPanel, "ManageEnrollment"));
        manageAssignmentButton.addActionListener(e -> cardLayout.show(mainPanel, "ManageAssignment"));
        logoutButton.addActionListener(e -> cardLayout.show(mainPanel, "Login"));

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(manageAccountButton, gbc);

        gbc.gridy = 1;
        add(manageClassesButton, gbc);

        gbc.gridy = 2;
        add(manageEquipmentButton, gbc);

        gbc.gridy = 3;
        add(managePaymentsButton, gbc);

        gbc.gridy = 4;
        add(manageEnrollmentButton, gbc);

        gbc.gridy = 5;
        add(manageAssignmentButton, gbc);

        gbc.gridy = 6;
        add(logoutButton, gbc);
    }
}

