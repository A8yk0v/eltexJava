package lab_2;

import java.util.Iterator;
import java.util.PriorityQueue;

public class Orders {

    private PriorityQueue<Order> orders;

    Orders() {
        orders = new PriorityQueue<>();
    }

    public void shop(ShoppingCart cart, Credentials credentials) {
        Order order = new Order(cart, credentials);
        orders.add(order);
    }

    public void clean() {
        for (Iterator<Order> iter = orders.iterator(); iter.hasNext(); ) {
            Order element = iter.next();
            if ( element.isDone() && element.isTimeout() )
                iter.remove();
        }
    }
}
