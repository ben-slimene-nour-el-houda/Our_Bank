package src;
import java.awt.*;
import javax.swing.*;

public class ClientInterface extends Initial_interface {
    private String userName;
    private double balance;
    private int userId;

    public ClientInterface(String name, double balance, int id) {
        super("Bank Dashboard - " + name, "Images/ClientBack.jpg");
        this.userName = name;
        this.balance = balance;
        this.userId = id;

        if (backgroundPanel != null) {
            backgroundPanel.setLayout(null);

            // --- WELCOME & BALANCE ---
            JLabel welcomeLabel = new JLabel("Welcome Back, " + userName, SwingConstants.LEFT);
            welcomeLabel.setFont(new Font("Arial", Font.BOLD, 22));
            welcomeLabel.setBounds(50, 30, 400, 30);
            backgroundPanel.add(welcomeLabel);

            JLabel balanceLabel = new JLabel("Current Balance: " + balance + " DT");
            balanceLabel.setFont(new Font("Arial", Font.ITALIC, 18));
            balanceLabel.setForeground(new Color(0, 102, 0)); 
            balanceLabel.setBounds(50, 65, 300, 25);
            backgroundPanel.add(balanceLabel);

            // --- BOUTON : MY ACCOUNT ---
            ImageIcon accountIcon = createResizedIcon("Images/acount info.png", 40, 40);
            Button accountButton = new Button(accountIcon, "Account", 50, 120, 100, 100);
            backgroundPanel.add(accountButton);
            
            accountButton.addActionListener(e -> {
                new ClientAccount(userName, balance, userId).show(); 
                frame.dispose();
            });

            // --- BOUTON : DEPOSIT (Dépôt) ---
            // On crée d'abord le bouton, puis on ajoute l'écouteur
            ImageIcon depositIcon = createResizedIcon("Images/depost icon.png", 40, 40); // Assurez-vous que l'image existe
            Button depositButton = new Button(depositIcon, "Deposit", 170, 120, 100, 100);
            backgroundPanel.add(depositButton);

            depositButton.addActionListener(e -> {
                new UpDownMoney("Deposit", "Images/DepotBack.jpg", "Deposit Money", userName, balance, userId).show();
                frame.dispose();
            });

            // --- BOUTON : WITHDRAW (Retrait) ---
            ImageIcon withdrawIcon = createResizedIcon("Images/withdraw.png", 40, 40); // Assurez-vous que l'image existe
            Button withdrawButton = new Button(withdrawIcon, "Withdraw", 290, 120, 100, 100);
            backgroundPanel.add(withdrawButton);

            withdrawButton.addActionListener(e -> {
                new UpDownMoney("Withdraw", "Images/WithdrawBack.jpg", "Withdraw Money", userName, balance, userId).show();
                frame.dispose();
            });

            // --- BOUTON : TRANSFER ---
            ImageIcon transferIcon = createResizedIcon("Images/transfer.png", 40, 40);
            Button transferButton = new Button(transferIcon, "Transfer", 410, 120, 100, 100);
            backgroundPanel.add(transferButton);

            transferButton.addActionListener(e -> {
                new Transfer(userName, balance, userId).show();
                frame.dispose();
            });

            frame.add(backgroundPanel);
        }
    }

    private ImageIcon createResizedIcon(String path, int width, int height) {
        try {
            ImageIcon icon = new ImageIcon(path);
            Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } catch (Exception e) {
            System.out.println("Icon not found: " + path);
            return null;
        }
    }

    @Override
    public void show() {
        frame.setVisible(true);
    }
}