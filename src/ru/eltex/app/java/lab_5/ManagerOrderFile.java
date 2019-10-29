package ru.eltex.app.java.lab_5;

import ru.eltex.app.java.lab_2.Order;

import java.io.*;
import java.util.PriorityQueue;
import java.util.concurrent.locks.Lock;

public class ManagerOrderFile extends AManageOrder {

    public ManagerOrderFile(String save_file, PriorityQueue<Order> orders, Lock ordersLock) {
        super(save_file, orders, ordersLock);

        // TODO Что точно должно быть!!!:c
        Object object;
    }

    @Override
    public Order readById(int id) {
        try {
            int count = objectInputStream.readInt();

            for (int i = 0; i < count; i++) {
                Order tmp = (Order)objectInputStream.readObject();

                if ( tmp.getId() == id ) {
                    return tmp;
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }

        return null;
    }

    @Override
    public void saveById(int id) {

    }

    @Override
    public PriorityQueue<Order> readAll() {

        PriorityQueue<Order> orders_tmp = new PriorityQueue<>();
        try {
            int count = objectInputStream.readInt();

            for (int i = 0; i < count; i++) {
                orders_tmp.add( (Order)objectInputStream.readObject() );
            }
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }

        return orders_tmp;
    }

    @Override
    public void saveAll() {
        ordersLock.lock();
        try {
            objectOutputStream.writeInt(orders.size());

            for (Order item: orders) {
                objectOutputStream.writeObject(item);
            }
        }
        catch (IOException e) {
            System.out.println(e.toString());
        }
        finally {
            ordersLock.unlock();
        }

        System.out.println("ManagerOrderFile completed");
    }
}
