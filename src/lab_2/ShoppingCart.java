package lab_2;

import lab_1.Device;

import java.util.*;

public class ShoppingCart {

    private List<Device> cart;
    Set<Integer> idTable = new TreeSet<>();

    ShoppingCart() {
        cart = new ArrayList<>();
    }

    public void add(Device dev) {
        cart.add(dev);
        idTable.add(dev.getId());
    }

    public void delete(Device dev) {
        cart.remove(dev);
        idTable.remove(dev);
    }

    public void printAll() {
        for ( Device dev: cart) {
            System.out.println("---------");
            dev.read();
        }
        System.out.println("idTable.size()= " + idTable.size());
    }

    public int getCountItem() {
        return cart.size();
    }

    public Device getDeviceOnID(int id) {
        for (Device dev: cart) {
            if ( dev.getId() == id )
                return dev;
        }
        return null;
    }

    public boolean isHaveDevice(int id) {
        return idTable.contains(id);
    }
}
