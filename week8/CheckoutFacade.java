package week8;

public class CheckoutFacade {
    private Inventory inventory;
    private Payment payment;
    private Shipping shipping;
    private Email email;
    
    //extensions
    private TaxCalculator taxCalculator;
    private Logger logger;

    public CheckoutFacade() {
        this.inventory = new Inventory();
        this.payment = new Payment();
        this.shipping = new Shipping();
        this.email = new Email();
        this.taxCalculator = new TaxCalculator();
        this.logger = new Logger();
    }

    public OrderResult checkout(String userId, String productId, double price, String address) {
        if (!inventory.checkStock(productId)) {
            logger.logAttempt(userId, false, "Out of stock");
            return new OrderResult(false, null, "Item out of stock");
        }

        double tax = taxCalculator.calculateTax(price, address);
        double totalPrice = price + tax;

        if (!payment.charge(userId, totalPrice)) {
            logger.logAttempt(userId, false, "Payment failed");
            return new OrderResult(false, null, "Payment failed");
        }

        inventory.reserve(productId);

        if (!shipping.isAvailable()) {
            // Rollback: refund and release inventory
            payment.refund(userId, totalPrice);
            inventory.release(productId);
            
            logger.logAttempt(userId, false, "Shipping unavailable - Rolled back");
            return new OrderResult(false, null, "Shipping unavailable. Order refunded.");
        }

        String label = shipping.createLabel(address);
        shipping.schedulePickup(label);

        email.send(userId, "Order Confirmed", "Your total was $" + totalPrice + ".");

        logger.logAttempt(userId, true, "Checkout complete");

        return new OrderResult(true, label, "Order successful");
    }
}
