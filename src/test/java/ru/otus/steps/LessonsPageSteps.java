package ru.otus.steps;

import com.google.inject.Inject;
import io.cucumber.java.ru.Тогда;
import ru.otus.pages.LessonsPage;

public class LessonsPageSteps {

    @Inject
    LessonsPage lessonsPage;

    @Тогда("Вывожу информацию о выбранном курсе в консоль")
    public void printCourseInfo() {
        lessonsPage.printInfoLesson();
    }

    @Тогда("Выполнен переход на страницу {string}")
    public void checkCurrentUrlSteps(String url) {
        lessonsPage.checkCurrentUrl(url);
    }

    @Тогда("На открывшейся странице отображается название курса {string}")
    public void checkCurrentName(String courseName) {
        lessonsPage.checkLessonName(courseName);
    }


}
