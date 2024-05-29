package CMPE232;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AssignmentDAO {

    public void addAssignment(Assignment assignment) throws SQLException {
        String query = "INSERT INTO Assignment (TrainerID, ClassID) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, assignment.getTrainerID());
            stmt.setInt(2, assignment.getClassID());
            stmt.executeUpdate();
        }
    }

    public Assignment getAssignment(int trainerID, int classID) throws SQLException {
        String query = "SELECT * FROM Assignment WHERE TrainerID = ? AND ClassID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, trainerID);
            stmt.setInt(2, classID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Assignment(
                        rs.getInt("TrainerID"),
                        rs.getInt("ClassID")
                    );
                }
            }
        }
        return null;
    }

    public List<Assignment> getAllAssignments() throws SQLException {
        String query = "SELECT * FROM Assignment";
        List<Assignment> assignments = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                assignments.add(new Assignment(
                    rs.getInt("TrainerID"),
                    rs.getInt("ClassID")
                ));
            }
        }
        return assignments;
    }

    public void deleteAssignment(int trainerID, int classID) throws SQLException {
        String query = "DELETE FROM Assignment WHERE TrainerID = ? AND ClassID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, trainerID);
            stmt.setInt(2, classID);
            stmt.executeUpdate();
        }
    }
}

