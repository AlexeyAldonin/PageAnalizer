package ru.aldonin.task;

import java.io.*;
import java.net.URL;

//Класс для считывания web-страницы и сохранения содержимого в файл.

public class PageReader {

    private static PageReader instance;
    private static PathProvider pathProvider = PathProvider.getInstance();
    private File textContainer; //Целевой файл

    private PageReader() {
        URL url = pathProvider.getUrl();
        File targetDirectory = pathProvider.getDestinationDirectory();
        if (targetDirectory != null && url != null) {
            this.textContainer = new File(String.format("%s\\%s.html", targetDirectory, url.getHost()));
            try {
                saveTextToFile(url);
                System.out.println("Файл " + this.textContainer + " успешно сохранён");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Ошибка при чтении или записи файла" + this.textContainer);
                this.textContainer = null;
            }
        } else
            this.textContainer = null;
    }

    public static PageReader getInstance() {
        if (instance != null) {
            return instance;
        }
        return new PageReader();
    }

    public File getTextContainer() {
        return textContainer;
    }

    public void saveTextToFile(URL sourcePage) throws IOException {

        if (sourcePage == null)
            return;

        if (this.textContainer == null)
            return;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(sourcePage.openStream()));
             BufferedWriter writer = new BufferedWriter(new FileWriter(this.textContainer))) {

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.flush();
            }
        }

    }
}
