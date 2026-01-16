package src;
import java.awt.*;
import javax.swing.*;

public class Button extends JButton {
    public Button(ImageIcon icon, String text, int x, int y, int width, int height) {
        super(text); 
        this.setFont(new Font("Arial", Font.BOLD, 9));
        this.setHorizontalTextPosition(SwingConstants.CENTER);
        this.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.setIcon(icon);
        this.setBounds(x, y, width, height); 
    }
}
