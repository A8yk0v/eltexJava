package ru.eltex.app.java.lab_4;

import ru.eltex.app.java.GlobalConsts;
import ru.eltex.app.java.lab_2.Order;
import ru.eltex.app.java.lab_2.Orders;
import ru.eltex.app.java.lab_6.IListenerComplete;

import java.util.Queue;

/**
 * Класс в потоке проверяет заказы по статусу «обработан».
 * Если заказ обнаружен в этом состоянии, заказ удаляется из списка.
 */

public class CompletedCheck extends ACheck implements IListenerComplete {

    private Orders<Order> orders;
    private long timeout_CompletedCheck;

    public CompletedCheck(Orders<Order> orders) {
        this.orders = orders;
        this.timeout_CompletedCheck = GlobalConsts.IN_COMPLETEDCHECK_TIMEOUT;
        System.out.println("CompletedCheck object start, id=" + Thread.currentThread().getId());
    }

    public CompletedCheck(Orders<Order> orders, long timeout_CompletedCheck) {
        this.orders = orders;
        this.timeout_CompletedCheck = timeout_CompletedCheck;
        System.out.println("CompletedCheck object start, id=" + Thread.currentThread().getId());
    }

    @Override
    public void run() {
        try
        {
            while (true) {
                Thread.sleep(timeout_CompletedCheck);

                if (orders.removeOneCompletedOrder()) {
                    System.out.println("CompletedCheck=" + Thread.currentThread().getId() + " - One order - removed");
                }
            }
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void complete(Order order) {
        if ( orders.removeCompletedOrder(order) ) {
            System.out.println("CompletedCheck=" + Thread.currentThread().getId() + " - One order - removed");
        }
    }
}
