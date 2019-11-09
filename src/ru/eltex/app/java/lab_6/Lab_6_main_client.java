package ru.eltex.app.java.lab_6;

import ru.eltex.app.java.lab_2.Order;
import ru.eltex.app.java.lab_4.AutomaticOrderGeneration;

public class Lab_6_main_client {

    static Client client;

    public static void main(String[] args) {
        try {
            client = new Client();
            while (!client.listen()) ;

            client.TCPConnect();

            Order myOrder = new AutomaticOrderGeneration().getOrder();
            client.orderSend(myOrder);
            client.TCPBreak();

            System.out.println("Client end)");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
