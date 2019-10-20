package ru.eltex.app.java.lab_3;

import ru.eltex.app.java.lab_1.Device;
import ru.eltex.app.java.lab_2.DeviceFactory;
import ru.eltex.app.java.lab_2.ShoppingCart;

public class lab_3_main {
    public static void main(String[] args) {
        ShoppingCart<Device> cart = new ShoppingCart<>();

        cart.add( DeviceFactory.getInstance().getDevice() );
        cart.add( DeviceFactory.getInstance().getDevice() );
        cart.add( DeviceFactory.getInstance().getDevice() );

        cart.printAll();
    }
}
