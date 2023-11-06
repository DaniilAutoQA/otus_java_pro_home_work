package ru.otus.pages;

import com.google.inject.Inject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.otus.configuration.GuiceScoped;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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


    @Inject
    public MainPage(GuiceScoped guiceScoped) {
        super(guiceScoped);
    }


    public void goToSpecializationByStartDate(String startDate) {
        getCourseByStartDate1(specialStartDate, startDate).get().click();
    }

    public Optional<WebElement> getCourseByStartDate(List<WebElement> elements, String startDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
        return elements.stream().reduce((el1, el2) -> {
            try {
                String dateEl1 = el1.getText().substring(2).split("\n")[0];
                String dateEl2 = el2.getText().substring(2).split("\n")[0];
                return formatter.parse(dateEl1).before(formatter.parse(dateEl2)) ? el1 : el2;
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    public Optional<WebElement> getCourseByStartDate1(List<WebElement> elements, String startDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
        Optional<WebElement> resEquals = elements.stream().filter(ele -> {
            try {
                String dateEl2 = ele.getText().substring(2).split("(?!(?:.* ){2})")[0].trim() + " " + LocalDate.now().getYear();
                return formatter.parse(startDate).equals(formatter.parse(dateEl2));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return false;
        }).findFirst();

        Optional<WebElement> resAfter = elements.stream().filter(ele -> {
            try {
                return (formatter.parse(ele.getText().substring(2).split("\n")[0]).after(formatter.parse(startDate)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return false;
        }).findFirst();

        if (resEquals.isEmpty()) {
            return resAfter;
        } else
            return resEquals;

    }


    public Optional<WebElement> getCourseByCondition(List<WebElement> elements, String condition) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
        return elements.stream().reduce((el1, el2) -> {
            try {
                String dateEl1 = el1.getText().substring(2).split("\n")[0];
                String dateEl2 = el2.getText().substring(2).split("\n")[0];

                switch (condition) {
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
