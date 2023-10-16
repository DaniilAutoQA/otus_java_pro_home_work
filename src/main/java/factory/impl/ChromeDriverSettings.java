package factory.impl;

import exceptions.DriverTypeNotSupported;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ChromeDriverSettings implements IBrowserSettings {


    @SuppressWarnings("checkstyle:Indentation")
    @Override
    public WebDriver webDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-fullscreen");
        chromeOptions.addArguments("--homepage=about:blank");

        if (getRemoteUrl() == null) {
            try {
                downloadLocalWebDriver(DriverManagerType.CHROME);
            } catch (DriverTypeNotSupported ex) {
                ex.printStackTrace();
            }
            return new ChromeDriver(chromeOptions);
        } else
            return new RemoteWebDriver(getRemoteUrl(), chromeOptions);
    }
}
