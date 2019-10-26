package ru.eltex.app.java.lab_4;

import ru.eltex.app.java.GlobalConsts;
import ru.eltex.app.java.lab_1.Device;
import ru.eltex.app.java.lab_2.*;

import java.util.Random;

/**
 * Класс в потоке через каждый установленный интервал времени
 * создает в коллекции Orders новый заказ
 */

public class AutomaticOrderGeneration implements Runnable {

    private Orders<Order> orders;
    private Random random = new Random();

    public AutomaticOrderGeneration(Orders<Order> orders) {
        this.orders = orders;
    }

    @Override
    public void run() {
        try
        {
            while (true) {
                Thread.sleep(GlobalConsts.IN_AUTOMATICORDERGENERATION_TIMEOUT);

                Credentials credentials = CredentialsFactory.getInstance().getCredentials();
                ShoppingCart<Device> cart = new ShoppingCart<>();
                int count_device = random.nextInt(GlobalConsts.MAX_DEVICE_COUNT_IN_CARTS);
                for (int i = 0; i < count_device; i++) {
                    cart.add(DeviceFactory.getInstance().getDevice());
                }
                orders.shop(cart, credentials);

                System.out.println("Order generation");
            }
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }
}
