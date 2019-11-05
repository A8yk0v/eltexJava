package ru.eltex.app.java.lab_5;

import ru.eltex.app.java.GlobalConsts;
import ru.eltex.app.java.lab_2.Order;

import java.io.*;
import java.util.PriorityQueue;
import java.util.Scanner;
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

    protected FileOutputStream fileOutputStream;
    protected ObjectOutputStream objectOutputStream;
    protected FileInputStream fileInputStream;
    protected ObjectInputStream objectInputStream;

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

    protected void inputInit() {
        try {
            fileInputStream = new FileInputStream(save_file);
            objectInputStream = new ObjectInputStream(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void inputClose() {
        try {
            fileInputStream.close();
            objectInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void outputInit() {
        try {
            fileOutputStream = new FileOutputStream(save_file);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void outputClose() {
        try {
            fileOutputStream.close();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
