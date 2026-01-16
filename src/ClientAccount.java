package src;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;

public class ClientAccount extends Initial_interface {
    private String userName;
    private double balance;
    private int userId;

    // Le constructeur reçoit les infos de session
    public ClientAccount(String name, double balance, int id) {
        super("My Account", "Images\\AccountBack.png");
        this.userName = name;
        this.balance = balance;
        this.userId = id;
        
        backgroundPanel.setLayout(null);

        // --- BOUTON RETOUR ---
        ImageIcon Previousicon = new ImageIcon("Images\\back.jpg");
        Image previousImage = Previousicon.getImage().getScaledInstance(50, 20, Image.SCALE_SMOOTH); 
        Previousicon = new ImageIcon(previousImage); 

        JButton previousButton = new JButton();
        previousButton.setBounds(1, 1, 50, 20);
        previousButton.setIcon(Previousicon);
        backgroundPanel.add(previousButton);

        previousButton.addActionListener((ActionEvent e) -> {
            // On renvoie les données à l'interface principale
            new ClientInterface(this.userName, this.balance, this.userId).show();
            frame.dispose(); 
        });

        // --- TITRE INFORMATIONS PERSONNELLES ---
        JLabel PersonalIfo = new JLabel("Personal Information");
        PersonalIfo.setFont(new Font("Arial", Font.BOLD, 27));
        PersonalIfo.setBounds(50, 50, 300, 35);
        PersonalIfo.setForeground(Color.decode("#2518AD")); 
        backgroundPanel.add(PersonalIfo);

        // --- AFFICHAGE DYNAMIQUE VIA SQL ---
        String userEmail = "";
        String userPhone = "";
        String userCIN = "";

        try (Connection conn = Base.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT email, telephone, cin FROM clients WHERE id_client = ?")) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                userEmail = rs.getString("email");
                userPhone = rs.getString("telephone");
                userCIN = rs.getString("cin");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JLabel FullNameLabel = new JLabel("Full Name: " + userName);
        FullNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        FullNameLabel.setBounds(70, 95, 400, 25);
        backgroundPanel.add(FullNameLabel);

        JLabel EmailLabel = new JLabel("Email: " + userEmail);
        EmailLabel.setFont(new Font("Arial", Font.BOLD, 14));
        EmailLabel.setBounds(70, 125, 400, 25);
        backgroundPanel.add(EmailLabel);

        JLabel TelephoneLabel = new JLabel("Telephone: " + userPhone);
        TelephoneLabel.setFont(new Font("Arial", Font.BOLD, 14));
        TelephoneLabel.setBounds(70, 155, 400, 25);
        backgroundPanel.add(TelephoneLabel);

        // --- TITRE DÉTAILS DU COMPTE ---
        JLabel AccountDet = new JLabel("Account Details");
        AccountDet.setFont(new Font("Arial", Font.BOLD, 27));
        AccountDet.setBounds(50, 200, 300, 35);
        AccountDet.setForeground(Color.decode("#2518AD")); 
        backgroundPanel.add(AccountDet);

        // Simulation du RIB/IBAN basé sur le CIN et l'ID
        JLabel RIB = new JLabel("RIB: 20930" + userId + userCIN.substring(0, 2));
        RIB.setFont(new Font("Arial", Font.BOLD, 14));
        RIB.setBounds(70, 245, 300, 25);
        backgroundPanel.add(RIB);

        JLabel IBAN = new JLabel("IBAN: TN59 " + userCIN);
        IBAN.setFont(new Font("Arial", Font.BOLD, 14));
        IBAN.setBounds(70, 275, 400, 25);
        backgroundPanel.add(IBAN);

        JLabel Amount = new JLabel("Balance: " + balance + " DT");
        Amount.setFont(new Font("Arial", Font.BOLD, 22));
        Amount.setForeground(new Color(0, 120, 0));
        Amount.setBounds(450, 320, 300, 30);
        backgroundPanel.add(Amount);

        frame.add(backgroundPanel);
    }

    // Le main est maintenant juste pour le test rapide, 
    // mais en réalité cette page sera lancée depuis ClientInterface
    public static void main(String[] args) {
        new ClientAccount("Test User", 0.0, 1).show();
    }
}