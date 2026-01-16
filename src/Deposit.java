package src;
public class Deposit extends UpDownMoney {
    
    // Le constructeur doit maintenant accepter les 3 paramètres de session
    public Deposit(String name, double balance, int id) {
        // On transmet les 6 paramètres requis par le nouveau constructeur de UpDownMoney
        super("Deposit", "Images\\DepositBack.jpg", "Deposit Money", name, balance, id);
    }

    // Le main de test doit aussi être mis à jour (pour éviter les erreurs de compilation)
    public static void main(String[] args) {
        new Deposit("Test User", 0.0, 1).show();
    }
}