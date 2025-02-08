import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SeleniumTest {
    public static void main(String[] args) {
        // Automatski preuzima ChromeDriver
        WebDriverManager.chromedriver().setup();

        // Pokreće Chrome
        WebDriver driver = new ChromeDriver();

        // Implicit Wait - čeka do 10 sekundi da se svaki element učita
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Otvara Google stranicu
        driver.get("https://www.google.com");

        // Ispisuje naslov stranice u konzoli
        System.out.println("Page title is: " + driver.getTitle());

        // Explicit Wait - čeka da element postane klikabilan (npr. nekada bi ovo bio neki specifičan element)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.name("q"))); // Primjer čekanja da se input za pretragu postane klikabilan

        // Zatvara preglednik
        driver.quit();
    }
}
