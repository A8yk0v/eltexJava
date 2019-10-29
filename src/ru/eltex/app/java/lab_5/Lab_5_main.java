package ru.eltex.app.java.lab_5;

import ru.eltex.app.java.lab_2.Order;
import ru.eltex.app.java.lab_2.Orders;
import ru.eltex.app.java.lab_4.AutomaticOrderGeneration;
import ru.eltex.app.java.lab_4.CompletedCheck;
import ru.eltex.app.java.lab_4.InWaitingCheck;

import javax.management.MalformedObjectNameException;

public class Lab_5_main {

    public static void main(String[] args) {
        Orders<Order> store = new Orders<>();
        ManagerOrderFile managerOrderFile =
            new ManagerOrderFile("OrdersSaveFile.sev", store.getOrders(), store.getOrdersLock());

        AutomaticOrderGeneration automaticOrderGeneration_1 =
            new AutomaticOrderGeneration(store);

        InWaitingCheck inWaitingCheck_1 = new InWaitingCheck(store);

        CompletedCheck completedCheck_1 = new CompletedCheck(store);


        try {
            Thread.sleep(12000);
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }

        store.printAll();
        managerOrderFile.saveAll();
        managerOrderFile.readAll();
    }
}
