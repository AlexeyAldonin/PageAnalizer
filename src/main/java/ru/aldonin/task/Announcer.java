package ru.aldonin.task;

import java.util.Scanner;

//Класс для диалга с пользователем
public class Announcer {

    private static Announcer instance;

    private Announcer() {}

    public static Announcer getInstance() {
        if (instance != null) {
            return instance;
        }
        return new Announcer();
    }

    public void sayHello() {
        System.out.println("Вас приветствует приложение для анализа текста");
    }

    public void sayBye() {
        System.out.println("Благодарим за использование приложения!");
    }

    public String getUserChoice() {
        System.out.println("Для выхода введите «Выход»\n" +
                "Для продолжения нажмите ENTER");
        return new Scanner(System.in).nextLine();
    }
}
