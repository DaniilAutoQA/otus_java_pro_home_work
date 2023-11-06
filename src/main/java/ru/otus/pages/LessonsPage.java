package ru.otus.pages;

import com.google.inject.Inject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.otus.configuration.GuiceScoped;

import static org.assertj.core.api.Assertions.assertThat;

public class LessonsPage extends AbsBasePage<LessonsPage> {

    @FindBy(xpath = "//*[contains(@class, 'diGrSa')]")
    private WebElement lessonName;
    @FindBy(xpath = "//*[contains(@class, 'dgWykw')]")
    private WebElement lessonStartDate;

    @Inject
    public LessonsPage(GuiceScoped guiceScoped) {
        super(guiceScoped);
    }

    public void checkLessonName(String pageTitle) {
        assertThat(lessonName.getText()).as("Название лекции не соответствует").contains(pageTitle);
    }

    public void printInfoLesson() {
        System.out.println("Название курса: " + lessonName.getText() + "\n" + "Дата начала курса: " + lessonStartDate.getText());
    }

}
