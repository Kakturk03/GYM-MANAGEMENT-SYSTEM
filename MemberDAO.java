package CMPE232;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

    public void addMember(Member member) throws SQLException {
        String query = "INSERT INTO Members (MemberID, Name, PhoneNumber, Email, Username, Password, Address) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, member.getMemberID());
            stmt.setString(2, member.getName());
            stmt.setString(3, member.getPhoneNumber());
            stmt.setString(4, member.getEmail());
            stmt.setString(5, member.getUsername());
            stmt.setString(6, member.getPassword());
            stmt.setString(7, member.getAddress());
            stmt.executeUpdate();
        }
    }

    public Member getMember(int memberID) throws SQLException {
        String query = "SELECT * FROM Members WHERE MemberID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, memberID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Member(
                        rs.getInt("MemberID"),
                        rs.getString("Name"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Email"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Address")
                    );
                }
            }
        }
        return null;
    }

    public List<Member> getAllMembers() throws SQLException {
        String query = "SELECT * FROM Members";
        List<Member> members = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                members.add(new Member(
                    rs.getInt("MemberID"),
                    rs.getString("Name"),
                    rs.getString("PhoneNumber"),
                    rs.getString("Email"),
                    rs.getString("Username"),
                    rs.getString("Password"),
                    rs.getString("Address")
                ));
            }
        }
        return members;
    }

    public void updateMember(Member member) throws SQLException {
        String query = "UPDATE Members SET Name = ?, PhoneNumber = ?, Email = ?, Username = ?, Password = ?, Address = ? WHERE MemberID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, member.getName());
            stmt.setString(2, member.getPhoneNumber());
            stmt.setString(3, member.getEmail());
            stmt.setString(4, member.getUsername());
            stmt.setString(5, member.getPassword());
            stmt.setString(6, member.getAddress());
            stmt.setInt(7, member.getMemberID());
            stmt.executeUpdate();
        }
    }

    public void deleteMember(int memberID) throws SQLException {
        String query = "DELETE FROM Members WHERE MemberID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, memberID);
            stmt.executeUpdate();
        }
    }
}
