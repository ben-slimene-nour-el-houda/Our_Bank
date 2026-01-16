package src;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AdminAllAccounts extends Initial_interface {
    private String adminName;

    public AdminAllAccounts(String adminName) {
        super("All Client Accounts", "Images/AdminBack.jpg");
        this.adminName = adminName;
        backgroundPanel.setLayout(new BorderLayout(10, 10));

        // --- TITRE ---
        JLabel titleLabel = new JLabel("Bank Database - All Accounts", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        titleLabel.setForeground(Color.WHITE);
        backgroundPanel.add(titleLabel, BorderLayout.NORTH);

        // --- TABLEAU (JTable) ---
        String[] columnNames = {"ID", "Nom", "CIN", "RIB", "Solde (DT)", "Date Ouverture"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        
        // Chargement des données SQL
        try (Connection conn = Base.getConnection()) {
            String query = "SELECT c.id_client, c.nom, c.cin, b.RIB, b.solde, b.Date_Ouverture " +
                           "FROM clients c JOIN Compte_Bancaire b ON c.id_client = b.id_client";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id_client"),
                    rs.getString("nom"),
                    rs.getString("cin"),
                    rs.getString("RIB"),
                    rs.getDouble("solde") + " DT",
                    rs.getDate("Date_Ouverture")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage());
        }

        JScrollPane scrollPane = new JScrollPane(table);
        backgroundPanel.add(scrollPane, BorderLayout.CENTER);

        // --- BOUTON RETOUR ---
        JButton backBtn = new JButton("Back to Dashboard");
        backBtn.addActionListener(e -> {
            new AdminInterface(adminName).show();
            frame.dispose();
        });
        backgroundPanel.add(backBtn, BorderLayout.SOUTH);

        frame.add(backgroundPanel);
    }
}