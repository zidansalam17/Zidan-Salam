import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameRental {
    private final String DB_URL = "jdbc:mysql://localhost:3306/gamerental"; // URL database
    private final String DB_USER = "root"; // Username database (default Laragon)
    private final String DB_PASSWORD = ""; // Password database (default kosong di Laragon)

    public GameRental() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            System.out.println("Connected to the database.");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database.", e);
        }
    }

    public void addGame(String title, String genre) {
        String query = "INSERT INTO games (title, genre) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, title);
            stmt.setString(2, genre);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to add game.", e);
        }
    }

    public void updateGame(int id, String title, String genre) {
        String query = "UPDATE games SET title = ?, genre = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, title);
            stmt.setString(2, genre);
            stmt.setInt(3, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update game.", e);
        }
    }

    public void deleteGame(int id) {
        String query = "DELETE FROM games WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete game.", e);
        }
    }

    public List<Game> listGames() {
        List<Game> games = new ArrayList<>();
        String query = "SELECT * FROM games";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String genre = rs.getString("genre");
                games.add(new Game(title, genre));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to list games.", e);
        }
        return games;
    }
}
