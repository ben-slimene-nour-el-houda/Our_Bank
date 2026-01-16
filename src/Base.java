package src;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Base {

    // Paramètres de connexion
    private static final String URL = "jdbc:mysql://localhost:3306/Bank";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Charger le driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Établir la connexion
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexion réussie ✅");

        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC introuvable ❌");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erreur de connexion ❌");
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) {
        getConnection();
    }
}
