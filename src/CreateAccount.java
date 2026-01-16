package src;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;

import javax.swing.*;


public class CreateAccount extends Initial_interface{
    public CreateAccount() {
        super("Sign UP", "Images\\ClientBack.jpg");
        // Remove GridBagLayout
        backgroundPanel.setLayout(null);

        ImageIcon Previousicon = new ImageIcon("Images\\back.jpg");
        Image previousImage = Previousicon.getImage().getScaledInstance(50, 20, Image.SCALE_SMOOTH); // Resize image
        Previousicon = new ImageIcon(previousImage); // Reassign resized image as the icon

        JButton previousButton = new JButton();
        previousButton.setFont(new Font("Arial", Font.BOLD, 14));
        previousButton.setBounds(1, 1, 50, 20);
        previousButton.setIcon(Previousicon);
        backgroundPanel.add(previousButton);

        previousButton.addActionListener((ActionEvent e) -> {
            new ClientLogin().show();
            frame.dispose(); 
        });

        // Add welcome label
        JLabel welcomeLabel = new JLabel("Create Account", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setBounds(200, 20, 300, 30); // Adjust position and size
        backgroundPanel.add(welcomeLabel);

        // Add Full Name label and text field
        JLabel Name = new JLabel("Full Name:");
        Name.setFont(new Font("Arial", Font.PLAIN, 14));
        Name.setBounds(150, 80, 100, 25); // Position and size
        backgroundPanel.add(Name);

        JTextField NameField = new JTextField();
        NameField.setBounds(300, 80, 200, 25); // Position and size
        backgroundPanel.add(NameField);

        // Add Email label and text field
        JLabel EmaiLabel = new JLabel("Email:");
        EmaiLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        EmaiLabel.setBounds(150, 120, 100, 25); // Position and size
        backgroundPanel.add(EmaiLabel);

        JTextField EamilField = new JTextField();
        EamilField.setBounds(300, 120, 200, 25); // Position and size
        backgroundPanel.add(EamilField);

        // Add Telephone label and text field
        JLabel TelephonLabel = new JLabel("Telephone:");
        TelephonLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        TelephonLabel.setBounds(150, 160, 100, 25); // Position and size
        backgroundPanel.add(TelephonLabel);

        JTextField TelepohneField = new JTextField();
        TelepohneField.setBounds(300, 160, 200, 25); // Position and size
        backgroundPanel.add(TelepohneField);

        // Add CIN label and text field
        JLabel CIN = new JLabel("CIN:");
        CIN.setFont(new Font("Arial", Font.PLAIN, 14));
        CIN.setBounds(150, 200, 100, 25); // Position and size
        backgroundPanel.add(CIN);

        JTextField CINField = new JTextField();
        CINField.setBounds(300, 200, 200, 25); // Position and size
        backgroundPanel.add(CINField);

        // Add Password label and text field
        JLabel passwordJLabel = new JLabel("Password:");
        passwordJLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordJLabel.setBounds(150, 240, 100, 25); // Position and size
        backgroundPanel.add(passwordJLabel);

        JPasswordField passworField = new JPasswordField();
        passworField.setBounds(300, 240, 200, 25); // Position and size
        backgroundPanel.add(passworField);

        // Add Confirm Password label and text field
        JLabel ConfirmPassword = new JLabel("Confirm Password:");
        ConfirmPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        ConfirmPassword.setBounds(150, 280, 150, 25); // Position and size
        backgroundPanel.add(ConfirmPassword);

        JPasswordField ConfirmPassworField = new JPasswordField();
        ConfirmPassworField.setBounds(300, 280, 200, 25); // Position and size
        backgroundPanel.add(ConfirmPassworField);

        // Add Sign Up button
        JButton signUpButton = new JButton("Sign UP");
        signUpButton.setBounds(550, 320, 100, 30); // Position and size
        backgroundPanel.add(signUpButton);

        signUpButton.addActionListener((ActionEvent e) -> {
            // 1. Récupération des données depuis les champs
            String name = NameField.getText();
            String email = EamilField.getText();
            String phone = TelepohneField.getText();
            String cin = CINField.getText();
            String password = new String(passworField.getPassword());
            String confirmPass = new String(ConfirmPassworField.getPassword());

            // 2. Vérification rapide des champs vides
            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || cin.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Tous les champs sont obligatoires !");
                return;
            }

            // 3. Vérification des mots de passe
            if (!password.equals(confirmPass)) {
                JOptionPane.showMessageDialog(frame, "Les mots de passe ne correspondent pas !");
                return;
            }

            // 4. Insertion dans la base de données
            String query = "INSERT INTO clients (nom, email, telephone, cin, password, solde) VALUES (?, ?, ?, ?, ?, 0.0)";

            try (Connection conn = Base.getConnection(); 
                java.sql.PreparedStatement pstmt = conn.prepareStatement(query)) {
                
                pstmt.setString(1, name);
                pstmt.setString(2, email);
                pstmt.setString(3, phone);
                pstmt.setString(4, cin);
                pstmt.setString(5, password);

                pstmt.executeUpdate();
                
                JOptionPane.showMessageDialog(frame, "Compte créé avec succès ! ✅");
                
                // Retour au login
                new ClientLogin().show();
                frame.dispose();

            } catch (java.sql.SQLException ex) {
                if (ex.getErrorCode() == 1062) { // Code erreur pour "Duplicate entry" (Email ou CIN existe déjà)
                    JOptionPane.showMessageDialog(frame, "Erreur : Cet Email ou CIN est déjà utilisé !");
                } else {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Erreur lors de l'inscription : " + ex.getMessage());
                }
            }
        });


        // Add the panel to the frame
        frame.add(backgroundPanel);
    }

    public static void main(String[] args) {
        new CreateAccount().show();
    }

}
