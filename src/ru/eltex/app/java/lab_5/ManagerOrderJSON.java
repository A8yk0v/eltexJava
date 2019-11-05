package ru.eltex.app.java.lab_5;

import com.google.gson.Gson;
import ru.eltex.app.java.lab_2.Order;

import java.io.EOFException;
import java.io.IOException;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.concurrent.locks.Lock;

public class ManagerOrderJSON extends AManageOrder {

    private Gson gson;

    public ManagerOrderJSON(String save_file, PriorityQueue<Order> orders, Lock ordersLock) {
        super(save_file, orders, ordersLock);

        gson = new Gson();
    }

    @Override
    public Order readById(int id) {
        super.inputInit();
        try {
            while (true) {
                String orderInJSON = (String) objectInputStream.readObject();
                Order tmp = gson.fromJson(orderInJSON, Order.class);
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

    @Override
    public PriorityQueue<Order> readAll() {
        super.inputInit();
        PriorityQueue<Order> orders_tmp = new PriorityQueue<>();
        String orderInJSON;
        try {
            while(true) {
                orderInJSON = (String) objectInputStream.readObject();
                Order order = gson.fromJson(orderInJSON, Order.class);
                orders_tmp.add( order );
            }
        }
        catch (EOFException e) {}
        catch (Exception e) {
            System.out.println(e.toString());
        }
        super.inputClose();
        System.out.println("ManagerOrderJSON.readAll() - completed");
        return orders_tmp;
    }

    @Override
    public void saveAll() {
        ordersLock.lock();
        super.outputInit();

        try {
            for (Order item: orders) {
                String orderInJSON = gson.toJson(item);
                objectOutputStream.writeObject(orderInJSON);
            }
        }
        catch (IOException e) {
            System.out.println(e.toString());
        }
        finally {
            ordersLock.unlock();
            super.outputClose();
        }
        System.out.println("ManagerOrderJSON.saveAll() - completed");
    }
}
