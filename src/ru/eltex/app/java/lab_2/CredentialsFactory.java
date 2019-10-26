package ru.eltex.app.java.lab_2;

import java.util.Random;

public class CredentialsFactory {

    private static CredentialsFactory instance;
    private CredentialsFactory(){
        id = 1;
    }

    public synchronized static CredentialsFactory getInstance(){
        if(instance == null){
            instance = new CredentialsFactory();
        }
        return instance;
    }

    private static int id;

    private String[] arrayName = { "Николай", "Иван", "Петр", "Антон" };
    private String[] arraySurname = { "Иванов", "Сидоров", "Петров", "Николаев", "Груодис" };
    private String[] arrayPatronymic = { "Петрович", "Иванович", "Сидорович", "Сергеевич" };
    private String[] arrayEmail = { "FIG-01@mail.com", "Kl-1@gmail.com", "5@one.ua", "One@yandex.ru" };
    private Random random = new Random();

    public Credentials getCredentials() {
        return new Credentials(
                id,
                arrayName[ random.nextInt(arrayName.length) ],
                arraySurname[ random.nextInt(arraySurname.length) ],
                arrayPatronymic[ random.nextInt(arrayPatronymic.length) ],
                arrayEmail[ random.nextInt(arrayEmail.length) ]
        );
    }
}
