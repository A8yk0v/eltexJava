package lab_3;

import lab_1.Device;
import lab_2.DeviceFactory;
import lab_2.ShoppingCart;

public class lab_3_main {
    public static void main(String[] args) {
        ShoppingCart<Device> cart = new ShoppingCart<>();

        cart.add( DeviceFactory.getInstance().getDevice() );
        cart.add( DeviceFactory.getInstance().getDevice() );
        cart.add( DeviceFactory.getInstance().getDevice() );

        cart.printAll();
    }
}
