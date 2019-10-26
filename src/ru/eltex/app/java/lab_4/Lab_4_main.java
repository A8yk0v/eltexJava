package ru.eltex.app.java.lab_4;

import ru.eltex.app.java.lab_2.Order;
import ru.eltex.app.java.lab_2.Orders;

public class Lab_4_main {
    public static void main(String[] args) {
        Orders<Order> store = new Orders<>();

        AutomaticOrderGeneration automaticOrderGeneration_1 =
                new AutomaticOrderGeneration(store);

        //AutomaticOrderGeneration automaticOrderGeneration_2 =
        //        new AutomaticOrderGeneration(store, 5000L);

        InWaitingCheck inWaitingCheck = new InWaitingCheck(store);
        InWaitingCheck inWaitingCheck2 = new InWaitingCheck(store);

        CompletedCheck completedCheck = new CompletedCheck(store);
        CompletedCheck completedCheck2 = new CompletedCheck(store);

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
