package ru.otus.steps;

import com.google.inject.Inject;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import ru.otus.pages.MainPage;

import java.lang.reflect.InvocationTargetException;

public class ManePageSteps {

    @Inject
    MainPage mainPage;

    @Когда("Открыта главная страница")
    public void openMainPage() {
        mainPage.open();
    }

    @И("Выполнен клик по карточке курса {string}")
    public void goToCourseByName(String courseName) {
        mainPage.findCourseByName(courseName).get().click();
    }

    @И("^Выполнен клик по карточке курса, который стартует не раньше (.*)$")
    public void goToCourseByDate(String date) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        mainPage.goToSpecializationByStartDate(date);
    }



}
