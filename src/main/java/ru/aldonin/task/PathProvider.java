package ru.aldonin.task;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

//Класс, используемый для получения адресов исходной страницы и конечной директории для сохранения файла
public class PathProvider {

    private static PathProvider instance;
    private URL url;
    private File destinationDirectory;

    private PathProvider() {
        this.url = requestUrlPage();
        //Проверка для случая, если пользователь решил выйти
        if (this.url == null) {
            this.destinationDirectory = null;
        } else {
            this.destinationDirectory = requestFileDestination();
        }
    }

    public static PathProvider getInstance() {
        if (instance != null) {
            return instance;
        }
        return new PathProvider();
    }

    public URL getUrl() {
        return url;
    }

    public File getDestinationDirectory() {
        return destinationDirectory;
    }

    private static URL requestUrlPage() {

        System.out.println("Введите адрес исходной страницы\n" +
                "для выхода из раздела введите «выход»\n");
        String input = new Scanner(System.in).nextLine();

        // Проверка ввода пользователем значения, требуемого для выхода из программы
        if (input.equalsIgnoreCase(UserInputPattern.EXIT.value))
            return null;
        try {
            return new URL(input);
        } catch (MalformedURLException e) {
            System.out.println("Ошибка доступа к странице");
            //Возврат к запросу адреса
            return requestUrlPage();
        }
    }

    private static File requestFileDestination() {

        System.out.println("Введите адрес директории для сохранения файла\n" +
                "для выхода из раздела введите «выход»\n");
        String input = new Scanner(System.in).nextLine();

        // Проверка ввода пользователем значения, требуемого для выхода из программы
        if (input.equalsIgnoreCase(UserInputPattern.EXIT.value))
            return null;

        File file = new File(input);
        if (file.isDirectory()) {
            return file;
        } else {
            System.out.println("Директория не найдена");
            //Возврат к запросу адреса
            return requestFileDestination();
        }
    }
}
