package ru.eltex.app.java.lab_2;

import ru.eltex.app.java.GlobalConsts;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Order implements Comparable<Order>, Serializable {
    private StateOrder status;
    private Date creationTime;
    private Date timeout;
    private int id;

    private Credentials credentials;
    private ShoppingCart cart;

    public Order(ShoppingCart cart, Credentials credentials) {
        this.cart = cart;
        this.credentials = credentials;
        status = StateOrder.InWAITING;
        creationTime = new Date();
        // время ожидания
        timeout = new Date(GlobalConsts.WAIT_TIMEOUT);
        // уникальный id
        UUID uuid = UUID.randomUUID();
        id = uuid.hashCode();
    }

    // метод возвращает true, если время ожидания истекло
    public boolean isTimeout() {
        Date current = new Date();
        return current.getTime() > creationTime.getTime() + timeout.getTime() ? true : false;
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

    public void executeOrder() {
        this.status = StateOrder.COMPLETED;
    }

    public int getId() {
        return id;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }
}

enum StateOrder {
    InWAITING,
    COMPLETED;
    int i;
}