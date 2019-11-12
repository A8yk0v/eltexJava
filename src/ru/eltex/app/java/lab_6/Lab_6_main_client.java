package ru.eltex.app.java.lab_6;

import ru.eltex.app.java.GlobalConsts_for_lab6;
import ru.eltex.app.java.lab_2.Order;
import ru.eltex.app.java.lab_4.AutomaticOrderGeneration;

import java.net.BindException;

/**
 * Класс с main методом для запуска клиента
 * В цикле происходит оправка заказов на сервер
 */
public class Lab_6_main_client {

    static Client client;

    public static void main(String[] args) {
        try {
            client = new Client();
            while (!client.listen()) ;

            while (true) {
                client.TCPConnect();
                Order myOrder = new AutomaticOrderGeneration().getOrder();
                client.orderSend(myOrder);
                client.TCPBreak();

                client.udp_response();
                System.out.println("Client cycle end");
                Thread.sleep(GlobalConsts_for_lab6.CLIENT_CYCLE_TIMEOUT);
            }
        }
        catch (BindException e) {
            System.out.println("Socket address already in use (Bind failed)");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
