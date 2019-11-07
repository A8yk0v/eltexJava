package ru.eltex.app.java.lab_5;

import com.google.gson.Gson;
import ru.eltex.app.java.lab_2.Order;

import java.io.*;
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
        try (FileInputStream fileInputStream = new FileInputStream(save_file);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

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
        return null;
    }

    @Override
    public PriorityQueue<Order> readAll() {
        PriorityQueue<Order> orders_tmp = new PriorityQueue<>();
        try (FileInputStream fileInputStream = new FileInputStream(save_file);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            while(true) {
                String orderInJSON = (String) objectInputStream.readObject();
                Order order = gson.fromJson(orderInJSON, Order.class);
                orders_tmp.add( order );
            }
        }
        catch (EOFException e) {}
        catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println("ManagerOrderJSON.readAll() - completed");
        return orders_tmp;
    }

    @Override
    public void saveAll() {
        ordersLock.lock();
        try (FileOutputStream fileOutputStream = new FileOutputStream(save_file);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

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
        }
        System.out.println("ManagerOrderJSON.saveAll() - completed");
    }
}
