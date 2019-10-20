package ru.eltex.app.java.lab_1;

import java.util.ArrayList;

public class Lab_1 {

    private static int objectCount = 1;
    private static String objectType = "TVSet";

    public static void main(String[] args) {

        // Разбор параметров
        if (args.length > 2)
        {
            System.out.println("Too many parameters");
            System.out.println("STOP");
            return;
        } else if (args.length == 1)
        {
            objectCount = Integer.parseInt(args[0]);
        } else if (args.length == 2)
        {
            objectCount = Integer.parseInt(args[0]);
            objectType = args[1];
        }

        System.out.println("objectCount= " + objectCount);
        System.out.println("objectType= " + objectType);
        System.out.println("Start!!");

        // Основной цикл
        ArrayList<ICrudAction> mas = new ArrayList<ICrudAction>();
        for (int i = 0; i < objectCount; i++)
        {
            if ( objectType.equals("TVSet") )
            {
                TVSet tvset = new TVSet();
                tvset.update();
                mas.add( tvset );
            }
            else if ( objectType.equals("SetTopBox") )
            {
                SetTopBox tmp = new SetTopBox();
                tmp.update();
                mas.add( tmp );
            }
            else if ( objectType.equals("GameConsole") )
            {
                GameConsole tmp = new GameConsole();
                tmp.update();
                mas.add( tmp );
            }
        }

        //...
        System.out.println("count Device - " + Device.count);

        System.out.println("mas.get(0).delete() - Ok");
        mas.get(0).delete();
        System.out.println("count Device - " + Device.count);

        System.out.println("mas.get(1).update() - Ok");
        mas.get(1).update();
        System.out.println("mas.get(1).read() - Ok");
        mas.get(1).read();

//        System.out.println("Output all mas: ");
//        for (ICrudAction iter: mas) {
//            iter.read();
//        }
    }
}
