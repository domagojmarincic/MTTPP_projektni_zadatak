import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTest {
    public static void main(String[] args) {
        // Automatski preuzima ChromeDriver
        WebDriverManager.chromedriver().setup();

        // Pokreće Chrome
        WebDriver driver = new ChromeDriver();

        // Otvara Google stranicu
        driver.get("https://www.google.com");

        // Ispisuje naslov stranice u konzoli
        System.out.println("Page title is: " + driver.getTitle());

        // Zatvara preglednik
        driver.quit();
    }
}

