package ru.eltex.app.java.lab_5;

public interface IOrder {

    // TODO Как лучше обрабатывать ошибку: кодом возврата или исключением?!
    void readById(int id);
    void saveById(int id);
    void readAll();
    void saveAll();
}
