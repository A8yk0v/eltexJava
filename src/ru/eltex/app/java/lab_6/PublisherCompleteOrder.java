package ru.eltex.app.java.lab_6;

import ru.eltex.app.java.lab_2.Order;

import java.util.ArrayList;

/**
 * Класс реализующи паттерн "Наблюдатель"
 * Оповещающий подписчиков о наступлении события
 * завершение заказа.
 * Событие завешения заказа генерируется в классе InWaitingCheck
 */
public class PublisherCompleteOrder {
    private ArrayList<IListenerComplete> listeners;

    public PublisherCompleteOrder() {
        listeners = new ArrayList<>();
    }

    public void addListener(IListenerComplete listener) {
        listeners.add(listener);
    }

    public void completedOrder(Order order) {
        synchronized (this.getClass()) {
            for (IListenerComplete iter : listeners) {
                iter.complete(order);
            }
        }
    }
}
