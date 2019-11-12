package ru.eltex.app.java.lab_6;

import ru.eltex.app.java.GlobalConsts_for_lab6;
import ru.eltex.app.java.lab_2.Order;
import ru.eltex.app.java.lab_2.Orders;
import ru.eltex.app.java.lab_4.CompletedCheck;
import ru.eltex.app.java.lab_4.InWaitingCheck;

import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * Инициализация всех сервисов сервера
 */
public class Lab_6_main_server {

    public static void main(String[] args) {

        try {
            // Сокет с которого будут рассылаться "приглашения" на подключение
            DatagramSocket UDPSocket = new DatagramSocket(GlobalConsts_for_lab6.UDP_PORT_FOR_INVITATION);

            ServerUDPOrderComplete serverUDPOrderComplete =
                    new ServerUDPOrderComplete(GlobalConsts_for_lab6.CLIENT_UDP_PORT_1, UDPSocket);

            ServerUDPHello serverUDPHello_1 = new ServerUDPHello(GlobalConsts_for_lab6.UDP_PORTS_FOR_INVITATION[0], UDPSocket);
            Thread th_udp_hello_1 = new Thread(serverUDPHello_1);
            th_udp_hello_1.start();
            ServerUDPHello serverUDPHello_2 = new ServerUDPHello(GlobalConsts_for_lab6.UDP_PORTS_FOR_INVITATION[1], UDPSocket);
            Thread th_udp_hello_2 = new Thread(serverUDPHello_2);
            th_udp_hello_2.start();
            ServerUDPHello serverUDPHello_3 = new ServerUDPHello(GlobalConsts_for_lab6.UDP_PORTS_FOR_INVITATION[2], UDPSocket);
            Thread th_udp_hello_3 = new Thread(serverUDPHello_3);
            th_udp_hello_3.start();

            // Коллекция для хранения всех активных(невыполненных и выполненных,
            // но клиенты которых еще не получили оповещение) заказов
            Orders<Order> orders = new Orders<>();
            PublisherCompleteOrder publisherCompleteOrder = new PublisherCompleteOrder();
            publisherCompleteOrder.addListener(serverUDPOrderComplete);

            // Перевод заказов в состояние выполнен
            // Запускается в отдельном потоке
            InWaitingCheck inWaitingCheck = new InWaitingCheck(orders, publisherCompleteOrder);
            // Удаляет заказы из коллекции
            // получая уведомление о событии - завершении заказа
            CompletedCheck completedCheck = new CompletedCheck(orders);
            publisherCompleteOrder.addListener(completedCheck);

            // Создаем рабочии потоки, для обработки заказов
            try ( ServerSocket serverSocket = new ServerSocket(GlobalConsts_for_lab6.SERVER_TCP_PORT) ) {
                while (true) {
                    Socket client = serverSocket.accept();
                    Thread t = new Thread(new TCPWorker(client, orders) );
                    t.start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
