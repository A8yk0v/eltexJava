package lab_2;

import lab_1.Device;

import java.util.*;

public class ShoppingCart<T extends Device> {

    private List<T> cart;
    Set<Integer> idTable = new TreeSet<>();

    public ShoppingCart() {
        cart = new ArrayList<>();
    }

    public void add(T dev) {
        cart.add(dev);
        idTable.add(dev.getId());
    }

    public void delete(T dev) {
        cart.remove(dev);
        idTable.remove(dev);
    }

    public void printAll() {
        for ( T dev: cart) {
            System.out.println("---------");
            dev.read();
        }
        System.out.println("idTable.size()= " + idTable.size());
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

    public boolean isHaveDevice(int id) {
        return idTable.contains(id);
    }
}
