package src;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class AdminLogin extends UserLogin {

    public AdminLogin() {
        super("Admin Login", "Images/AdminBack.jpg");

        loginButton.addActionListener((ActionEvent e) -> {
            String inputCIN = emailField.getText(); 
            String inputPass = new String(passwordField.getPassword());

            if (inputCIN.isEmpty() || inputPass.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Veuillez remplir tous les champs", "Erreur", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // --- LOGIQUE DE CONNEXION SQL ---
            // On s'assure de sélectionner le CIN pour l'afficher plus tard
            String query = "SELECT cin FROM admins WHERE cin = ? AND password = ?";
            
            try (Connection conn = Base.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(query)) {
                
                pstmt.setString(1, inputCIN);
                pstmt.setString(2, inputPass);
                
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    // RÉCUPÉRATION DU NOM/CIN DE L'ADMIN
                    String adminName = rs.getString("cin"); 
                    
                    // PASSAGE DU NOM À L'INTERFACE (Ajusté pour correspondre au nouveau constructeur)
                    new AdminInterface(adminName).show();
                    frame.dispose(); 
                } else {
                    JOptionPane.showMessageDialog(frame, "CIN ou Mot de passe incorrect !", "Erreur", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        new AdminLogin().show();
    }
}