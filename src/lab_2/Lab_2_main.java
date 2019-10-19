package lab_2;

import lab_1.Device;
import java.util.*;

public class Lab_2_main {
    public static void main(String[] args) throws InterruptedException {
        // Коллекция для хранения и поиска уникальных идентификаторов
        Set<Integer> idTable = new TreeSet<>();

        // Коллекция для хранения заказов по времени создания
        //Map<Long, Device> hashmap = new HashMap<>();

        for (int i = 0; i < 100; i++) {
            Device tmp = DeviceFactory.getInstance().getDevice();
            ///hashmap.put(new Date().getTime(), tmp);
            idTable.add( tmp.getId() );
            // для генерации всей сотни записей в hashmap
            // нужно уникальное время
            ///Thread.sleep(2);
        }

        //System.out.println(hashmap.toString());
        //System.out.println("hashmap.size()="+hashmap.size());
        System.out.println("idTable.size()="+idTable.size());


        // Проверка работы ShoppingCart
        ShoppingCart shoppingCart = new ShoppingCart();
        //Iterator<Map.Entry<Long, Device>> itr = hashmap.entrySet().iterator();
        for (int i = 0; i < 3; i++) {
            //shoppingCart.add( itr.next().getValue() );
            shoppingCart.add( DeviceFactory.getInstance().getDevice() );
        }
        shoppingCart.printAll();

        int id_dev = 0;
        Device tmp = DeviceFactory.getInstance().getDevice();
        id_dev = tmp.getId();
        shoppingCart.add( tmp );

        Credentials credentials = CredentialsFactory.getInstance().getCredentials();

        Orders orders = new Orders();
        orders.shop(shoppingCart, credentials);
        orders.printAll();

        // Коллекция для хранения заказов по времени создания
        Map<Long, Device> warehouse_orders = new HashMap<>();
        //warehouse_orders.put(orders.)

        // заказы же хранятся в классе Orders ????



        System.out.println("-.-.-.-.-.-.-.-");

        System.out.println("Find id=" + id_dev + " in cart:");
        shoppingCart.getDeviceOnID(id_dev).read();

    }
}
