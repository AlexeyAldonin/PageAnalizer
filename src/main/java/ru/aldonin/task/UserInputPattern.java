package ru.aldonin.task;

// Варианты ответов пользователя, на основании которых выбирается алгоритм
public enum UserInputPattern {
    CONTINUE (""),
    EXIT ("выход");

    String value;

    UserInputPattern(String value) {
        this.value = value;
    }
}
