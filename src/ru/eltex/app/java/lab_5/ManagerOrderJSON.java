package ru.eltex.app.java.lab_5;

import ru.eltex.app.java.lab_2.Order;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Lock;

public class ManagerOrderJSON extends AManageOrder {

    public ManagerOrderJSON(String save_file, PriorityQueue<Order> orders, Lock ordersLock) {
        super(save_file, orders, ordersLock);
    }

    @Override
    public Order readById(int id) {
        return null;
    }

    @Override
    public void saveById(int id) {

    }

    @Override
    public PriorityQueue<Order> readAll() {
        return null;
    }

    @Override
    public void saveAll() {

    }
}
