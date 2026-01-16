package src;
public class Withdraw extends UpDownMoney {
    
    // Le constructeur reçoit les informations de l'utilisateur connecté
    public Withdraw(String name, double balance, int id) {
        // Appelle le constructeur de UpDownMoney avec les 6 paramètres requis
        super("Withdraw", "Images\\WithdrawBack.jpg", "Withdraw Money", name, balance, id);
    }

    public static void main(String[] args) {
        // Pour un test isolé, on passe des valeurs par défaut
        new Withdraw("Test User", 500.0, 1).show();
    }
}