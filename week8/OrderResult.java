package week8;

public class OrderResult {
    private final boolean success;
    private final String trackingNumber;
    private final String message;

    public OrderResult(boolean success, String trackingNumber, String message) {
        this.success = success;
        this.trackingNumber = trackingNumber;
        this.message = message;
    }

    public boolean isSuccess() { return success; }
    public String getTrackingNumber() { return trackingNumber; }
    public String getMessage() { return message; }
}

class TaxCalculator {
    public double calculateTax(double price, String address) {
        // Hardcode: "CA" -> 8%, else 0%
        if (address != null && address.contains("CA")) {
            return price * 0.08;
        }
        return 0.0;
    }
}

class Logger {
    public void logAttempt(String userId, boolean success, String message) {
        System.out.println("[" + System.currentTimeMillis() + "] User: " + userId + 
                           " | Success: " + success + " | Msg: " + message);
    }
}
