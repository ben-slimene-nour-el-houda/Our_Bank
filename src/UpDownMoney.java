package src;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.*;

public class UpDownMoney extends Initial_interface {
    private String userName;
    private double currentBalance;
    private int userId;

    // Ajout des paramètres de session au constructeur
    UpDownMoney(String typeOperation, String path, String title, String name, double balance, int id) {
        super(typeOperation, path);
        this.userName = name;
        this.currentBalance = balance;
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
            new ClientInterface(this.userName, this.currentBalance, this.userId).show();
            frame.dispose();
        });

        // --- INTERFACE ---
        JLabel welcomeLabel = new JLabel(title);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 50));
        welcomeLabel.setBounds(150, 50, 600, 55);
        welcomeLabel.setForeground(Color.decode("#2518AD"));
        backgroundPanel.add(welcomeLabel);

        JLabel EnterLabel = new JLabel("Enter amount");
        EnterLabel.setFont(new Font("Arial", Font.BOLD, 25));
        EnterLabel.setBounds(250, 150, 300, 25);
        backgroundPanel.add(EnterLabel);

        JTextField AmountField = new JTextField();
        AmountField.setBounds(230, 200, 200, 30);
        backgroundPanel.add(AmountField);

        JButton actiButton = new JButton(typeOperation);
        actiButton.setBounds(280, 250, 100, 30);
        backgroundPanel.add(actiButton);

        // --- LOGIQUE DE TRANSACTION ---
        actiButton.addActionListener((ActionEvent e) -> {
            try {
                double amount = Double.parseDouble(AmountField.getText());
                double newBalance = currentBalance;

                if (amount <= 0) {
                    JOptionPane.showMessageDialog(frame, "Amount must be positive!");
                    return;
                }

                // Vérifier si c'est un dépôt ou un retrait
                if (typeOperation.equalsIgnoreCase("Withdraw")) {
                    if (amount > currentBalance) {
                        JOptionPane.showMessageDialog(frame, "Insufficient balance!");
                        return;
                    }
                    newBalance -= amount;
                } else {
                    newBalance += amount;
                }

                // Mise à jour de la base de données
                updateDatabaseBalance(newBalance);
                
                this.currentBalance = newBalance; // Mise à jour locale
                JOptionPane.showMessageDialog(frame, "Success! New balance: " + newBalance + " DT");
                
                // Retour à l'interface principale
                new ClientInterface(userName, currentBalance, userId).show();
                frame.dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number.");
            }
        });

        frame.add(backgroundPanel);
    }

    private void updateDatabaseBalance(double newBalance) {
        String query = "UPDATE clients SET solde = ? WHERE id_client = ?";
        try (Connection conn = Base.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setDouble(1, newBalance);
            pstmt.setInt(2, userId);
            pstmt.executeUpdate();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Database Error: " + ex.getMessage());
        }
    }
}