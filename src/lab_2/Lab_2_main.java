package lab_2;

import lab_1.Device;
import java.util.*;

public class Lab_2_main {
    public static void main(String[] args) throws InterruptedException {
        // Коллекция для хранения и поиска уникальных идентификаторов
        Set<Integer> idTable = new TreeSet<>();

        for (int i = 0; i < 100; i++) {
            Device tmp = DeviceFactory.getInstance().getDevice();
            idTable.add( tmp.getId() );
            // для генерации всей сотни записей в hashmap
            // нужно уникальное время
            ///Thread.sleep(2);
        }
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

        System.out.println("-.-.-.-.-.-.-.-");
        System.out.println("shoppingCart.isHaveDevice(id_dev) - " + shoppingCart.isHaveDevice(id_dev));
        System.out.println("shoppingCart.isHaveDevice(0) - " + shoppingCart.isHaveDevice(0));

        System.out.println("-.-.-.-.-.-.-.-");

        System.out.println("Find id=" + id_dev + " in cart:");
        shoppingCart.getDeviceOnID(id_dev).read();

        System.out.println("-.-.-.-.-.-.-.-");
    }
}
