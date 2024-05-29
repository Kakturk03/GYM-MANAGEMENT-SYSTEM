package CMPE232;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ManageAccountPanel extends JPanel {
    private MemberDAO memberDAO;

    public ManageAccountPanel(JPanel mainPanel, CardLayout cardLayout, MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton changeNameButton = new JButton("Change Name");
        JButton changeEmailButton = new JButton("Change Email");
        JButton changePasswordButton = new JButton("Change Password");
        JButton manageMembershipButton = new JButton("Manage Membership");
        JButton homeButton = new JButton("Home");

        homeButton.addActionListener(e -> cardLayout.show(mainPanel, "StartPage"));

        changeNameButton.addActionListener(e -> handleChangeName());
        changeEmailButton.addActionListener(e -> handleChangeEmail());
        changePasswordButton.addActionListener(e -> handleChangePassword());
        manageMembershipButton.addActionListener(e -> handleManageMembership());

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(changeNameButton, gbc);

        gbc.gridy = 1;
        add(changeEmailButton, gbc);

        gbc.gridy = 2;
        add(changePasswordButton, gbc);

        gbc.gridy = 3;
        add(manageMembershipButton, gbc);

        gbc.gridy = 4;
        add(homeButton, gbc);
    }

    private void handleChangeName() {
        int memberID = Integer.parseInt(JOptionPane.showInputDialog("Enter Member ID:"));
        String newName = JOptionPane.showInputDialog("Enter New Name:");

        try {
            Member member = memberDAO.getMember(memberID);
            if (member != null) {
                member.setName(newName);
                memberDAO.updateMember(member);
                JOptionPane.showMessageDialog(this, "Name updated successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Member not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleChangeEmail() {
        int memberID = Integer.parseInt(JOptionPane.showInputDialog("Enter Member ID:"));
        String newEmail = JOptionPane.showInputDialog("Enter New Email:");

        try {
            Member member = memberDAO.getMember(memberID);
            if (member != null) {
                member.setEmail(newEmail);
                memberDAO.updateMember(member);
                JOptionPane.showMessageDialog(this, "Email updated successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Member not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleChangePassword() {
        int memberID = Integer.parseInt(JOptionPane.showInputDialog("Enter Member ID:"));
        String newPassword = JOptionPane.showInputDialog("Enter New Password:");

        try {
            Member member = memberDAO.getMember(memberID);
            if (member != null) {
                member.setPassword(newPassword);
                memberDAO.updateMember(member);
                JOptionPane.showMessageDialog(this, "Password updated successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Member not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleManageMembership() {
        JOptionPane.showMessageDialog(this, "Manage Membership not implemented yet.");
    }
}
