package ru.eltex.app.java.lab_5;

import ru.eltex.app.java.lab_2.Order;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Lock;

/**
 * Класс AManageOrder хранит ссылку на коллекцию orders
 * из класса Orders и объект блокировки этого класса.
 *
 * Производит по требванию запись в файл/чтение из файла
 * элементов коллекции
 */

public abstract class AManageOrder implements IOrder {

    protected PriorityQueue<Order> orders;
    protected Lock ordersLock;
    protected String save_file;

    public AManageOrder(String save_file, PriorityQueue<Order> orders, Lock ordersLock) {
        this.orders = orders;
        this.ordersLock = ordersLock;
        this.save_file = save_file;
    }

    @Override
    public void saveById(int id) {
        ordersLock.lock();
        for (Order item: orders) {
            if (item.getId() == id) {
                try {
                    PriorityQueue<Order> orders_tmp = readAll();
                    if ( !orders_tmp.contains(item) ) {
                        orders_tmp.add(item);
                        PriorityQueue<Order> orders_save = orders;
                        orders = orders_tmp;
                        saveAll();
                        orders = orders_save;
                    }
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
                ordersLock.unlock();
                return;
            }
        }
        ordersLock.unlock();
    }
}
