package ru.eltex.app.java.lab_6;

import ru.eltex.app.java.GlobalConsts_for_lab6;
import ru.eltex.app.java.lab_2.Order;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Класс отправляет на клиент информацию о завершении заказа
 * Является подписчиком на событие завершения заказа
 */
public class ServerUDPOrderComplete implements IListenerComplete{

    private DatagramSocket socket;
    private int client_port;

    public ServerUDPOrderComplete(int client_port, DatagramSocket socket) {
        this.client_port = client_port;
        this.socket = socket;
        System.out.println("ServerUDPOrderComplete is starting");
    }

    @Override
    public void complete(Order order) {
        byte[]message = GlobalConsts_for_lab6.YOUR_ORDER_COMPLETE.getBytes();
        DatagramPacket packet = new DatagramPacket(message, message.length, order.getCredentials().getInetAddress(), client_port);
        try {
            socket.send(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("ServerUDPOrderComplete send message");
    }
}
