package CMPE232;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class ManageEquipmentPanel extends JPanel {
    private EquipmentDAO equipmentDAO;

    public ManageEquipmentPanel(JPanel mainPanel, CardLayout cardLayout, EquipmentDAO equipmentDAO) {
        this.equipmentDAO = equipmentDAO;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton viewEquipmentButton = new JButton("View Equipment");
        JButton reportIssuesButton = new JButton("Report Issues with Equipment");
        JButton checkMaintenanceButton = new JButton("Check Maintenance Schedules");
        JButton homeButton = new JButton("Home");

        homeButton.addActionListener(e -> cardLayout.show(mainPanel, "StartPage"));

        viewEquipmentButton.addActionListener(e -> handleViewEquipment());
        reportIssuesButton.addActionListener(e -> handleReportIssues());
        checkMaintenanceButton.addActionListener(e -> handleCheckMaintenance());

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(viewEquipmentButton, gbc);

        gbc.gridy = 1;
        add(reportIssuesButton, gbc);

        gbc.gridy = 2;
        add(checkMaintenanceButton, gbc);

        gbc.gridy = 3;
        add(homeButton, gbc);
    }

    private void handleViewEquipment() {
        try {
            List<Equipment> equipmentList = equipmentDAO.getAllEquipment();
            StringBuilder sb = new StringBuilder();
            for (Equipment eq : equipmentList) {
                sb.append(eq.getEquipmentID()).append(": ").append(eq.getName()).append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString(), "Equipment", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleReportIssues() {
        int equipmentID = Integer.parseInt(JOptionPane.showInputDialog("Enter Equipment ID:"));
        String condition = JOptionPane.showInputDialog("Enter Condition:");

        try {
            Equipment equipment = equipmentDAO.getEquipment(equipmentID);
            if (equipment != null) {
                equipment.setCondition(condition);
                equipmentDAO.updateEquipment(equipment);
                JOptionPane.showMessageDialog(this, "Equipment issue reported successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Equipment not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleCheckMaintenance() {
        JOptionPane.showMessageDialog(this, "Check Maintenance Schedules not implemented yet.");
    }
}
