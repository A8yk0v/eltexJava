package ru.eltex.app.java.lab_5;

import ru.eltex.app.java.lab_2.Order;
import ru.eltex.app.java.lab_2.Orders;
import ru.eltex.app.java.lab_4.AutomaticOrderGeneration;

public class Lab_5_main_JSON {
    public static void main(String[] args) {
        Orders<Order> store = new Orders<>();
        ManagerOrderJSON managerOrderJSON =
                new ManagerOrderJSON("OrdersSaveFile.sev", store.getOrders(), store.getOrdersLock());
        AutomaticOrderGeneration automaticOrderGeneration =
                new AutomaticOrderGeneration(store);

        for (int i = 0; i < 4; i++) {
            store.addOrder( automaticOrderGeneration.getOrder() );
        }

        store.printAll();
        managerOrderJSON.saveAll();
        managerOrderJSON.readAll();
    }
}
