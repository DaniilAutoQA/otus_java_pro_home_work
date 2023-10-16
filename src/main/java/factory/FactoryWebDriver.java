package factory;

import exceptions.DriverTypeNotSupported;
import factory.impl.ChromeDriverSettings;
import factory.impl.FirefoxDriverSettings;
import factory.impl.OperaDriverSettings;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import java.util.Locale;

public class FactoryWebDriver implements IFactoryWebDriver {

	private String browserName = System.getProperty("browser").toLowerCase(Locale.ROOT);

	@Override
	public EventFiringWebDriver getWebDriver() {
		switch (this.browserName) {
			case "chrome": {
				return new EventFiringWebDriver(new ChromeDriverSettings().webDriver());
			}
			case "firefox": {
				return new EventFiringWebDriver(new FirefoxDriverSettings().webDriver());
			}
			case "opera": {
				return new EventFiringWebDriver(new OperaDriverSettings().webDriver());
			}
			default:
				try {
					throw new DriverTypeNotSupported(this.browserName);
				} catch (DriverTypeNotSupported ex) {
					ex.printStackTrace(); return null;
				}
		}
	}
}
