package ru.eltex.app.java.lab_6;

public class Lab_6_main_server {
    public static void main(String[] args) {
        ServerUDPHello serverUDPHello = new ServerUDPHello();
        Thread th_udp_hello = new Thread(serverUDPHello);
        th_udp_hello.start();

        // TODO Код сервера

    }
}
