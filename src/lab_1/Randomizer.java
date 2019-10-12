package lab_1;

import java.util.Random;

public class Randomizer {

    private static Randomizer instance;
    private Randomizer(){}

    public static Randomizer getInstance(){
        if(instance == null){
            instance = new Randomizer();
        }
        return instance;
    }

    private String[] arrayName = { "Hi", "Love", "Tree", "Stone" };
    private String[] arrayBrand = { "LG", "Samsung", "Sony", "Huawei", "Apple" };
    private String[] arrayModel = { "FIG-01", "Kl-1", "5", "One" };
    private Random random = new Random();

    public String getRandomName() {
        String str = arrayName[ random.nextInt(arrayName.length) ];
        return str;
    }

    public String getRandomModel() {
        String str = arrayModel[ random.nextInt(arrayModel.length) ];
        return str;
    }

    public String getRandomBrand() {
        String str = arrayBrand[ random.nextInt(arrayBrand.length) ];
        return str;
    }

    public int getRandomPrice() {
        int price = random.nextInt(10000);
        return price;
    }
}
