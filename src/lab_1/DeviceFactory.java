package lab_1;

import java.util.Random;

public class DeviceFactory {

    private static DeviceFactory instance;
    private DeviceFactory(){}

    public static DeviceFactory getInstance(){
        if(instance == null){
            instance = new DeviceFactory();
        }
        return instance;
    }

    private String[] arrayName = { "Hi", "Love", "Tree", "Stone" };
    private String[] arrayBrand = { "LG", "Samsung", "Sony", "Huawei", "Apple" };
    private String[] arrayModel = { "FIG-01", "Kl-1", "5", "One" };
    private Random random = new Random();

    public GameConsole getGameConsole() {
        return new GameConsole(
                 1024,
                 100,
                 arrayName[ random.nextInt(arrayName.length) ],
                 arrayBrand[ random.nextInt(arrayBrand.length) ],
                 arrayModel[ random.nextInt(arrayModel.length) ]
        );
    }

    public SetTopBox getSetTopBox() {
        return new SetTopBox(
                26,
                17,
                5,
                100,
                arrayName[ random.nextInt(arrayName.length) ],
                arrayBrand[ random.nextInt(arrayBrand.length) ],
                arrayModel[ random.nextInt(arrayModel.length) ]
        );
    }

    public TVSet TVSet() {
        return new TVSet(
                103,
                100,
                arrayName[ random.nextInt(arrayName.length) ],
                arrayBrand[ random.nextInt(arrayBrand.length) ],
                arrayModel[ random.nextInt(arrayModel.length) ]
        );
    }
}
