package lab_2;

import java.util.Date;

public class Order {
    private StateOrder status;
    private Date creationTime;
    private Date timeout;

    private Credentials credentials;
    private ShoppingCart cart;

    Order(ShoppingCart cart, Credentials credentials) {
        this.cart = cart;
        this.credentials = credentials;
        status = StateOrder.CREATE;
        creationTime = new Date();
        // 3 часа в миллисекундах
        timeout = new Date(10800000L);
    }

    public boolean isTimeout() {
        Date current = new Date();
        return current.getTime() < creationTime.getTime() + timeout.getTime() ? true : false;
    }

    public boolean isDone() {
        return status.equals(StateOrder.DONE) ? true : false;
    }

    public void read() {
        System.out.println(credentials.getSurname()+" "+credentials.getName()+": заказал " + cart.getCountItem());
    }
}

enum StateOrder {
    CREATE,
    DONE
}