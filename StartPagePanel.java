package CMPE232;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPagePanel extends JPanel {
    public StartPagePanel(JPanel mainPanel, CardLayout cardLayout) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton manageAccountButton = new JButton("Manage Account");
        JButton manageClassesButton = new JButton("Manage Classes");
        JButton manageEquipmentButton = new JButton("Manage Equipment");
        JButton logoutButton = new JButton("Logout");

        manageAccountButton.addActionListener(e -> cardLayout.show(mainPanel, "ManageAccount"));
        manageClassesButton.addActionListener(e -> cardLayout.show(mainPanel, "ManageClasses"));
        manageEquipmentButton.addActionListener(e -> cardLayout.show(mainPanel, "ManageEquipment"));
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
        add(logoutButton, gbc);
    }
}

