package lab_1;

import java.util.Scanner;

public class TVSet extends Device {

    // в см
    private int diagonal;

    TVSet() {
        diagonal = 102;
        price = 1000;
        name = "Hi";
        brand = "LG";
        model = "KJ-100";

        super.create();
    }

    TVSet(String name, int diagonal) {
        this.diagonal = diagonal;
        this.name = name;
        price = 1000;
        brand = "LG";
        model = "KJ-100";

        super.create();
    }

    @Override
    public void read() {
        super.read();
        System.out.println("diagonal= " + diagonal);
    }

    @Override
    public void update() {
        Scanner in = new Scanner(System.in);
        System.out.println("Input TVSet: name brand model price diagonal");
        name = in.next();
        brand = in.next();
        model = in.next();
        price = in.nextInt();
        diagonal = in.nextInt();
    }

    @Override
    public void delete() {
        diagonal = 0;
        super.delete();
    }
}
