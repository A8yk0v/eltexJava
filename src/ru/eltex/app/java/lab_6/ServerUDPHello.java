package ru.eltex.app.java.lab_6;

import ru.eltex.app.java.GlobalConsts_for_lab6;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Класс будуче запущенным в потоке каждую секунду
 * отправляет широковещательный UPD пакет
 * с "приглашение" к подключению
 */
public class ServerUDPHello implements Runnable {

    private DatagramSocket socket;
    private InetAddress address;
    private DatagramPacket packet;
    private int client_port;
    private boolean running = true;

    public ServerUDPHello(int client_port, DatagramSocket socket) {
        try {
            this.socket = socket;
            this.client_port = client_port;
            address = InetAddress.getByName("127.0.0.1"); //255.255.255.255
            String server_tcp_port = String.valueOf(GlobalConsts_for_lab6.SERVER_TCP_PORT);
            byte[]message = server_tcp_port.getBytes();
            packet = new DatagramPacket(message, message.length, address, client_port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("ServerUDPHello is starting on port=" + client_port);
        while (running) {
            try {
                Thread.sleep(GlobalConsts_for_lab6.SERVER_UDP_HELLO_TIMEOUT);
                socket.send(packet);

            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        running = false;
    }

    public void start() {
        running = true;
    }
}
