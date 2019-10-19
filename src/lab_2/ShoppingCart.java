package lab_2;

import lab_1.Device;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShoppingCart {

    private List<Device> cart;

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
            System.out.println("---------");
            dev.read();
        }
    }

    public int getCountItem() {
        return cart.size();
    }

    public Device getDeviceOnID(int id) {
        for (Device dev: cart) {
            if ( dev.getId() == id )
                return dev;
            else
                return null;
        }
        return null;
    }
}
