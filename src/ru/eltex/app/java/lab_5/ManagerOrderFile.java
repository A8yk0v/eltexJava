package ru.eltex.app.java.lab_5;

import ru.eltex.app.java.lab_2.Order;

import java.io.*;
import java.util.PriorityQueue;

public class ManagerOrderFile extends AManageOrder implements Runnable  {

    private PriorityQueue<Order> orders;
    private ObjectOutputStream objectOutputStream;
    FileOutputStream outputStream;

    FileInputStream fileInputStream;
    ObjectInputStream objectInputStream;

    public ManagerOrderFile(String save_file, PriorityQueue<Order> orders) {
        super(save_file);
        this.orders = orders;

        try {
            outputStream = new FileOutputStream(save_file);
            objectOutputStream = new ObjectOutputStream(outputStream);

            fileInputStream = new FileInputStream(save_file);
            objectInputStream = new ObjectInputStream(fileInputStream);

            Thread t = new Thread(this);
            t.start();
        }
        catch (IOException e) {
            System.out.println(e.toString());
        }
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
        try {
            objectOutputStream.writeInt(orders.size());

            for (Order item: orders) {
                objectOutputStream.writeObject(item);
            }
        }
        catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public void run() {
        try {
            while(true) {
                Thread.sleep(15000);

                saveAll();
                System.out.println("ManagerOrderFile: saveAll()");
            }
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
