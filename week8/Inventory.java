package week8;

public class Inventory {
    public boolean checkStock(String itemCode) {
        return true;
    }

    public void reserve(String itemCode) {
    }

    public void release(String productId) {
        System.out.println("Released hold on product: " + productId);
    }
}
