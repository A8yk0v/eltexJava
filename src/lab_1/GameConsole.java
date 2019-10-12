package lab_1;

import java.util.Scanner;

public class GameConsole extends Device {
    // Объем HDD в гб
    private int HDDCapacity;

    GameConsole() {
        HDDCapacity = 1024;
        price = 1000;
        name = "O_MEGA";
        brand = "Hitachi";
        model = "jjl";

        super.create();
    }

    GameConsole(int HDDCapacity, int price, String name,
                                String brand, String model) {
        this.HDDCapacity = HDDCapacity;
        this.price = price;
        this.name = name;
        this.brand = brand;
        this.model = model;

        super.create();
    }

    @Override
    public void read() {
        super.read();
        System.out.println("HDDCapacity= " + HDDCapacity);
    }

    @Override
    public void update() {
        Scanner in = new Scanner(System.in);
        System.out.println("Input GameConsole: name brand model price HDDCapacity");
        name = in.next();
        brand = in.next();
        model = in.next();
        price = in.nextInt();
        HDDCapacity = in.nextInt();
    }

    @Override
    public void delete() {
        HDDCapacity = 0;
        super.delete();
    }
}
