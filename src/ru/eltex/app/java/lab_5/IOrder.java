package ru.eltex.app.java.lab_5;

import ru.eltex.app.java.lab_2.Order;

import java.util.PriorityQueue;

public interface IOrder {

    // TODO Как лучше обрабатывать ошибку: кодом возврата или исключением?!
    Order readById(int id);
    void saveById(int id);
    PriorityQueue<Order> readAll();
    void saveAll();
}
