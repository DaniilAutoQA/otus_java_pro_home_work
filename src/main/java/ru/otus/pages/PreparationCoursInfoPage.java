package ru.otus.pages;


import com.google.inject.Inject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.otus.configuration.GuiceScoped;


public class PreparationCoursInfoPage extends AbsBasePage<PreparationCoursInfoPage> {

    @FindBy(css = ".clsYBl")
    private WebElement courseInfoCards;

    @Inject
    public PreparationCoursInfoPage(GuiceScoped guiceScoped) {
        super(guiceScoped);
    }

    public void printCourseInfo() {
        System.out.println("Курс" + courseInfoCards.getText().split("Курс")[1].split("Оплатить")[0]);
    }

}
