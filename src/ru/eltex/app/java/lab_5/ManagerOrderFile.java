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
        try (FileInputStream fileInputStream = new FileInputStream(save_file);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

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
        return null;
    }

    @Override
    public PriorityQueue<Order> readAll() {
        PriorityQueue<Order> orders_tmp = new PriorityQueue<>();
        try (FileInputStream fileInputStream = new FileInputStream(save_file);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            while (true) {
                orders_tmp.add((Order) objectInputStream.readObject());
            }

        } catch (EOFException e) {
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return orders_tmp;
    }

    @Override
    public void saveAll() {
        ordersLock.lock();
        try (FileOutputStream fileOutputStream = new FileOutputStream(save_file);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

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
