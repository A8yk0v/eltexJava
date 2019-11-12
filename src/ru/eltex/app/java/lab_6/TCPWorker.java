package ru.eltex.app.java.lab_6;

import ru.eltex.app.java.lab_2.Order;
import ru.eltex.app.java.lab_2.Orders;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * Класс принимает по сокету заказа от клиента
 * и добавляет его в коллекцию заказов
 */
public class TCPWorker implements Runnable {
    private Socket client;
    private Orders<Order> orders;

    public TCPWorker(Socket client, Orders<Order> orders) {
        this.client = client;
        this.orders = orders;
    }

    @Override
    public void run() {
        try (InputStream inputStream = client.getInputStream();
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {

            Order order = (Order) objectInputStream.readObject();
            order.read();
            order.getCredentials().setInetAddress(client.getInetAddress());
            orders.addOrder(order);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
