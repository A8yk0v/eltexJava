package ru.eltex.app.java.lab_5;

import ru.eltex.app.java.GlobalConsts;
import ru.eltex.app.java.lab_1.Device;
import ru.eltex.app.java.lab_2.*;
import ru.eltex.app.java.lab_4.AutomaticOrderGeneration;
import ru.eltex.app.java.lab_4.CompletedCheck;
import ru.eltex.app.java.lab_4.InWaitingCheck;

import java.util.PriorityQueue;
import java.util.Random;

public class Lab_5_main {

    public static void main(String[] args) {
        Orders<Order> store = new Orders<>();
        ManagerOrderFile managerOrderFile =
            new ManagerOrderFile("OrdersSaveFile.sev", store.getOrders(), store.getOrdersLock());

        AutomaticOrderGeneration automaticOrderGeneration_1 =
            new AutomaticOrderGeneration(store);

        //InWaitingCheck inWaitingCheck_1 = new InWaitingCheck(store);

        //CompletedCheck completedCheck_1 = new CompletedCheck(store);


        try {
            Thread.sleep(10000);
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }

        store.printAll();
        managerOrderFile.saveAll();
        store.printAll_descriprion();
        //managerOrderFile.saveAll();
        //managerOrderFile.readAll();

        PriorityQueue<Order> test = managerOrderFile.readAll();

        Credentials credentials = CredentialsFactory.getInstance().getCredentials();
        ShoppingCart<Device> cart = new ShoppingCart<>();
        int count_device = new Random().nextInt(GlobalConsts.MAX_DEVICE_COUNT_IN_CARTS + 1);
        for (int i = 0; i < count_device; i++) {
            cart.add(DeviceFactory.getInstance().getDevice());
        }
        Order another_one_order = new Order(cart, CredentialsFactory.getInstance().getCredentials());
        System.out.print("new Order.read() - "); another_one_order.read();

        store.getOrdersLock().lock();
            PriorityQueue<Order> test_2 = store.getOrders();
            test_2.add(another_one_order);
        store.getOrdersLock().unlock();

        managerOrderFile.saveById(another_one_order.getId());
        test = managerOrderFile.readAll();
        for (Order item: test) {
            item.read();
        }

        System.out.println("The end(((");
    }
}
