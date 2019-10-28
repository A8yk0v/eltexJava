package ru.eltex.app.java.lab_5;

import ru.eltex.app.java.GlobalConsts;

import java.io.*;
import java.util.Scanner;

public abstract class AManageOrder implements IOrder {

    protected FileOutputStream fileOutputStream;
    protected BufferedInputStream bufferedInputStream;

    public AManageOrder(String save_file) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(save_file);
            //FileInputStream fileInputStream = new FileInputStream(save_file);
            //bufferedInputStream = new BufferedInputStream(fileInputStream, GlobalConsts.SIZE_OF_BUFFEREDINPUTSTREAM);
        }
        catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
