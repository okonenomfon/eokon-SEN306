package week8;

public class Payment {
    public boolean charge(String customerEmail, double amount) {
        return true;
    }

    public void refund(String userId, double amount) {
        System.out.println("Refunded $" + amount + " to user: " + userId);
    }
}
