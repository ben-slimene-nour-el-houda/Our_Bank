package src;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class Transfer extends Initial_interface {
    private String userName;
    private double currentBalance;
    private int userId;

    public Transfer(String name, double balance, int id) {
        super("Transfer Money", "Images\\TransferBack.jpg");
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
        JLabel welcomeLabel = new JLabel("Transfer Money");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 50));
        welcomeLabel.setBounds(150, 50, 600, 55);
        welcomeLabel.setForeground(Color.decode("#524BA1"));
        backgroundPanel.add(welcomeLabel);

        JLabel Recipient = new JLabel("Recipient's CIN");
        Recipient.setFont(new Font("Arial", Font.BOLD, 20));
        Recipient.setBounds(100, 150, 300, 25);
        backgroundPanel.add(Recipient);

        JTextField RecipientField = new JTextField();
        RecipientField.setBounds(80, 180, 200, 30);
        backgroundPanel.add(RecipientField);

        JLabel EnterLabel = new JLabel("Enter amount");
        EnterLabel.setFont(new Font("Arial", Font.BOLD, 20));
        EnterLabel.setBounds(415, 150, 300, 25);
        backgroundPanel.add(EnterLabel);

        JTextField AmountTextField = new JTextField();
        AmountTextField.setBounds(380, 180, 200, 30);
        backgroundPanel.add(AmountTextField);

        JButton transferButton = new JButton("Transfer");
        transferButton.setBounds(280, 250, 100, 30);
        backgroundPanel.add(transferButton);

        // --- LOGIQUE DE TRANSFERT ---
        transferButton.addActionListener((ActionEvent e) -> {
            String recipientCIN = RecipientField.getText();
            String amountStr = AmountTextField.getText();

            if (recipientCIN.isEmpty() || amountStr.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill all fields");
                return;
            }

            try {
                double amount = Double.parseDouble(amountStr);
                if (amount <= 0 || amount > currentBalance) {
                    JOptionPane.showMessageDialog(frame, "Invalid amount or insufficient balance");
                    return;
                }

                executeTransfer(recipientCIN, amount);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid amount");
            }
        });

        frame.add(backgroundPanel);
    }

    private void executeTransfer(String cin, double amount) {
        Connection conn = Base.getConnection();
        if (conn == null) return;

        try {
            conn.setAutoCommit(false); // Début de la transaction (tout ou rien)

            // 1. Vérifier si le destinataire existe et récupérer son solde
            String checkDest = "SELECT id_client, solde FROM clients WHERE cin = ?";
            PreparedStatement pstmt1 = conn.prepareStatement(checkDest);
            pstmt1.setString(1, cin);
            ResultSet rs = pstmt1.executeQuery();

            if (rs.next()) {
                int destId = rs.getInt("id_client");
                double destBalance = rs.getDouble("solde");

                if (destId == userId) {
                    JOptionPane.showMessageDialog(frame, "You cannot transfer to yourself!");
                    conn.rollback();
                    return;
                }

                // 2. Débiter l'expéditeur
                String debit = "UPDATE clients SET solde = solde - ? WHERE id_client = ?";
                PreparedStatement pstmt2 = conn.prepareStatement(debit);
                pstmt2.setDouble(1, amount);
                pstmt2.setInt(2, userId);
                pstmt2.executeUpdate();

                // 3. Créditer le destinataire
                String credit = "UPDATE clients SET solde = solde + ? WHERE id_client = ?";
                PreparedStatement pstmt3 = conn.prepareStatement(credit);
                pstmt3.setDouble(1, amount);
                pstmt3.setInt(2, destId);
                pstmt3.executeUpdate();

                conn.commit(); // Valider les changements
                this.currentBalance -= amount; // Mise à jour locale
                JOptionPane.showMessageDialog(frame, "Transfer Successful! New balance: " + currentBalance);
                
                new ClientInterface(userName, currentBalance, userId).show();
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Recipient CIN not found");
                conn.rollback();
            }

        } catch (SQLException ex) {
            try { conn.rollback(); } catch (SQLException e1) { e1.printStackTrace(); }
            ex.printStackTrace();
        }
    }
}