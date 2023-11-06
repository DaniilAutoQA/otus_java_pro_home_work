package ru.otus.steps;

import io.cucumber.java.ru.Пусть;

public class CommonSteps {

    @Пусть("Выбран браузер {string}")
    public void selectBrowser(String browserName) {
        System.setProperty("browser", browserName);
    }
}
