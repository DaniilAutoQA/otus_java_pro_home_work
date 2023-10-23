import annotations.Driver;
import extensions.UIExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import pages.MainPage;

import java.lang.reflect.InvocationTargetException;

@ExtendWith(UIExtension.class)
public class SelectCourseTest {

    @Driver
    public WebDriver driver;

    @Test
    public void findFirstStartedCourse() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        new MainPage(driver).open().goToFirstStartedSpecialization().printPageTitle();
    }

    @Test
    public void selectCourseWithMouseAction() {
        Actions actions = new Actions(driver);
        actions.moveToElement(new MainPage(driver).open().findCourseByName("QA")).click().build().perform();
    }

}
