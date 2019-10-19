package lab_1;

import java.util.Scanner;

public class SetTopBox extends Device {
    // Габариты в мм
    private int width;
    private int height;
    private int depth;

    public SetTopBox() {
        width = 10;
        height = 10;
        depth = 10;
        price = 1000;
        name = "E-1";
        brand = "Eltex";
        model = "SSA-K1";
        super.create();
    }

    public SetTopBox(int width, int height, int depth, int price,
                    String name, String brand, String model) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.price = price;
        this.name = name;
        this.brand = brand;
        this.model = model;

        super.create();
    }

    @Override
    public void read() {
        super.read();
        System.out.println("(width, height, depth) - " +
                "(" + width +", " + height + ", " + depth);
    }

    @Override
    public void update() {
        Scanner in = new Scanner(System.in);
        System.out.println("Input SetTopBox: name brand model price width height depth");
        name = in.next();
        brand = in.next();
        model = in.next();
        price = in.nextInt();
        width = in.nextInt();
        height = in.nextInt();
        depth = in.nextInt();
    }

    @Override
    public void delete() {
        width = height = depth = 0;
        super.delete();
    }
}
