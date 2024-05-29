package CMPE232;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentDAO {

    public void addEquipment(Equipment equipment) throws SQLException {
        String query = "INSERT INTO Equipment (EquipmentID, Name, Type, Condition, Availability) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, equipment.getEquipmentID());
            stmt.setString(2, equipment.getName());
            stmt.setString(3, equipment.getType());
            stmt.setString(4, equipment.getCondition());
            stmt.setBoolean(5, equipment.isAvailability());
            stmt.executeUpdate();
        }
    }

    public Equipment getEquipment(int equipmentID) throws SQLException {
        String query = "SELECT * FROM Equipment WHERE EquipmentID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, equipmentID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Equipment(
                        rs.getInt("EquipmentID"),
                        rs.getString("Name"),
                        rs.getString("Type"),
                        rs.getString("Condition"),
                        rs.getBoolean("Availability")
                    );
                }
            }
        }
        return null;
    }

    public List<Equipment> getAllEquipment() throws SQLException {
        String query = "SELECT * FROM Equipment";
        List<Equipment> equipmentList = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                equipmentList.add(new Equipment(
                    rs.getInt("EquipmentID"),
                    rs.getString("Name"),
                    rs.getString("Type"),
                    rs.getString("Condition"),
                    rs.getBoolean("Availability")
                ));
            }
        }
        return equipmentList;
    }

    public void updateEquipment(Equipment equipment) throws SQLException {
        String query = "UPDATE Equipment SET Name = ?, Type = ?, Condition = ?, Availability = ? WHERE EquipmentID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, equipment.getName());
            stmt.setString(2, equipment.getType());
            stmt.setString(3, equipment.getCondition());
            stmt.setBoolean(4, equipment.isAvailability());
            stmt.setInt(5, equipment.getEquipmentID());
            stmt.executeUpdate();
        }
    }

    public void deleteEquipment(int equipmentID) throws SQLException {
        String query = "DELETE FROM Equipment WHERE EquipmentID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, equipmentID);
            stmt.executeUpdate();
        }
    }
}

