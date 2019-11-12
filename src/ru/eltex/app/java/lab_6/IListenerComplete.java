package ru.eltex.app.java.lab_6;

import ru.eltex.app.java.lab_2.Order;

/**
 * Интерфейс для реализации паттерна "Наблюдатель"
 * в классе PublisherCompleteOrder.
 * Который рассылает информацию о выполненном заказе
 * ссылкой на объект выполненного заказа.
 */
public interface IListenerComplete {
    void complete(Order order);
}
