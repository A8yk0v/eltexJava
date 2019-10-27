package ru.eltex.app.java.lab_5;

import ru.eltex.app.java.lab_2.Order;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.PriorityQueue;

public class ManagerOrderFile extends AManageOrder implements Runnable  {

    private PriorityQueue<Order> orders;
    private ObjectOutputStream objectOutputStream;
    FileOutputStream outputStream;

    public ManagerOrderFile(String save_file, PriorityQueue<Order> orders) {
        super(save_file);
        this.orders = orders;

        try {
            outputStream = new FileOutputStream(save_file);
            objectOutputStream = new ObjectOutputStream(outputStream);

            Thread t = new Thread(this);
            t.run();
        }
        catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public void readById(int id) {
        int count;
    }

    @Override
    public void saveById(int id) {

    }

    @Override
    public void readAll() {

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
