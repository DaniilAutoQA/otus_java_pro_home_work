package ru.otus.steps;

import com.google.inject.Inject;
import io.cucumber.java.ru.И;
import ru.otus.pages.PreparationCoursInfoPage;

public class PreparationCoursInfoPageSteps {

    @Inject
    PreparationCoursInfoPage preparationCoursInfoPage;

    @И("^Вывожу, в консоль, информацию о выбранном подготовительном курсе$")
    public void printCourseInfo() {
        preparationCoursInfoPage.printCourseInfo();

    }

}
