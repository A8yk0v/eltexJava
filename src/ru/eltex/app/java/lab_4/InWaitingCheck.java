package ru.eltex.app.java.lab_4;

import ru.eltex.app.java.GlobalConsts;
import ru.eltex.app.java.lab_2.Order;
import ru.eltex.app.java.lab_2.Orders;

/**
 * Класс в потоке с определенным интервалом делает проверку коллекции
 * и проверяет заказы по статусу «в ожидании».
 * Если заказ обнаружен в этом состоянии,
 * то меняется статус заказа на состояние «обработан».
 */

public class InWaitingCheck extends ACheck {

    private Orders<Order> orders;

    public InWaitingCheck(Orders<Order> orders) {
        this.orders = orders;
    }

    @Override
    public void run() {
        try
        {
            while (true) {
                Thread.sleep(GlobalConsts.IN_WAITINGCHECK_TIMEOUT);

                if (orders.changeOrderStatus()) {
                    System.out.println("One order - completed");
                }
            }
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }
}
