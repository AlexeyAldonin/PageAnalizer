package ru.aldonin.task;

import org.jsoup.Jsoup;

import java.io.*;

// Класс, используемый для извлечения текста, отображаемого при загрузке HTML файла
public class TextExtractor {

    private static TextExtractor instance;
    private static PageReader pageReader = PageReader.getInstance();
    private String extractedText;

    private TextExtractor() {
        this.extractedText = extractTextFromHtml(pageReader.getTextContainer());
    }

    public static TextExtractor getInstance() {
        if (instance != null) {
            return instance;
        }
        return new TextExtractor();
    }

    public String getExtractedText() {
        return extractedText;
    }

    public static String extractTextFromHtml(File textContainer) {
        StringBuilder stringBuilder = new StringBuilder();
        // NULL будет получен в случае, если полльзователь выбрал "Выход" при запросе от программы
        if (textContainer == null)
            return null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(textContainer))) {
            String temp;
            while ((temp = bufferedReader.readLine()) != null) {
                stringBuilder.append(temp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Ошибка доступа к сохранённому файлу");
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ошибка при четнии файла");
            return null;
        }
        return Jsoup.parse(stringBuilder.toString()).text();
    }
}
