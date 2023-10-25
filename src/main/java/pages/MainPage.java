package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class MainPage extends AbsBasePage<MainPage> {

    @FindBy(css = ".AjnvM")
    private List<WebElement> specialNameList;
    @FindBy(css = ".dPBnbE")
    private List<WebElement> specialStartDate;
    @FindBy(css = ".hvCeDA")
    private List<WebElement> courseNameList;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public LessonsPage goToSpecializationByStartDate(String startDate) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        getCourseByStartDate(specialStartDate, startDate).get().click();
        return new LessonsPage(driver);
    }

    public Optional<WebElement> getCourseByStartDate(List<WebElement> elements, String startDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
        return elements.stream().reduce((el1, el2) -> {
            try {
                String dateEl1 = el1.getText().substring(2).split("\n")[0];
                String dateEl2 = el2.getText().substring(2).split("\n")[0];
                switch (startDate) {
                    case "early": {
                        return formatter.parse(dateEl1).before(formatter.parse(dateEl2)) ? el1 : el2;
                    }
                    case "later": {
                        return formatter.parse(dateEl1).after(formatter.parse(dateEl2)) ? el1 : el2;
                    }
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    public Optional<WebElement> findCourseByName(String courseName) {
        assert standartWaiter.waitForCondition(ExpectedConditions.visibilityOfAllElements(specialNameList));
        assert standartWaiter.waitForCondition(ExpectedConditions.visibilityOfAllElements(courseNameList));
        return Stream.concat(specialNameList.stream(), courseNameList.stream())
                .filter(name -> name.getText().contains(courseName)).findFirst();

    }

}
