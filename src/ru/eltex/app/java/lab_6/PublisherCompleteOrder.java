package ru.eltex.app.java.lab_6;

import ru.eltex.app.java.lab_2.Order;

import java.util.ArrayList;

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
