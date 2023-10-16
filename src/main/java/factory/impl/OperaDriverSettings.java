package factory.impl;

import exceptions.DriverTypeNotSupported;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class OperaDriverSettings implements IBrowserSettings {


    @Override
    public WebDriver webDriver() {
        ChromeOptions options = new ChromeOptions();
        //OperaOptions operaOptions = new OperaOptions();
        options.addArguments("--start-fullscreen");

        if (getRemoteUrl() == null) {
            try {
                downloadLocalWebDriver(DriverManagerType.OPERA);
            } catch (DriverTypeNotSupported ex) {
                ex.printStackTrace();
            }
            return new ChromeDriver();
        } else
            return new RemoteWebDriver(getRemoteUrl(), options);
    }
}


