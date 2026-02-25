package pages;

import annotations.Path;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public abstract class AbsBasePage {

    protected Page page;
    protected String baseUrl;

    public AbsBasePage(Page page) {
        this.page = page;
        this.baseUrl = System.getenv().getOrDefault(
                "BASE_URL",
                "https://otus.ru"
        );
    }

    private String getPath() {
        Class clazz = getClass();
        if (clazz.isAnnotationPresent(Path.class)) {
            Path path = (Path) clazz.getDeclaredAnnotation(Path.class);
            return path.value();
        }
        return "";
    }

    public void open() {
        page.navigate(baseUrl + getPath());
    }

    public void headlineCheck(String title) {
        assertThat(page.getByRole(AriaRole.HEADING)).hasText(title);
    }
}