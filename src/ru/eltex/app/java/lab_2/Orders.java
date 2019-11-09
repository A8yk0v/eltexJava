package ru.eltex.app.java.lab_2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Orders<T extends Order> {

    private PriorityQueue<T> orders;
    private Map<Long, Order> warehouse_orders;

    // Блокировка для параллельной работы коллекции
    // Блокировка заменила synchronized, т.к. нужно было передать
    // блокировку в класс AManageOrder
    private Lock ordersLock;

    public Orders() {
        orders = new PriorityQueue<>();
        warehouse_orders = new HashMap<>();
        ordersLock = new ReentrantLock();
    }

    public PriorityQueue<T> getOrders() {
        return orders;
    }

    public Lock getOrdersLock() {
        return ordersLock;
    }

    public void shop(ShoppingCart cart, Credentials credentials) {
        ordersLock.lock();

        T order = (T) new Order(cart, credentials);
        warehouse_orders.put(order.getCreationTime().getTime(), order);
        orders.add(order);

        ordersLock.unlock();
    }

    public void addOrder(T order) {
        ordersLock.lock();
        orders.add( order );
        ordersLock.unlock();
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
//        for ( T dev: orders) {
//            dev.read();
//        }

        //System.out.println("warehouse_orders.size()= " + warehouse_orders.size());
        System.out.println("orders.size()= " + orders.size());
    }

    public void printAll_descriprion() {
        ordersLock.lock();
        System.out.println("warehouse_orders.size()= " + warehouse_orders.size());
        for ( T dev: orders) {
            dev.read();
        }
        ordersLock.unlock();
    }

    public Order changeOrderStatus() {
        ordersLock.lock();

        for (Order value : orders) {
            if ( !value.isCompleted() ) {
                value.executeOrder();
                ordersLock.unlock();
                return value;
            }
        }
        ordersLock.unlock();
        return null;
    }

    public boolean removeOneCompletedOrder() {
        ordersLock.lock();

        for (Map.Entry<Long, Order> pair: warehouse_orders.entrySet())
        {
            if ( pair.getValue().isCompleted() ) {
                warehouse_orders.remove(pair.getKey());
                ordersLock.unlock();
                return true;
            }
        }
        ordersLock.unlock();
        return false;
    }

    public boolean removeCompletedOrder(Order order) {
        ordersLock.lock();
        boolean flag_remove = orders.remove(order);
        ordersLock.unlock();
        return flag_remove;
    }
}
