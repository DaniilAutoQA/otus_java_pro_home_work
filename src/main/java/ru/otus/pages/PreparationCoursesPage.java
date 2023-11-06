package ru.otus.pages;


import com.google.inject.Inject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.otus.annotations.Path;
import ru.otus.configuration.GuiceScoped;
import ru.otus.exceptions.CourseNotFound;

import java.util.Comparator;
import java.util.List;

@Path("/online")
public class PreparationCoursesPage extends AbsBasePage<PreparationCoursesPage> {

    @FindBy(css = ".lessons a")
    private List<WebElement> courseCards;
    @FindBy(css = ".lessons__new-item-price")
    private List<WebElement> lessonsPrice;

    @Inject
    public PreparationCoursesPage(GuiceScoped guiceScoped) {
        super(guiceScoped);
    }


    public WebElement getExpensiveOrCheapCourse(String criteria) throws CourseNotFound {
        WebElement course;
        switch (criteria) {
            case "дешевый": {
                course = lessonsPrice.stream().min(Comparator.comparingInt(el -> Integer.parseInt(el.getText().split("\\s")[0]))).get();
                break;
            }
            case "дорогой": {
                course = lessonsPrice.stream().max(Comparator.comparingInt(el -> Integer.parseInt(el.getText().split("\\s")[0]))).get();
                break;
            }
            default:
                throw new CourseNotFound(criteria);
        }

        return course;
    }


}
