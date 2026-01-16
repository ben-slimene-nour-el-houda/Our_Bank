package src;
import java.awt.*;
import javax.swing.*;

public class AdminInterface extends Initial_interface {
    private String adminName;

    public AdminInterface(String name) {
        super("Admin Dashboard - " + name, "Images\\AdminBack.jpg");
        this.adminName = name;

        if (backgroundPanel != null) {
            backgroundPanel.setLayout(null);

            // ------ WELCOME ------
            JLabel welcomeLabel = new JLabel("Welcome Back mr " + adminName, SwingConstants.CENTER);
            welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
            welcomeLabel.setBounds(150, 70, 400, 30);
            backgroundPanel.add(welcomeLabel);

            // ------ BUTTON 1: ACCOUNTS ------
            ImageIcon accountIcon = createResizedIcon("Images\\acount info.png", 40, 40);
            Button accountButton = new Button(accountIcon, "Accounts", 225, 150, 80, 80);
            backgroundPanel.add(accountButton);
            
            // Dans AdminInterface.java
            accountButton.addActionListener(e -> {
                // On ouvre la nouvelle fenêtre en passant le nom de l'admin
                new AdminAllAccounts(this.adminName).show();
                
                // On ferme le dashboard actuel
                frame.dispose();
            });

            // ------ BUTTON 2: HISTORY ------
            ImageIcon historyIcon = createResizedIcon("Images\\hisotory.png", 40, 40);
            Button historyButton = new Button(historyIcon, "History", 375, 150, 80, 80);
            backgroundPanel.add(historyButton);

            historyButton.addActionListener(e -> {
                // IMPORTANT : L'admin n'a pas de solde (0.0) ni d'ID client (0)
                // On passe des valeurs neutres ou un ID spécifique pour les logs admin
                new History(adminName, 0.0, 0).show();
                frame.dispose(); 
            });

            // ------ BUTTON 3: LOGOUT (Optionnel mais recommandé) ------
            JButton logoutBtn = new JButton("Logout");
            logoutBtn.setBounds(10, 10, 80, 25);
            backgroundPanel.add(logoutBtn);
            logoutBtn.addActionListener(e -> {
                new Role().show(); // Ou votre page de garde
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
            System.out.println("Erreur icône : " + path);
            return null;
        }
    }

    public static void main(String[] args) {
        new AdminInterface("Admin").show();
    }
}