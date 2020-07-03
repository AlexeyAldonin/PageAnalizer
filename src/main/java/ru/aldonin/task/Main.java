package ru.aldonin.task;

public class Main {
    public static void main(String[] args) {
        Announcer announcer = Announcer.getInstance();
        announcer.sayHello();
        String input = announcer.getUserChoice();
        while (!input.equalsIgnoreCase(UserInputPattern.EXIT.value)) {
            if (input.equalsIgnoreCase(UserInputPattern.CONTINUE.value)) {
                WordsCounter wordsCounter = WordsCounter.getInstance();
                wordsCounter.getCountResult();
            }
            input = announcer.getUserChoice();
        }
        WordsCounter wordsCounter = WordsCounter.getInstance();
        wordsCounter.getCountResult();
        announcer.sayBye();
    }
}
