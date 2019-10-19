package lab_1;

import java.util.Scanner;
import java.util.UUID;

public abstract class Device implements ICrudAction, Comparable<Device> {
    // Счетчик объектов класса
    public static int count = 0;

    protected int id;
    protected int price;
    protected String name;
    protected String brand;
    protected String model;

    Device() {
        UUID uuid = UUID.randomUUID();
        id = uuid.hashCode();
    }

    @Override
    public void read() {
        System.out.println("id= " + id);
        System.out.println("brand= " + brand);
        System.out.println("name= "  + name );
        System.out.println("model= " + model);
        System.out.println("price= " + price);
    }

    @Override
    public void create() {
//        price = 100;
//        name = "NAME";
//        brand = "BRAND";
//        model = "MODEL";
        count++;
    }

    @Override
    public void update() {
        Scanner in = new Scanner(System.in);
        System.out.println("Input: name brand model price");
        name = in.next();
        brand = in.next();
        model = in.next();
        price = in.nextInt();
    }

    @Override
    public void delete() {
        id = 0;
        price = 0;
        name = "";
        brand = "";
        model = "";

        count--;
    }

    public int getId() {
        return id;
    }

    public int compareTo(Device p){

        return this.id - p.getId();
    }
}