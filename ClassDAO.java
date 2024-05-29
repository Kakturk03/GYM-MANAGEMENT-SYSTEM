package CMPE232;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ClassDAO {

    public void addClass(Class cls) throws SQLException {
        String query = "INSERT INTO Classes (ClassID, Name, Description, Schedule, Duration, Capacity) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, cls.getClassID());
            stmt.setString(2, cls.getName());
            stmt.setString(3, cls.getDescription());
            stmt.setTimestamp(4, cls.getSchedule());
            stmt.setInt(5, cls.getDuration());
            stmt.setInt(6, cls.getCapacity());
            stmt.executeUpdate();
        }
    }

    public Class getClass(int classID) throws SQLException {
        String query = "SELECT * FROM Classes WHERE ClassID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, classID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Class(
                        rs.getInt("ClassID"),
                        rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getTimestamp("Schedule"),
                        rs.getInt("Duration"),
                        rs.getInt("Capacity")
                    );
                }
            }
        }
        return null;
    }

    public List<Class> getAllClasses() throws SQLException {
        String query = "SELECT * FROM Classes";
        List<Class> classes = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                classes.add(new Class(
                    rs.getInt("ClassID"),
                    rs.getString("Name"),
                    rs.getString("Description"),
                    rs.getTimestamp("Schedule"),
                    rs.getInt("Duration"),
                    rs.getInt("Capacity")
                ));
            }
        }
        return classes;
    }

    public void updateClass(Class cls) throws SQLException {
        String query = "UPDATE Classes SET Name = ?, Description = ?, Schedule = ?, Duration = ?, Capacity = ? WHERE ClassID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, cls.getName());
            stmt.setString(2, cls.getDescription());
            stmt.setTimestamp(3, cls.getSchedule());
            stmt.setInt(4, cls.getDuration());
            stmt.setInt(5, cls.getCapacity());
            stmt.setInt(6, cls.getClassID());
            stmt.executeUpdate();
        }
    }

    public void deleteClass(int classID) throws SQLException {
        String query = "DELETE FROM Classes WHERE ClassID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, classID);
            stmt.executeUpdate();
        }
    }
}

