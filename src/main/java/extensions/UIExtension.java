package extensions;

import annotations.Driver;
import factory.FactoryWebDriver;
import listeners.MouseListener;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;

public class UIExtension implements BeforeEachCallback, AfterEachCallback {

    private EventFiringWebDriver driver = null;

    private List<Field> getAnnotatedFields(Class<? extends Annotation> annotation, ExtensionContext extensionContext) {
        List<Field> set = new ArrayList<>();
        Class<?> testClass = extensionContext.getTestClass().get();
        while (testClass != null) {
            for (Field field : testClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(annotation)) {
                    set.add(field);
                }
            }
            testClass = testClass.getSuperclass();
        }
        return set;
    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) {
        driver = new FactoryWebDriver().getWebDriver();
        driver.register(new MouseListener());
        List<Field> fields = getAnnotatedFields(Driver.class, extensionContext);

        for (Field field : fields) {
            if (field.getType().getName().equals(WebDriver.class.getName())) {
                AccessController.doPrivileged((PrivilegedAction<Void>)
                        () -> {
                            try {
                                field.setAccessible(true);
                                field.set(extensionContext.getTestInstance().get(), driver);
                            } catch (IllegalAccessException e) {
                                throw new Error(String.format("Could not access or set webdriver in field: %s - is this field public?", field), e);
                            }
                            return null;
                        }
                );
            }
        }
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}
