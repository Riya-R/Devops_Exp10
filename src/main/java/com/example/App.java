import java.sql.*;
import java.util.Scanner;
import java.io.*;

public class App {

    public static void main(String[] args) {

        // Use try-with-resources (auto close)
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.println("Enter username:");
            String userInput = scanner.nextLine();

            // Database connection details (better to use environment variables)
            String url = "jdbc:mysql://localhost:3306/testdb";
            String user = "root";
            String password = System.getenv("DB_PASSWORD"); // secure

            // Use PreparedStatement (prevents SQL Injection)
            String query = "SELECT * FROM users WHERE username = ?";

            try (Connection conn = DriverManager.getConnection(url, user, password);
                 PreparedStatement pstmt = conn.prepareStatement(query)) {

                pstmt.setString(1, userInput);

                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    System.out.println("User found: " + rs.getString("username"));
                }

            } catch (SQLException e) {
                System.out.println("Database error occurred."); // no sensitive info
            }

            // Avoid command injection (validate input or remove exec)
            if (userInput.matches("[a-zA-Z0-9_]+")) {
                ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "dir", userInput);
                pb.start();
            } else {
                System.out.println("Invalid input for command execution.");
            }

        } catch (Exception e) {
            System.out.println("Unexpected error occurred.");
        }
    }
}
