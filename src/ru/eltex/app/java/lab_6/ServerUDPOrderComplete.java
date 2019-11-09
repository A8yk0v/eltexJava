package ru.eltex.app.java.lab_6;

import ru.eltex.app.java.lab_2.Order;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

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
        byte[]message = "Your order complete".getBytes();
        // TODO Магическое число
        DatagramPacket packet = new DatagramPacket(message, message.length, order.getCredentials().getInetAddress(), client_port);
        try {
            Thread.sleep(1000);
            socket.send(packet);
            System.out.println("send");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("ServerUDPOrderComplete send message");
    }
}
