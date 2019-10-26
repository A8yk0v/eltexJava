package ru.eltex.app.java.lab_4;

import ru.eltex.app.java.lab_2.Order;
import ru.eltex.app.java.lab_2.Orders;

public class Lab_4_main {
    public static void main(String[] args) {
        Orders<Order> store = new Orders<>();

        AutomaticOrderGeneration automaticOrderGeneration = new AutomaticOrderGeneration(store);
        Thread t1 = new Thread(automaticOrderGeneration);
        t1.start();

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
