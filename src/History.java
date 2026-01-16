package src;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.nio.file.*;
import javax.swing.*;

public class History extends Initial_interface {
    private String userName;
    private double currentBalance;
    private int userId;

    /**
     * Constructeur de la page Historique
     * @param name Nom de l'utilisateur (Session)
     * @param balance Solde actuel (Session)
     * @param id Identifiant unique (Session)
     */
    public History(String name, double balance, int id) {
        super("Transaction History", "Images\\HistoryBack.jpg");
        this.userName = name;
        this.currentBalance = balance;
        this.userId = id;

        backgroundPanel.setLayout(null);

        // --- BOUTON RETOUR ---
        ImageIcon previousIcon = new ImageIcon("Images\\back.jpg");
        Image img = previousIcon.getImage().getScaledInstance(50, 20, Image.SCALE_SMOOTH);
        previousIcon = new ImageIcon(img);

        JButton previousButton = new JButton(previousIcon);
        previousButton.setBounds(10, 10, 50, 20);
        backgroundPanel.add(previousButton);

        previousButton.addActionListener((ActionEvent e) -> {
            // Retourne à l'interface client en transmettant les données de session
            new ClientInterface(this.userName, this.currentBalance, this.userId).show();
            frame.dispose(); 
        });

        // --- TITRE ---
        JLabel titleLabel = new JLabel("Transaction History", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setBounds(150, 20, 400, 40);
        titleLabel.setForeground(Color.decode("#524BA1"));
        backgroundPanel.add(titleLabel);

        // --- ZONE D'AFFICHAGE (JTextArea) ---
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        textArea.setBackground(new Color(255, 255, 255, 200)); // Légèrement transparent

        // --- CHARGEMENT DE L'HISTORIQUE PERSONNEL ---
        try {
            // On cherche un fichier nommé 'history_ID.txt' (ex: history_1.txt)
            String fileName = "history_" + userId + ".txt";
            Path path = Path.of(fileName);

            if (Files.exists(path)) {
                String content = Files.readString(path);
                textArea.setText(content);
            } else {
                textArea.setText("Welcome " + userName + "!\n\nNo transactions recorded yet.");
            }
        } catch (IOException e) {
            textArea.setText("Error loading history: " + e.getMessage());
        }

        // --- SCROLLBAR (Barre de défilement) ---
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(100, 80, 500, 250);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Recent Activities"));
        backgroundPanel.add(scrollPane);

        frame.add(backgroundPanel);
    }

    public static void main(String[] args) {
        // Test avec des données fictives
        new History("Yassin", 2000.0, 1).show();
    }
}