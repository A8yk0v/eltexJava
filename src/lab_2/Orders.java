package lab_2;

import java.util.Iterator;
import java.util.PriorityQueue;

public class Orders<T extends Order> {

    private PriorityQueue<T> orders;

    Orders() {
        orders = new PriorityQueue<>();
    }

    public void shop(ShoppingCart cart, Credentials credentials) {
        T order = (T) new Order(cart, credentials);
        orders.add(order);
    }

    public void clean() {
        for (Iterator<T> iter = orders.iterator(); iter.hasNext(); ) {
            T element = iter.next();
            if ( element.isDone() && element.isTimeout() )
                iter.remove();
        }
    }

    public void printAll() {
        for ( T dev: orders) {
            dev.read();
        }
    }
}
