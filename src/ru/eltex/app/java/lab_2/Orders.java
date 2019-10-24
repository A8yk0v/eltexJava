package ru.eltex.app.java.lab_2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;

public class Orders<T extends Order> {

    private PriorityQueue<T> orders;
    Map<Long, Order> warehouse_orders = new HashMap<>();

    Orders() {
        orders = new PriorityQueue<>();
    }

    public void shop(ShoppingCart cart, Credentials credentials) {
        T order = (T) new Order(cart, credentials);
        warehouse_orders.put(order.getCreationTime().getTime(), order);
        orders.add(order);
    }

    public void clean() {
        for (Iterator<T> iter = orders.iterator(); iter.hasNext(); ) {
            T element = iter.next();
            if ( element.isCompleted() && element.isTimeout() ) {
                warehouse_orders.remove( element.getCreationTime().getTime() );
                iter.remove();
                break;
            }
        }
    }

    public void printAll() {
        for ( T dev: orders) {
            dev.read();
        }

        System.out.println("warehouse_orders.size()= " + warehouse_orders.size());
    }

    public synchronized boolean changeOrderStatus() {
        for (Order value : warehouse_orders.values()) {
            if ( value.isTimeout() ) {
                value.executeOrder();
                return true;
            }
        }
        return false;
    }

    public synchronized boolean removeOneCompletedOrder() {
        for (Map.Entry<Long, Order> pair: warehouse_orders.entrySet())
        {
            if ( pair.getValue().isCompleted() ) {
                warehouse_orders.remove(pair.getKey());
                return true;
            }
        }
        return false;
    }
}
