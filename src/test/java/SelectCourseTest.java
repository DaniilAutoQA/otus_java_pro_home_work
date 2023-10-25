import annotations.Driver;
import extensions.UIExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import pages.LessonsPage;
import pages.MainPage;

import java.lang.reflect.InvocationTargetException;

@ExtendWith(UIExtension.class)
public class SelectCourseTest {

    @Driver
    public WebDriver driver;

    @Test
    public void findCourseByStartDate() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        new MainPage(driver).open()
                .goToSpecializationByStartDate("later")
                .checkCurrentUrl();
    }

    @Test
    public void selectCourseWithMouseAction() {
        Actions actions = new Actions(driver);
        MainPage mainPage = new MainPage(driver).open();
        actions.moveToElement(mainPage.findCourseByName("QA").get()).click().build().perform();
        new LessonsPage(driver).checkLessonName("QA");

    }

}
