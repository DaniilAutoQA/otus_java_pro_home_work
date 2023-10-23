package pages;

import org.openqa.selenium.WebDriver;

public class Lessons extends AbsBasePage<Lessons> {

  public Lessons(WebDriver driver) {
    super(driver);
  }

  public String getPageTitle() {
    return driver.getTitle();
  }

}
