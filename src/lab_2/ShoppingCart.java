package lab_2;

import lab_1.Device;

import java.util.ArrayList;

public class ShoppingCart {

    private ArrayList<Device> cart;

    ShoppingCart() {
        cart = new ArrayList<>();
    }

    public void add(Device dev) {
        cart.add(dev);
    }

    public void delete(Device dev) {
        // Как быть???
        cart.remove(dev);
    }

    public void printAll() {
        for ( Device dev: cart) {
            dev.read();
        }
    }

    public int getCountItem() {
        return cart.size();
    }
}
