package src;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class ClientLogin extends UserLogin {

    public ClientLogin() {
        // Appelle le constructeur de UserLogin avec les paramètres spécifiques au client
        super("Client Login", "Images/ClientBack.jpg");

        // --- Logique du bouton LOGIN (hérité de UserLogin) ---
        this.loginButton.addActionListener((var1x) -> {
            String email = this.emailField.getText();
            String pass = new String(this.passwordField.getPassword());

            // Dans l'actionListener du bouton LOGIN :
            if (!email.isEmpty() && !pass.isEmpty()) {
                // On récupère toutes les infos nécessaires
                String query = "SELECT id_client, nom, solde FROM clients WHERE email = ? AND password = ?";
                
                try (java.sql.Connection conn = Base.getConnection();
                    java.sql.PreparedStatement pstmt = conn.prepareStatement(query)) {
                    
                    pstmt.setString(1, email);
                    pstmt.setString(2, pass);
                    
                    java.sql.ResultSet rs = pstmt.executeQuery();

                    if (rs.next()) {
                        // Extraction des données réelles
                        int id = rs.getInt("id_client");
                        String name = rs.getString("nom");
                        double currentBalance = rs.getDouble("solde");
                        
                        // On envoie TOUT à l'interface
                        new ClientInterface(name, currentBalance, id).show();
                        this.frame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(this.frame, "Identifiants invalides", "Erreur", 0);
                    }
                } catch (java.sql.SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // --- Ajout du bouton Sign Up (spécifique au Client) ---
        JButton SignUPButton = new JButton("Sign up");
        SignUPButton.setFont(new Font("Arial", Font.BOLD, 14));
        // Positionné à côté du bouton LOGIN
        SignUPButton.setBounds(410, 210, 100, 30);
        backgroundPanel.add(SignUPButton);

        SignUPButton.addActionListener((ActionEvent e) -> {
            new CreateAccount().show();
            frame.dispose(); 
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ClientLogin().show();
        });
    }
}