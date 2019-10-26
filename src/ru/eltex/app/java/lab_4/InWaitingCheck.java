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
    private long timeout_WaitingCheck;
    private Thread thread;

    public InWaitingCheck(Orders<Order> orders) {
        this.orders = orders;
        this.timeout_WaitingCheck = GlobalConsts.IN_WAITINGCHECK_TIMEOUT;

        thread = new Thread(this);
        System.out.println("InWaitingCheck object start, id=" + thread.getId());
        thread.start();
    }

    public InWaitingCheck(Orders<Order> orders, long timeout_WaitingCheck) {
        this.orders = orders;
        this.timeout_WaitingCheck = timeout_WaitingCheck;

        thread = new Thread(this);
        System.out.println("InWaitingCheck object start, id=" + thread.getId());
        thread.start();
    }

    @Override
    public void run() {
        try
        {
            while (true) {
                Thread.sleep(timeout_WaitingCheck);

                if (orders.changeOrderStatus()) {
                    System.out.println("InWaitingCheck=" + thread.getId() + " - One order - completed");
                }
            }
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }
}
