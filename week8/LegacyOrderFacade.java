package week8;

// DO NOT MODIFY THIS CLASS (As per instructions)
class LegacyOrderProcessor {
    public void processOrder(String customerEmail, String itemCode, double amount, String deliveryAddress) {
        Inventory inv = new Inventory();
        Payment pay = new Payment();
        Shipping ship = new Shipping();
        Email email = new Email();

        if (!inv.checkStock(itemCode)) {
            System.out.println("Out of stock");
            return;
        }
        if (!pay.charge(customerEmail, amount)) {
            System.out.println("Payment fail");
            return;
        }
        inv.reserve(itemCode);
        String label = ship.createLabel(deliveryAddress);
        ship.schedulePickup(label);
        email.send(customerEmail, "Order", "Shipped");
        System.out.println("Order complete");
    }
}

public class LegacyOrderFacade {
    // Wrap the legacy processor using composition
    private LegacyOrderProcessor processor;

    public LegacyOrderFacade() {
        this.processor = new LegacyOrderProcessor();
    }

    // Clean simplified interface for the client
    public void placeOrder(String customerEmail, String itemCode, double amount, String deliveryAddress) {
        processor.processOrder(customerEmail, itemCode, amount, deliveryAddress);
    }
}
