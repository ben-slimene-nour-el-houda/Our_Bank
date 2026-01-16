package src;
import java.awt.*;
import javax.swing.*;

public class Initial_interface {
    // AJOUT de 'protected' pour permettre l'accès aux classes qui héritent
    protected JFrame frame;
    protected JPanel backgroundPanel;

    public Initial_interface(String title, String backgroundImagePath)  {
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon(backgroundImagePath);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        // Très important pour pouvoir placer vos boutons librement
        backgroundPanel.setLayout(null); 
    }

    public void show() {
        frame.setVisible(true);
    }
}