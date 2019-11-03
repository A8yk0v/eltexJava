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
    private long timeout_automicOrderGeneration;

    public AutomaticOrderGeneration(Orders<Order> orders) {

        this.orders = orders;
        timeout_automicOrderGeneration = GlobalConsts.IN_AUTOMATICORDERGENERATION_TIMEOUT;
    }
    public AutomaticOrderGeneration(Orders<Order> orders, long timeout_automicOrderGeneration) {

        this.orders = orders;
        this.timeout_automicOrderGeneration = timeout_automicOrderGeneration;
    }

    @Override
    public void run() {
        try
        {
            while ( !Thread.currentThread().isInterrupted() ) {
                Thread.sleep(timeout_automicOrderGeneration);

                orders.addOrder( getOrder() );

                System.out.println("AutomaticOrderGeneration=" +
                                        Thread.currentThread().getId() +
                                        " - Order generation");
            }
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }

    public Order getOrder() {
        Credentials credentials = CredentialsFactory.getInstance().getCredentials();
        ShoppingCart<Device> cart = new ShoppingCart<>();
        int count_device = random.nextInt(GlobalConsts.MAX_DEVICE_COUNT_IN_CARTS + 1);
        for (int i = 0; i < count_device; i++) {
            cart.add(DeviceFactory.getInstance().getDevice());
        }
        return new Order(cart, credentials);
    }
}
