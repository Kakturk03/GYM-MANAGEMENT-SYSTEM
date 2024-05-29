package CMPE232;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class ManageEquipmentPanelMenu extends JPanel {
    private EquipmentDAO equipmentDAO;

    public ManageEquipmentPanelMenu(JPanel mainPanel, CardLayout cardLayout, EquipmentDAO equipmentDAO) {
        this.equipmentDAO = equipmentDAO;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton addButton = new JButton("Add Equipment");
        JButton updateButton = new JButton("Update Equipment");
        JButton deleteButton = new JButton("Delete Equipment");
        JButton listButton = new JButton("List Equipment");
        JButton homeButton = new JButton("Home");

        addButton.addActionListener(e -> handleAddEquipment());
        updateButton.addActionListener(e -> handleUpdateEquipment());
        deleteButton.addActionListener(e -> handleDeleteEquipment());
        listButton.addActionListener(e -> handleListEquipment());
        homeButton.addActionListener(e -> cardLayout.show(mainPanel, "StartPage"));

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(addButton, gbc);

        gbc.gridy = 1;
        add(updateButton, gbc);

        gbc.gridy = 2;
        add(deleteButton, gbc);

        gbc.gridy = 3;
        add(listButton, gbc);

        gbc.gridy = 4;
        add(homeButton, gbc);
    }

    private void handleAddEquipment() {
        JTextField equipmentIDField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField typeField = new JTextField();
        JTextField conditionField = new JTextField();
        JCheckBox availabilityCheckBox = new JCheckBox();

        Object[] message = {
            "Equipment ID:", equipmentIDField,
            "Name:", nameField,
            "Type:", typeField,
            "Condition:", conditionField,
            "Availability:", availabilityCheckBox
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Equipment", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int equipmentID = Integer.parseInt(equipmentIDField.getText());
                String name = nameField.getText();
                String type = typeField.getText();
                String condition = conditionField.getText();
                boolean availability = availabilityCheckBox.isSelected();

                Equipment equipment = new Equipment(equipmentID, name, type, condition, availability);
                equipmentDAO.addEquipment(equipment);
                JOptionPane.showMessageDialog(this, "Equipment added successfully.");
            } catch (SQLException | NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Error adding equipment: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void handleUpdateEquipment() {
        String input = JOptionPane.showInputDialog(this, "Enter Equipment ID:", "Update Equipment", JOptionPane.QUESTION_MESSAGE);
        if (input != null) {
            try {
                int equipmentID = Integer.parseInt(input);
                Equipment equipment = equipmentDAO.getEquipment(equipmentID);
                if (equipment == null) {
                    JOptionPane.showMessageDialog(this, "Equipment not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                JTextField nameField = new JTextField(equipment.getName());
                JTextField typeField = new JTextField(equipment.getType());
                JTextField conditionField = new JTextField(equipment.getCondition());
                JCheckBox availabilityCheckBox = new JCheckBox();
                availabilityCheckBox.setSelected(equipment.isAvailability());

                Object[] message = {
                    "Name:", nameField,
                    "Type:", typeField,
                    "Condition:", conditionField,
                    "Availability:", availabilityCheckBox
                };

                int option = JOptionPane.showConfirmDialog(this, message, "Update Equipment", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    equipment.setName(nameField.getText());
                    equipment.setType(typeField.getText());
                    equipment.setCondition(conditionField.getText());
                    equipment.setAvailability(availabilityCheckBox.isSelected());

                    equipmentDAO.updateEquipment(equipment);
                    JOptionPane.showMessageDialog(this, "Equipment updated successfully.");
                }
            } catch (SQLException | NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Error updating equipment: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void handleDeleteEquipment() {
        String input = JOptionPane.showInputDialog(this, "Enter Equipment ID:", "Delete Equipment", JOptionPane.QUESTION_MESSAGE);
        if (input != null) {
            try {
                int equipmentID = Integer.parseInt(input);
                equipmentDAO.deleteEquipment(equipmentID);
                JOptionPane.showMessageDialog(this, "Equipment deleted successfully.");
            } catch (SQLException | NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Error deleting equipment: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void handleListEquipment() {
        try {
            List<Equipment> equipmentList = equipmentDAO.getAllEquipment();
            StringBuilder sb = new StringBuilder("Equipment:\n");
            for (Equipment equipment : equipmentList) {
                sb.append(equipment.getEquipmentID()).append(": ").append(equipment.getName()).append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString(), "List Equipment", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error listing equipment: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

