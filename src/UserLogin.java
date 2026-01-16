package src;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class UserLogin extends Initial_interface {
    // Déclarations au niveau de la classe (champs d'instance)
    protected JButton loginButton;
    protected JTextField emailField;
    protected JPasswordField passwordField;

    public UserLogin(String title, String backgroundImagePath) {
        super(title, backgroundImagePath);
        backgroundPanel.setLayout(null); 

        // Bouton Retour
        ImageIcon previousIcon = new ImageIcon("Images/back.jpg");
        Image previousImage = previousIcon.getImage().getScaledInstance(50, 20, Image.SCALE_SMOOTH);
        previousIcon = new ImageIcon(previousImage);

        JButton previousButton = new JButton();
        previousButton.setBounds(1, 1, 50, 20);
        previousButton.setIcon(previousIcon);
        backgroundPanel.add(previousButton);

        previousButton.addActionListener((ActionEvent e) -> {
            new Role().show();
            frame.dispose(); 
        });

        // Titre
        JLabel welcomeLabel = new JLabel("Welcome", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setBounds(270, 80, 150, 25);
        backgroundPanel.add(welcomeLabel);

        // Email
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        emailLabel.setBounds(250, 120, 100, 25);
        backgroundPanel.add(emailLabel);

        emailField = new JTextField(); // On utilise la variable de classe
        emailField.setBounds(350, 120, 200, 25);
        backgroundPanel.add(emailField);

        // Mot de passe
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordLabel.setBounds(250, 160, 100, 25);
        backgroundPanel.add(passwordLabel);

        passwordField = new JPasswordField(); // On utilise la variable de classe
        passwordField.setBounds(350, 160, 200, 25);
        backgroundPanel.add(passwordField);

        // Bouton Login
        loginButton = new JButton("LOGIN");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBounds(300, 210, 100, 30);
        backgroundPanel.add(loginButton);

        frame.add(backgroundPanel);
    }
}