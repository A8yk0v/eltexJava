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
    public final static long IN_WAITINGCHECK_TIMEOUT = 6000L;
    /**
     * Время ожидания между проходами класса CompletedCheck
     * коллекции Orders и удаление заказа со
     * статусом COMPLETED
     * 10 секунд
     */
    public final static long IN_COMPLETEDCHECK_TIMEOUT = 7000L;
    /**
     * Время ожидания между генерациями новых заказов
     * в классе AutomaticOrderGeneration
     * 10 секунд
     */
    public final static long IN_AUTOMATICORDERGENERATION_TIMEOUT = 2500L;
    /**
     * Максимальное кол-во товаров в генерируемых корзинах
     */
    public final static int MAX_DEVICE_COUNT_IN_CARTS = 4;

    public static final String ORDERS_SAVE_FILE_SEV = "OrdersSaveFile.sev";
}
