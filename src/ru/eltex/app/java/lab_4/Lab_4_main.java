package ru.eltex.app.java.lab_4;

import ru.eltex.app.java.lab_2.Order;
import ru.eltex.app.java.lab_2.Orders;

public class Lab_4_main {
    public static void main(String[] args) {
        Orders<Order> store = new Orders<>();

        AutomaticOrderGeneration automaticOrderGeneration_1 = new AutomaticOrderGeneration(store);
        Thread t1 = new Thread(automaticOrderGeneration_1);
        t1.start();

        AutomaticOrderGeneration automaticOrderGeneration_2 = new AutomaticOrderGeneration(store, 5000L);
        Thread t1_2 = new Thread(automaticOrderGeneration_2);
        t1_2.start();

        InWaitingCheck inWaitingCheck = new InWaitingCheck(store);
        Thread t2 = new Thread(inWaitingCheck);
        t2.start();

        CompletedCheck completedCheck = new CompletedCheck(store);
        Thread t3 = new Thread(completedCheck);
        t3.start();

        while (true) {
            try {
                    Thread.sleep(1000);

                    store.printAll();
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        }
    }
}
