package ru.eltex.app.java.lab_6;

import ru.eltex.app.java.GlobalConsts_for_lab6;
import ru.eltex.app.java.lab_2.Order;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;

public class Client {
    private DatagramSocket socket;
    private Socket tcpSocket;
    private Integer server_port;
    private InetAddress server_inetAddress;
    private ObjectOutputStream objectOutputStream;
    private boolean fsend;

    public Client() throws Exception {
        // test     ---
        //DatagramSocket socket1 = new DatagramSocket(GlobalConsts_for_lab6.UDP_PORTS_FOR_INVITATION[0]);
        // test end ---

        fsend = false;

        boolean stop_flag = true;
        int i = 0;
        while (stop_flag) {
            try {
                socket = new DatagramSocket(GlobalConsts_for_lab6.UDP_PORTS_FOR_INVITATION[i]);
                System.out.println("Client UDP start on port=" + GlobalConsts_for_lab6.UDP_PORTS_FOR_INVITATION[i]);
                stop_flag = false;
            }
            catch (BindException e) {
                i += 1;
                if (i == GlobalConsts_for_lab6.UDP_PORTS_FOR_INVITATION.length)
                    throw e;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
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

    public boolean udp_response() {
        try {
            byte[] buf = new byte[256];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);

            String response;
            do {
                socket.receive(packet);
                response = new String(packet.getData(), 0, packet.getLength());
            } while ( response.equals(GlobalConsts_for_lab6.UDP_PORT_FOR_INVITATION) );

            //return response.equals(GlobalConsts_for_lab6.YOUR_ORDER_COMPLETE);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
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
            tcpSocket.close();
            fsend = false;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
