package ru.eltex.app.java;

public class GlobalConsts {
    /**
     * Время ожидания заказа до того как он перейдет
     * в состояние завершён
     * 3 часа в миллисекундах
     */
    public final static long WAIT_TIMEOUT = 10800000L;
    /**
     * Время ожидания между проходами класса InWaitingCheck
     * коллекции Orders и перевода заказа из
     * статуса InWAITING в статус COMPLETED
     * 10 секунд
     */
    public final static long IN_WAITINGCHECK_TIMEOUT = 10000L;
}
