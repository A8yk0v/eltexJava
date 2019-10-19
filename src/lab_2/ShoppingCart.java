package lab_2;

import lab_1.Device;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShoppingCart<T extends Device> {

    private List<T> cart;

    public ShoppingCart() {
        cart = new ArrayList<>();
    }

    public void add(T dev) {
        cart.add(dev);
    }

    public void delete(T dev) {
        // Как быть???
        cart.remove(dev);
    }

    public void printAll() {
        for ( T dev: cart) {
            System.out.println("---------");
            dev.read();
        }
    }

    public int getCountItem() {
        return cart.size();
    }

    public T getDeviceOnID(int id) {
        for (T dev: cart) {
            if ( dev.getId() == id )
                return dev;
        }
        return null;
    }
}
