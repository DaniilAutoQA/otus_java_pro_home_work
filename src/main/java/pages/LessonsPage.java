package pages;

import org.openqa.selenium.WebDriver;


public class LessonsPage extends AbsBasePage<LessonsPage> {

    public LessonsPage(WebDriver driver) {
        super(driver);
    }

    public void printPageTitle(){
        System.out.println(driver.getTitle());
    }

}
