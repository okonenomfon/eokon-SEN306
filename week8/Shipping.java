package week8;

public class Shipping {
    public String createLabel(String deliveryAddress) {
        return "TRACKING-12345"; 
    }

    public void schedulePickup(String label) {
    }

    public boolean isAvailable() {
        return true;
    }
}