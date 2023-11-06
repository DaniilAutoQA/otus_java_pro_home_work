package ru.otus.factory.impl;

import ru.otus.exceptions.DriverTypeNotSupported;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.Config;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public interface IBrowserSettings {

    String REMOTE_URL = System.getProperty("webdriver.remote.url");

    public WebDriver webDriver();

    default URL getRemoteUrl() {
        try {
            return new URL(REMOTE_URL);
        } catch (MalformedURLException e) {
            return null;
        }
    }

    default void downloadLocalWebDriver(DriverManagerType driverType) throws DriverTypeNotSupported {

        String browserVersion = System.getProperty("browser.version", "");

        if (!browserVersion.isEmpty()) {
            switch (driverType) {
                case CHROME:
                    Config wdmConfig = WebDriverManager.chromedriver().config();
                    wdmConfig.setAvoidBrowserDetection(true);
                    wdmConfig.setChromeDriverVersion(browserVersion);
                    break;
                case FIREFOX:
                    Config wdmConfigFirefox = WebDriverManager.firefoxdriver().config();
                    wdmConfigFirefox.setAvoidBrowserDetection(true);
                    wdmConfigFirefox.setFirefoxVersion(browserVersion);
                    break;
                case OPERA:
                    Config wdmConfigOpera = WebDriverManager.operadriver().config();
                    wdmConfigOpera.setAvoidBrowserDetection(true);
                    wdmConfigOpera.setOperaVersion(browserVersion);
                    break;
                default:
                    throw new DriverTypeNotSupported(driverType);
            }
        }

        WebDriverManager.getInstance(driverType).setup();
    }
}
