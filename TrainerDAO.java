package CMPE232;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainerDAO {

    public void addTrainer(Trainer trainer) throws SQLException {
        String query = "INSERT INTO Trainers (TrainerID, Name, PhoneNumber, Email, Username, Password, Address, Branch) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, trainer.getTrainerID());
            stmt.setString(2, trainer.getName());
            stmt.setString(3, trainer.getPhoneNumber());
            stmt.setString(4, trainer.getEmail());
            stmt.setString(5, trainer.getUsername());
            stmt.setString(6, trainer.getPassword());
            stmt.setString(7, trainer.getAddress());
            stmt.setString(8, trainer.getBranch());
            stmt.executeUpdate();
        }
    }

    public Trainer getTrainer(int trainerID) throws SQLException {
        String query = "SELECT * FROM Trainers WHERE TrainerID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, trainerID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Trainer(
                        rs.getInt("TrainerID"),
                        rs.getString("Name"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Email"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Address"),
                        rs.getString("Branch")
                    );
                }
            }
        }
        return null;
    }

    public List<Trainer> getAllTrainers() throws SQLException {
        String query = "SELECT * FROM Trainers";
        List<Trainer> trainers = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                trainers.add(new Trainer(
                    rs.getInt("TrainerID"),
                    rs.getString("Name"),
                    rs.getString("PhoneNumber"),
                    rs.getString("Email"),
                    rs.getString("Username"),
                    rs.getString("Password"),
                    rs.getString("Address"),
                    rs.getString("Branch")
                ));
            }
        }
        return trainers;
    }

    public void updateTrainer(Trainer trainer) throws SQLException {
        String query = "UPDATE Trainers SET Name = ?, PhoneNumber = ?, Email = ?, Username = ?, Password = ?, Address = ?, Branch = ? WHERE TrainerID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, trainer.getName());
            stmt.setString(2, trainer.getPhoneNumber());
            stmt.setString(3, trainer.getEmail());
            stmt.setString(4, trainer.getUsername());
            stmt.setString(5, trainer.getPassword());
            stmt.setString(6, trainer.getAddress());
            stmt.setString(7, trainer.getBranch());
            stmt.setInt(8, trainer.getTrainerID());
            stmt.executeUpdate();
        }
    }

    public void deleteTrainer(int trainerID) throws SQLException {
        String query = "DELETE FROM Trainers WHERE TrainerID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, trainerID);
            stmt.executeUpdate();
        }
    }
}
