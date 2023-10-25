package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class LessonsPage extends AbsBasePage<LessonsPage> {

    @FindBy(xpath = "//*[contains(@class, 'diGrSa')]")
    private WebElement lessonName;

    public LessonsPage(WebDriver driver) {
        super(driver);
    }

    public void checkCurrentUrl() {
        assertThat(driver.getCurrentUrl()).as("url страницы не соответствует").contains("https://otus.ru/lessons");
    }

    public void checkLessonName(String pageTitle) {
        assertThat(lessonName.getText()).as("Название лекции не соответствует").contains(pageTitle);
    }

}
