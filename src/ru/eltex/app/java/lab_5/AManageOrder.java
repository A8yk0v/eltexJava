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

//    protected FileOutputStream fileOutputStream_end;
//    protected ObjectOutputStream objectOutputStream_end;

    protected String save_file;

    public AManageOrder(String save_file, PriorityQueue<Order> orders, Lock ordersLock) {
        this.orders = orders;
        this.ordersLock = ordersLock;
        this.save_file = save_file;
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

//    protected void outputInitEnd() {
//        try {
//            fileOutputStream_end = new FileOutputStream(save_file, true);
//            objectOutputStream_end = new ObjectOutputStream(fileOutputStream_end);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    protected void outputCloseEnd() {
//        try {
//            fileOutputStream_end.close();
//            objectOutputStream_end.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
