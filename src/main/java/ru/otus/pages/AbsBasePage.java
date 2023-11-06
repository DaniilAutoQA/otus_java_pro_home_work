package ru.otus.pages;

import com.google.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import ru.otus.actions.CommonActions;
import ru.otus.annotations.Path;
import ru.otus.configuration.GuiceScoped;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AbsBasePage<T> extends CommonActions<T> {

    @Inject
    public AbsBasePage(GuiceScoped guiceScoped) {
        super(guiceScoped);
    }


    private String getBaseUrl() {
        return StringUtils.stripEnd(System.getProperty("base.url", "https://otus.ru"), "/");
    }

    private String getPagePath() {
        Class<? extends AbsBasePage> clazz = this.getClass();
        if(clazz.isAnnotationPresent(Path.class)) {
            Path path = clazz.getAnnotation(Path.class);
            return path.value();
        }

        return "";
    }

    public void open() {
        driver.get(getBaseUrl() + getPagePath());
    }

    public void checkCurrentUrl(String url) {
        assertThat(driver.getCurrentUrl()).as("url страницы не соответствует").contains(url);
    }

}
