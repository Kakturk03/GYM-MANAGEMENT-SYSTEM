package CMPE232;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class ManageAccountPanelMenu extends JPanel {
    private MemberDAO memberDAO;

    public ManageAccountPanelMenu(JPanel mainPanel, CardLayout cardLayout, MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton addButton = new JButton("Add Member");
        JButton updateButton = new JButton("Update Member");
        JButton deleteButton = new JButton("Delete Member");
        JButton listButton = new JButton("List Members");
        JButton homeButton = new JButton("Home");

        addButton.addActionListener(e -> handleAddMember());
        updateButton.addActionListener(e -> handleUpdateMember());
        deleteButton.addActionListener(e -> handleDeleteMember());
        listButton.addActionListener(e -> handleListMembers());
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

    private void handleAddMember() {
        JTextField memberIDField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField phoneNumberField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField usernameField = new JTextField();
        JTextField passwordField = new JTextField();
        JTextField addressField = new JTextField();

        Object[] message = {
            "Member ID:", memberIDField,
            "Name:", nameField,
            "Phone Number:", phoneNumberField,
            "Email:", emailField,
            "Username:", usernameField,
            "Password:", passwordField,
            "Address:", addressField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Member", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int memberID = Integer.parseInt(memberIDField.getText());
                String name = nameField.getText();
                String phoneNumber = phoneNumberField.getText();
                String email = emailField.getText();
                String username = usernameField.getText();
                String password = passwordField.getText();
                String address = addressField.getText();

                Member member = new Member(memberID, name, phoneNumber, email, username, password, address);
                memberDAO.addMember(member);
                JOptionPane.showMessageDialog(this, "Member added successfully.");
            } catch (SQLException | NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Error adding member: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void handleUpdateMember() {
        String input = JOptionPane.showInputDialog(this, "Enter Member ID:", "Update Member", JOptionPane.QUESTION_MESSAGE);
        if (input != null) {
            try {
                int memberID = Integer.parseInt(input);
                Member member = memberDAO.getMember(memberID);
                if (member == null) {
                    JOptionPane.showMessageDialog(this, "Member not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                JTextField nameField = new JTextField(member.getName());
                JTextField phoneNumberField = new JTextField(member.getPhoneNumber());
                JTextField emailField = new JTextField(member.getEmail());
                JTextField usernameField = new JTextField(member.getUsername());
                JTextField passwordField = new JTextField(member.getPassword());
                JTextField addressField = new JTextField(member.getAddress());

                Object[] message = {
                    "Name:", nameField,
                    "Phone Number:", phoneNumberField,
                    "Email:", emailField,
                    "Username:", usernameField,
                    "Password:", passwordField,
                    "Address:", addressField
                };

                int option = JOptionPane.showConfirmDialog(this, message, "Update Member", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    member.setName(nameField.getText());
                    member.setPhoneNumber(phoneNumberField.getText());
                    member.setEmail(emailField.getText());
                    member.setUsername(usernameField.getText());
                    member.setPassword(passwordField.getText());
                    member.setAddress(addressField.getText());

                    memberDAO.updateMember(member);
                    JOptionPane.showMessageDialog(this, "Member updated successfully.");
                }
            } catch (SQLException | NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Error updating member: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void handleDeleteMember() {
        String input = JOptionPane.showInputDialog(this, "Enter Member ID:", "Delete Member", JOptionPane.QUESTION_MESSAGE);
        if (input != null) {
            try {
                int memberID = Integer.parseInt(input);
                memberDAO.deleteMember(memberID);
                JOptionPane.showMessageDialog(this, "Member deleted successfully.");
            } catch (SQLException | NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Error deleting member: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void handleListMembers() {
        try {
           List<Member> members = memberDAO.getAllMembers();
            StringBuilder sb = new StringBuilder("Members:\n");
            for (Member member : members) {
                sb.append(member.getMemberID()).append(": ").append(member.getName()).append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString(), "List Members", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error listing members: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

