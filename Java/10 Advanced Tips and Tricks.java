//Advanced Tips and Tricks

//Using Headless Chrome with Selenium:
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HeadlessBrowserExample {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://example.com");

        System.out.println("Title: " + driver.getTitle());
        driver.quit();
    }
}

//Example of Parallel Scraping:
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParallelScrapingExample {
    public static void main(String[] args) throws Exception {
        List<String> urls = Arrays.asList(
                "https://example.com/page1",
                "https://example.com/page2",
                "https://example.com/page3"
        );

        ExecutorService executor = Executors.newFixedThreadPool(3);
        HttpClient client = HttpClient.newHttpClient();

        for (String url : urls) {
            executor.submit(() -> {
                try {
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(new URI(url))
                            .build();
                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    System.out.println("Response from " + url + ": " + response.body());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
    }
}

//Use Streams for Efficient Data Processing:
import java.util.Arrays;
import java.util.List;

public class StreamExample {
    public static void main(String[] args) {
        List<String> data = Arrays.asList("apple", "banana", "cherry");

        data.stream()
            .filter(item -> item.startsWith("a"))
            .forEach(System.out::println);
    }
}

// Practical Example: Optimized Web Scraping Script
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class OptimizedScrapingExample {
    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Headless browser setup
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://example.com");
        System.out.println("Title: " + driver.getTitle());
        driver.quit();

        // Parallel scraping setup
        List<String> urls = Arrays.asList(
                "https://example.com/page1",
                "https://example.com/page2",
                "https://example.com/page3"
        );

        ExecutorService executor = Executors.newFixedThreadPool(3);
        HttpClient client = HttpClient.newHttpClient();

        for (String url : urls) {
            executor.submit(() -> {
                try {
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(new URI(url))
                            .build();
                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    System.out.println("Response from " + url + ": " + response.body());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
    }
}
