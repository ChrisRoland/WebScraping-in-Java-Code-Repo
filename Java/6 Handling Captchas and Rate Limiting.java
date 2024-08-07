//Handling Captchas and Rate Limiting

//Example of Integrating 2Captcha:
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

public class CaptchaSolver {
    private static final String API_KEY = "YOUR_2CAPTCHA_API_KEY";

    public static void main(String[] args) throws Exception {
        String siteKey = "SITE_KEY_FROM_WEBSITE";
        String pageUrl = "https://example.com";

        HttpClient client = HttpClient.newHttpClient();

        // Request captcha solution
        HttpRequest requestCaptcha = HttpRequest.newBuilder()
                .uri(URI.create("http://2captcha.com/in.php?key=" + API_KEY + "&method=userrecaptcha&googlekey=" + siteKey + "&pageurl=" + pageUrl))
                .build();
        HttpResponse<String> responseCaptcha = client.send(requestCaptcha, HttpResponse.BodyHandlers.ofString());
        String captchaId = responseCaptcha.body().split("\\|")[1];

        // Wait for captcha to be solved
        Thread.sleep(20000); // Adjust sleep time as needed

        // Get captcha solution
        HttpRequest requestSolution = HttpRequest.newBuilder()
                .uri(URI.create("http://2captcha.com/res.php?key=" + API_KEY + "&action=get&id=" + captchaId))
                .build();
        HttpResponse<String> responseSolution = client.send(requestSolution, HttpResponse.BodyHandlers.ofString());
        String solution = responseSolution.body().split("\\|")[1];

        System.out.println("Captcha Solution: " + solution);

        // Use captcha solution in your request
        HttpRequest dataRequest = HttpRequest.newBuilder()
                .uri(new URI(pageUrl))
                .POST(HttpRequest.BodyPublishers.ofString("g-recaptcha-response=" + solution))
                .build();
        HttpResponse<String> dataResponse = client.send(dataRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println("Data response: " + dataResponse.body());
    }
}

//Example of Rate Limiting:
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class RateLimitingExample {
    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        String[] urls = {"https://example.com/page1", "https://example.com/page2", "https://example.com/page3"};

        for (String url : urls) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response from " + url + ": " + response.body());

            // Delay between requests
            Thread.sleep(2000 + (int) (Math.random() * 3000)); // Random delay between 2 and 5 seconds
        }
    }
}


//User-Agent Rotation
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class RateLimitingExample {
    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        String[] urls = {"https://example.com/page1", "https://example.com/page2", "https://example.com/page3"};

        for (String url : urls) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response from " + url + ": " + response.body());

            // Delay between requests
            Thread.sleep(2000 + (int) (Math.random() * 3000)); // Random delay between 2 and 5 seconds
        }
    }
}

//Simulate Human Interactions
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class HumanSimulationExample {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://example.com");

        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.id("element-id"))).perform();
        actions.click().perform();
        actions.sendKeys("some text").perform();

        driver.quit();
    }
}

//Respect Robots.txt
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class RobotsTxtCheck {
    public static void main(String[] args) throws IOException {
        String websiteUrl = "https://example.com";
        String robotsTxtUrl = websiteUrl + "/robots.txt";
        
        try (Scanner scanner = new Scanner(new URL(robotsTxtUrl).openStream())) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (IOException e) {
            System.err.println("Error reading robots.txt: " + e.getMessage());
        }
    }
}
