package src;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class Role extends Initial_interface {
    public Role() {

        super("Role Selection", "Images\\RoleBackground.jpg");
        backgroundPanel.setLayout(new GridBagLayout()); 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding around components


        JLabel titleLabel = new JLabel("Select your role to continue.");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        backgroundPanel.add(titleLabel, gbc);


        ImageIcon adminIcon = new ImageIcon("Images\\Admin.png");
        Image adminImage = adminIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Resize image
        adminIcon = new ImageIcon(adminImage); 

        JButton adminButton = new JButton("ADMIN");
        adminButton.setFont(new Font("Arial", Font.BOLD, 14));
        adminButton.setHorizontalTextPosition(SwingConstants.CENTER);
        adminButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        adminButton.setIcon(adminIcon); // Set resized icon
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        backgroundPanel.add(adminButton, gbc);

        adminButton.addActionListener((ActionEvent e) -> {

            new AdminLogin().show();
            
            // Close the current window (role selection frame)
            frame.dispose(); // Dispose the current frame, where 'frame' refers to the JFrame in select_role
        });

        // Add the "Client" button with resized icon
        ImageIcon clientIcon = new ImageIcon("Images\\Client.png");
        Image clientImage = clientIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Resize image
        clientIcon = new ImageIcon(clientImage); // Reassign resized image as the icon

        JButton clientButton = new JButton("CLIENT");
        clientButton.setFont(new Font("Arial", Font.BOLD, 14));
        clientButton.setHorizontalTextPosition(SwingConstants.CENTER);
        clientButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        clientButton.setIcon(clientIcon); // Set resized icon
        gbc.gridx = 1;
        gbc.gridy = 1;
        backgroundPanel.add(clientButton, gbc);

        clientButton.addActionListener((ActionEvent e) -> {
            // Open the ClientLogin window
            new ClientLogin().show();
            // Close the current window (role selection frame)
            frame.dispose(); // Dispose the current frame, where 'frame' refers to the JFrame in select_role
        });
        

        // Add the background panel to the frame
        frame.add(backgroundPanel);
    }

    public static void main(String[] args) {
        new Role().show();
    }
}
