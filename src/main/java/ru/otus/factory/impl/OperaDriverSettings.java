package ru.otus.factory.impl;

import ru.otus.exceptions.DriverTypeNotSupported;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class OperaDriverSettings implements IBrowserSettings {

    @Override
    public WebDriver webDriver() {
        OperaOptions options = new OperaOptions();
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


