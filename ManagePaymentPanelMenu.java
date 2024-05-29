package CMPE232;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class ManagePaymentPanelMenu extends JPanel {
    private PaymentDAO paymentDAO;

    public ManagePaymentPanelMenu(JPanel mainPanel, CardLayout cardLayout, PaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton addButton = new JButton("Add Payment");
        JButton updateButton = new JButton("Update Payment");
        JButton deleteButton = new JButton("Delete Payment");
        JButton listButton = new JButton("List Payments");
        JButton homeButton = new JButton("Home");

        addButton.addActionListener(e -> handleAddPayment());
        updateButton.addActionListener(e -> handleUpdatePayment());
        deleteButton.addActionListener(e -> handleDeletePayment());
        listButton.addActionListener(e -> handleListPayments());
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

    private void handleAddPayment() {
        JTextField paymentIDField = new JTextField();
        JTextField memberIDField = new JTextField();
        JTextField dateField = new JTextField();
        JTextField amountField = new JTextField();
        JTextField descriptionField = new JTextField();

        Object[] message = {
            "Payment ID:", paymentIDField,
            "Member ID:", memberIDField,
            "Date (YYYY-MM-DD HH:MM:SS):", dateField,
            "Amount:", amountField,
            "Description:", descriptionField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Payment", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int paymentID = Integer.parseInt(paymentIDField.getText());
                int memberID = Integer.parseInt(memberIDField.getText());
                Timestamp date = Timestamp.valueOf(dateField.getText());
                double amount = Double.parseDouble(amountField.getText());
                String description = descriptionField.getText();

                Payment payment = new Payment(paymentID, memberID, date, amount, description);
                paymentDAO.addPayment(payment);
                JOptionPane.showMessageDialog(this, "Payment added successfully.");
            } catch (SQLException | NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Error adding payment: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void handleUpdatePayment() {
        String input = JOptionPane.showInputDialog(this, "Enter Payment ID:", "Update Payment", JOptionPane.QUESTION_MESSAGE);
        if (input != null) {
            try {
                int paymentID = Integer.parseInt(input);
                Payment payment = paymentDAO.getPayment(paymentID);
                if (payment == null) {
                    JOptionPane.showMessageDialog(this, "Payment not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                JTextField memberIDField = new JTextField(String.valueOf(payment.getMemberID()));
                JTextField dateField = new JTextField(payment.getDate().toString());
                JTextField amountField = new JTextField(String.valueOf(payment.getAmount()));
                JTextField descriptionField = new JTextField(payment.getDescription());

                Object[] message = {
                    "Member ID:", memberIDField,
                    "Date (YYYY-MM-DD HH:MM:SS):", dateField,
                    "Amount:", amountField,
                    "Description:", descriptionField
                };

                int option = JOptionPane.showConfirmDialog(this, message, "Update Payment", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    payment.setMemberID(Integer.parseInt(memberIDField.getText()));
                    payment.setDate(Timestamp.valueOf(dateField.getText()));
                    payment.setAmount(Double.parseDouble(amountField.getText()));
                    payment.setDescription(descriptionField.getText());

                    paymentDAO.updatePayment(payment);
                    JOptionPane.showMessageDialog(this, "Payment updated successfully.");
                }
            } catch (SQLException | NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Error updating payment: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void handleDeletePayment() {
        String input = JOptionPane.showInputDialog(this, "Enter Payment ID:", "Delete Payment", JOptionPane.QUESTION_MESSAGE);
        if (input != null) {
            try {
                int paymentID = Integer.parseInt(input);
                paymentDAO.deletePayment(paymentID);
                JOptionPane.showMessageDialog(this, "Payment deleted successfully.");
            } catch (SQLException | NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Error deleting payment: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void handleListPayments() {
        try {
            List<Payment> payments = paymentDAO.getAllPayments();
            StringBuilder sb = new StringBuilder("Payments:\n");
            for (Payment payment : payments) {
                sb.append(payment.getPaymentID()).append(": ").append(payment.getAmount()).append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString(), "List Payments", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error listing payments: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

