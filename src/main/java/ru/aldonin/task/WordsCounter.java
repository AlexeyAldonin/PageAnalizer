package ru.aldonin.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

// Класс, используемый для подсчёта слов в конкретном тексте
public class WordsCounter {

    private static WordsCounter instance;
    private static TextExtractor textExtractor = TextExtractor.getInstance();
    private String text;

    private WordsCounter() {
        this.text = textExtractor.getExtractedText();
    }

    public static WordsCounter getInstance() {
        if (instance != null) {
            return instance;
        }
        return new WordsCounter();
    }

    public void setText(String text) {
        this.text = text;
    }

    // Сбор слов с привязкой индексов подсчёта количества
    public static Map<String, Integer> countWords(String textToAnalyze) {

        // В случае, если пользователь выбрал "Выход"
        if (textToAnalyze == null)
            return null;

        List<String> wordsList = new ArrayList<>();
        String[] words = textToAnalyze.replaceAll("[\\p{Punct}\\n\\r\\t]", " ").split("\\s+");
        for (String s : words) {
            wordsList.add(s.toUpperCase());
        }
        return wordsList.stream()
                .collect(Collectors.toMap(Function.identity(), v -> 1, Integer::sum));
    }

    // Вывод результата на экран
    public void getCountResult() {
        Map<String, Integer> stats = countWords(this.text);
        if (stats == null)
            return;
        for ( Map.Entry<String, Integer> entry : stats.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }
}
