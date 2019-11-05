package ru.eltex.app.java.lab_5;

import ru.eltex.app.java.lab_2.Order;

import java.io.*;
import java.util.PriorityQueue;
import java.util.concurrent.locks.Lock;

public class ManagerOrderFile extends AManageOrder {

    public ManagerOrderFile(String save_file, PriorityQueue<Order> orders, Lock ordersLock) {
        super(save_file, orders, ordersLock);
    }

    @Override
    public Order readById(int id) {
        super.inputInit();
        try {
            while (true) {
                Order tmp = (Order)objectInputStream.readObject();
                if ( tmp.getId() == id ) {
                    return tmp;
                }
            }
        }
        catch (EOFException e) {}
        catch (Exception e) {
            System.out.println(e.toString());
        }
        finally {
            super.inputClose();
        }
        return null;
    }

    @Override
    public void saveById(int id) {
        ordersLock.lock();
//        super.outputInitEnd();
        for (Order item: orders) {
            if (item.getId() == id) {
                try {
                    // В таком варианте снова записывается заголовок потока
//                    objectOutputStream_end.writeObject(item);
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
//        super.outputCloseEnd();
        ordersLock.unlock();
    }

    @Override
    public PriorityQueue<Order> readAll() {
        super.inputInit();
        PriorityQueue<Order> orders_tmp = new PriorityQueue<>();
        try {
            while(true) {
                orders_tmp.add( (Order)objectInputStream.readObject() );
            }
        }
        catch (EOFException e) {}
        catch (Exception e) {
            System.out.println(e.toString());
        }
        super.inputClose();
        return orders_tmp;
    }

    @Override
    public void saveAll() {
        ordersLock.lock();
        super.outputInit();
        try {
            for (Order item: orders) {
                objectOutputStream.writeObject(item);
            }
        }
        catch (IOException e) {
            System.out.println(e.toString());
        }
        finally {
            ordersLock.unlock();
            super.outputClose();
        }
        System.out.println("ManagerOrderFile completed");
    }
}
