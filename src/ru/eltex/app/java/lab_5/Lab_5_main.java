package ru.eltex.app.java.lab_5;

import ru.eltex.app.java.GlobalConsts;
import ru.eltex.app.java.lab_2.*;
import ru.eltex.app.java.lab_4.AutomaticOrderGeneration;

import java.util.PriorityQueue;

public class Lab_5_main {

    public static void main(String[] args) {
        Orders<Order> store = new Orders<>();
        ManagerOrderFile managerOrderFile =
            new ManagerOrderFile(GlobalConsts.ORDERS_SAVE_FILE_SEV, store.getOrders(), store.getOrdersLock());

        AutomaticOrderGeneration automaticOrderGeneration_1 =
            new AutomaticOrderGeneration(store);
        Thread th1 = new Thread(automaticOrderGeneration_1);
        th1.start();

        //InWaitingCheck inWaitingCheck_1 = new InWaitingCheck(store);

        //CompletedCheck completedCheck_1 = new CompletedCheck(store);


        try {
            Thread.sleep(10000);
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }

        th1.interrupt();

        store.printAll();
        managerOrderFile.saveAll();
        store.printAll_descriprion();
        //managerOrderFile.saveAll();
        //managerOrderFile.readAll();

        PriorityQueue<Order> test = managerOrderFile.readAll();

        Order another_one_order = automaticOrderGeneration_1.getOrder();
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

//        th1 = new Thread(automaticOrderGeneration_1);
//        th1.start();

        System.out.println("The end(((");
    }
}
