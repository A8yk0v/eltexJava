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
    private long timeout_CompletedCheck;
    private Thread thread;

    public CompletedCheck(Orders<Order> orders) {
        this.orders = orders;
        this.timeout_CompletedCheck = GlobalConsts.IN_COMPLETEDCHECK_TIMEOUT;

        thread = new Thread(this);
        System.out.println("CompletedCheck object start, id=" + thread.getId());
        thread.start();
    }

    public CompletedCheck(Orders<Order> orders, long timeout_CompletedCheck) {
        this.orders = orders;
        this.timeout_CompletedCheck = timeout_CompletedCheck;

        thread = new Thread(this);
        System.out.println("CompletedCheck object start, id=" + thread.getId());
        thread.start();
    }

    @Override
    public void run() {
        try
        {
            while (true) {
                Thread.sleep(timeout_CompletedCheck);

                if (orders.removeOneCompletedOrder()) {
                    System.out.println("CompletedCheck=" + thread.getId() + " - One order - removed");
                }
            }
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }
}
