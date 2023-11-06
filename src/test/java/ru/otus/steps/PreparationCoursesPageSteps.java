package ru.otus.steps;

import com.google.inject.Inject;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import ru.otus.exceptions.CourseNotFound;
import ru.otus.pages.PreparationCoursesPage;

public class PreparationCoursesPageSteps {

    @Inject
    PreparationCoursesPage preparationCoursesPage;

    @Когда("Открыта страница подготовительных курсов")
    public void openPreparationCoursesPage() {
        preparationCoursesPage.open();

    }

    @И("^Выбрать самый (дорогой|дешевый) курс$")
    public void goToExpensiveOrCheapCourse(String criteria) throws CourseNotFound {
        preparationCoursesPage.getExpensiveOrCheapCourse(criteria).click();
    }

}
