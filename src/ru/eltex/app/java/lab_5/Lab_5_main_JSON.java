package ru.eltex.app.java.lab_5;

import ru.eltex.app.java.GlobalConsts;
import ru.eltex.app.java.lab_2.Order;
import ru.eltex.app.java.lab_2.Orders;
import ru.eltex.app.java.lab_4.AutomaticOrderGeneration;

import java.util.PriorityQueue;

public class Lab_5_main_JSON {

    public static void main(String[] args) {
        Orders<Order> store = new Orders<>();
        ManagerOrderJSON managerOrderJSON =
                new ManagerOrderJSON(GlobalConsts.ORDERS_SAVE_FILE_SEV, store.getOrders(), store.getOrdersLock());
        AutomaticOrderGeneration automaticOrderGeneration =
                new AutomaticOrderGeneration(store);

        for (int i = 0; i < 4; i++) {
            store.addOrder( automaticOrderGeneration.getOrder() );
        }

        store.printAll();
        store.printAll_descriprion();
        managerOrderJSON.saveAll();

        PriorityQueue<Order> tmp = managerOrderJSON.readAll();
        for (Order item : tmp) {
            item.read();
        }
    }
}
