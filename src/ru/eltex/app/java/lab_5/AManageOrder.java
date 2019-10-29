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

    public AManageOrder(String save_file, PriorityQueue<Order> orders, Lock ordersLock) {
        this.orders = orders;
        this.ordersLock = ordersLock;
        try {
            fileOutputStream = new FileOutputStream(save_file);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            fileInputStream = new FileInputStream(save_file);
            objectInputStream = new ObjectInputStream(fileInputStream);
        }
        catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
