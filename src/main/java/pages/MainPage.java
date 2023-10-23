package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class MainPage extends AbsBasePage<MainPage> {

    @FindBy(xpath = "//div[@class='sc-1pljn7g-6 kbUYTE']")
    private List<WebElement> courseCards;
    @FindBy(xpath = "//h5[@class='sc-1pljn7g-1 hvCeDA']")
    private List<WebElement> courseNameList;
    @FindBy(xpath = "//div[@class='sc-12yergf-11 fgNPoG']")
    private List<WebElement> specializationCards;
    @FindBy(xpath = "//div[@class='sc-12yergf-10 bddZLL']")
    private List<WebElement> specialNameList;
    @FindBy(xpath = "//span[@class='sc-12yergf-7 dPBnbE']")
    private List<WebElement> specialStartDate;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public <T extends AbsBasePage<T>> T openCoursePage(Class<T> pageClazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<T> constructor = pageClazz.getConstructor(WebDriver.class);
        return (T) constructor.newInstance(driver);
    }

    public void goToFirstStartedSpecialization() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        getFirstStartedCourse(specialStartDate).get().click();
    }

    public Optional<WebElement> getFirstStartedCourse(List<WebElement> elements) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
        return elements.stream().reduce((el1, el2) -> {
            try {
                return formatter.parse(el1.getText().substring(2).split("\n")[0]).before(formatter.parse(el2.getText().substring(2).split("\n")[0])) ? el1 : el2;
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    public Optional<WebElement> findFirstStartedCourse() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
        return specialStartDate.stream().reduce((el1, el2) -> {
            try {
                return formatter.parse(el1.getText().substring(2).split("\n")[0]).before(formatter.parse(el2.getText().substring(2).split("\n")[0])) ? el1 : el2;
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    public WebElement findCourseByName(String courseName) {

        return Stream.concat(specialNameList.stream(), courseNameList.stream())
                .filter(name -> name.getText().contains(courseName)).findFirst().get();

    }
}
