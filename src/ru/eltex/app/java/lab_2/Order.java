package ru.eltex.app.java.lab_2;

import ru.eltex.app.java.GlobalConsts;

import java.util.Date;

public class Order implements Comparable<Order> {
    private StateOrder status;
    private Date creationTime;
    private Date timeout;

    private Credentials credentials;
    private ShoppingCart cart;

    Order(ShoppingCart cart, Credentials credentials) {
        this.cart = cart;
        this.credentials = credentials;
        status = StateOrder.InWAITING;
        creationTime = new Date();
        // время ожидания
        timeout = new Date(GlobalConsts.WAIT_TIMEOUT);
    }

    public boolean isTimeout() {
        Date current = new Date();
        return current.getTime() < creationTime.getTime() + timeout.getTime() ? true : false;
    }

    public boolean isCompleted() {
        return status.equals(StateOrder.COMPLETED) ? true : false;
    }

    public void read() {
        System.out.println(credentials.getSurname()+" "+credentials.getName()+": заказал " + cart.getCountItem());
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public int compareTo(Order p){

        return (int)this.creationTime.getTime() - (int)p.getCreationTime().getTime();
    }
}

enum StateOrder {
    InWAITING,
    COMPLETED
}