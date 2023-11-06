package ru.otus.actions;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import ru.otus.configuration.GuiceScoped;
import ru.otus.waiters.StandartWaiter;

public abstract class CommonActions<T> {

    protected WebDriver driver;
    protected StandartWaiter standartWaiter;
    protected GuiceScoped guiceScoped;

    @Inject
    public CommonActions(GuiceScoped guiceScoped) {
        this.guiceScoped = guiceScoped;
        this.driver = guiceScoped.driver;
        this.standartWaiter = new StandartWaiter(driver);

        PageFactory.initElements(guiceScoped.driver, this);
    }

    public void clickByElement(WebElement element){
        element.click();
    }

}
