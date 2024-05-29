package CMPE232;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO {

    public void addEnrollment(Enrollment enrollment) throws SQLException {
        String query = "INSERT INTO Enrollment (MemberID, ClassID) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, enrollment.getMemberID());
            stmt.setInt(2, enrollment.getClassID());
            stmt.executeUpdate();
        }
    }

    public Enrollment getEnrollment(int memberID, int classID) throws SQLException {
        String query = "SELECT * FROM Enrollment WHERE MemberID = ? AND ClassID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, memberID);
            stmt.setInt(2, classID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Enrollment(
                        rs.getInt("MemberID"),
                        rs.getInt("ClassID")
                    );
                }
            }
        }
        return null;
    }

    public List<Enrollment> getAllEnrollments() throws SQLException {
        String query = "SELECT * FROM Enrollment";
        List<Enrollment> enrollments = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                enrollments.add(new Enrollment(
                    rs.getInt("MemberID"),
                    rs.getInt("ClassID")
                ));
            }
        }
        return enrollments;
    }

    public void deleteEnrollment(int memberID, int classID) throws SQLException {
        String query = "DELETE FROM Enrollment WHERE MemberID = ? AND ClassID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, memberID);
            stmt.setInt(2, classID);
            stmt.executeUpdate();
        }
    }
}

