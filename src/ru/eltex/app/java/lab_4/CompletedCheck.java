package ru.eltex.app.java.lab_4;

import ru.eltex.app.java.GlobalConsts;
import ru.eltex.app.java.lab_2.Order;
import ru.eltex.app.java.lab_2.Orders;

import java.util.Queue;

/**
 * Класс в потоке проверяет заказы по статусу «обработан».
 * Если заказ обнаружен в этом состоянии, заказ удаляется из списка.
 */

public class CompletedCheck extends ACheck {

    private Orders<Order> orders;

    public CompletedCheck(Orders<Order> orders) {
        this.orders = orders;
    }

    @Override
    public void run() {
        try
        {
            while (true) {
                Thread.sleep(GlobalConsts.IN_COMPLETEDCHECK_TIMEOUT);

                if (orders.removeOneCompletedOrder()) {
                    System.out.println("One order - removed");
                }
            }
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }
}
