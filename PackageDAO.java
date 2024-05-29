package CMPE232;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PackageDAO {

    public void addPackage(Package pkg) throws SQLException {
        String query = "INSERT INTO Package (PackageID, Name, Amount, Type, Password) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, pkg.getPackageID());
            stmt.setString(2, pkg.getName());
            stmt.setDouble(3, pkg.getAmount());
            stmt.setString(4, pkg.getType());
            stmt.setString(5, pkg.getPassword());
            stmt.executeUpdate();
        }
    }

    public Package getPackage(int packageID) throws SQLException {
        String query = "SELECT * FROM Package WHERE PackageID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, packageID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Package(
                        rs.getInt("PackageID"),
                        rs.getString("Name"),
                        rs.getDouble("Amount"),
                        rs.getString("Type"),
                        rs.getString("Password")
                    );
                }
            }
        }
        return null;
    }

    public List<Package> getAllPackages() throws SQLException {
        String query = "SELECT * FROM Package";
        List<Package> packages = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                packages.add(new Package(
                    rs.getInt("PackageID"),
                    rs.getString("Name"),
                    rs.getDouble("Amount"),
                    rs.getString("Type"),
                    rs.getString("Password")
                ));
            }
        }
        return packages;
    }

    public void updatePackage(Package pkg) throws SQLException {
        String query = "UPDATE Package SET Name = ?, Amount = ?, Type = ?, Password = ? WHERE PackageID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, pkg.getName());
            stmt.setDouble(2, pkg.getAmount());
            stmt.setString(3, pkg.getType());
            stmt.setString(4, pkg.getPassword());
            stmt.setInt(5, pkg.getPackageID());
            stmt.executeUpdate();
        }
    }

    public void deletePackage(int packageID) throws SQLException {
        String query = "DELETE FROM Package WHERE PackageID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, packageID);
            stmt.executeUpdate();
        }
    }
}

