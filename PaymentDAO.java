package CMPE232;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

    public void addPayment(Payment payment) throws SQLException {
        String query = "INSERT INTO Payments (PaymentID, MemberID, Date, Amount, Description) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, payment.getPaymentID());
            stmt.setInt(2, payment.getMemberID());
            stmt.setTimestamp(3, payment.getDate());
            stmt.setDouble(4, payment.getAmount());
            stmt.setString(5, payment.getDescription());
            stmt.executeUpdate();
        }
    }

    public Payment getPayment(int paymentID) throws SQLException {
        String query = "SELECT * FROM Payments WHERE PaymentID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, paymentID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Payment(
                        rs.getInt("PaymentID"),
                        rs.getInt("MemberID"),
                        rs.getTimestamp("Date"),
                        rs.getDouble("Amount"),
                        rs.getString("Description")
                    );
                }
            }
        }
        return null;
    }

    public List<Payment> getAllPayments() throws SQLException {
        String query = "SELECT * FROM Payments";
        List<Payment> payments = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                payments.add(new Payment(
                    rs.getInt("PaymentID"),
                    rs.getInt("MemberID"),
                    rs.getTimestamp("Date"),
                    rs.getDouble("Amount"),
                    rs.getString("Description")
                ));
            }
        }
        return payments;
    }

    public void updatePayment(Payment payment) throws SQLException {
        String query = "UPDATE Payments SET MemberID = ?, Date = ?, Amount = ?, Description = ? WHERE PaymentID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, payment.getMemberID());
            stmt.setTimestamp(2, payment.getDate());
            stmt.setDouble(3, payment.getAmount());
            stmt.setString(4, payment.getDescription());
            stmt.setInt(5, payment.getPaymentID());
            stmt.executeUpdate();
        }
    }

    public void deletePayment(int paymentID) throws SQLException {
        String query = "DELETE FROM Payments WHERE PaymentID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, paymentID);
            stmt.executeUpdate();
        }
    }
}


