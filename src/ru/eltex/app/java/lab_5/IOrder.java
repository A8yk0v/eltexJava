package ru.eltex.app.java.lab_5;

import ru.eltex.app.java.lab_2.Order;

import java.util.PriorityQueue;

/**
 * Интерфейс IOrder для хранения/манипулярованния заказами
 * при хранении их в файле
 *
 * Вслючаи не возможности вернуть обект(ы) возвращается null
 */

public interface IOrder {

    Order readById(int id);
    void saveById(int id);
    PriorityQueue<Order> readAll();
    void saveAll();
}
