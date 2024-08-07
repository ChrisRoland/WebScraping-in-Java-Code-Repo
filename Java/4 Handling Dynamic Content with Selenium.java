// Handling Dynamic Content with Selenium

// <!-- Setting up Selenium with Java, First, add Selenium to your Maven pom.xml file -->
// <!-- Maven dependency -->
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>3.141.59</version>
</dependency>

// Automating Browsers with Selenium
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class SeleniumExample {
    public static void main(String[] args) {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Create a new instance of the Chrome driver
        WebDriver driver = new ChromeDriver();

        // Navigate to a webpage
        driver.get("https://example.com");

        // Find elements by CSS selector
        List<WebElement> elements = driver.findElements(By.cssSelector("a[href]"));
        for (WebElement element : elements) {
            System.out.println(element.getAttribute("href"));
        }

        // Close the browser
        driver.quit();
    }
}


//Waiting for elements to load
//Explicit waits
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumWaitExample {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();

        driver.get("https://example.com");

        // Wait up to 10 seconds for the element to be present
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("dynamicElementId")));

        System.out.println(element.getText());

        driver.quit();
    }
}


//Extracting Data from Dynamic Pages
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class DynamicContentScraper {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();

        driver.get("https://example-dynamic-website.com");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("table.dynamic-table")));

        List<WebElement> rows = driver.findElements(By.cssSelector("table.dynamic-table tr"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                System.out.print(cell.getText() + "\t");
            }
            System.out.println();
        }

        driver.quit();
    }
}

//Integrating Scrape.do with Selenium
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ScrapeDoIntegration {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        Proxy proxy = new Proxy();
        proxy.setHttpProxy("scrape.do.proxy:port");

        ChromeOptions options = new ChromeOptions();
        options.setProxy(proxy);

        WebDriver driver = new ChromeDriver(options);

        driver.get("https://example.com");

        // Your scraping code here

        driver.quit();
    }
}
