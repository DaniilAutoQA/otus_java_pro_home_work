package ru.otus.configuration;

import io.cucumber.guice.ScenarioScoped;
import org.openqa.selenium.WebDriver;
import ru.otus.factory.FactoryWebDriver;

@ScenarioScoped
public class GuiceScoped {

    public WebDriver driver = new FactoryWebDriver().getWebDriver();

}
