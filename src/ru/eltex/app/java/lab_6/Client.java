package ru.eltex.app.java.lab_6;

import com.sun.source.tree.Scope;
import ru.eltex.app.java.lab_2.Order;

import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    private DatagramSocket socket;
    private Socket tcpSocket;
    private Integer server_port;
    private InetAddress server_inetAddress;
    private ObjectOutputStream objectOutputStream;
    private boolean fsend;

    public Client() {
        try {
            socket = new DatagramSocket(4446);
            fsend = false;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean listen() {
        try {
            byte[] buf = new byte[256];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);

            String received = new String(packet.getData(), 0, packet.getLength());
            server_port = Integer.decode(received);
            server_inetAddress = packet.getAddress();

            System.out.println("UDP package from Server:");
            System.out.println("server_port= " + server_port);
            System.out.println("InetAddress= " + server_inetAddress.toString());
            return true;
        }
        catch (Exception e) {
            //e.printStackTrace();
            return false;
        }
        //return false;
    }

    public void TCPConnect() {
        try {
            tcpSocket = new Socket(server_inetAddress, server_port);
            objectOutputStream =
                    new ObjectOutputStream(tcpSocket.getOutputStream());
            fsend = true;
        }
        catch (Exception e) {
            System.out.println("server not found...");
            e.printStackTrace();
        }
    }

    public void orderSend(Order order) {
        if (!fsend) return;
        try {
            objectOutputStream.writeObject(order);
            System.out.println("Order send on server");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void TCPBreak() {
        try {
            objectOutputStream.close();
            socket.close();
            fsend = false;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
