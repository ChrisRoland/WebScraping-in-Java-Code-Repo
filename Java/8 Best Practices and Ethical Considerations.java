//Best Practices and Ethical Considerations

//Checking Robots.txt:
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class RobotsTxtChecker {
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

//Example of Responsible Scraping:
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class ResponsibleScraping {
    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        String[] urls = {"https://example.com/page1", "https://example.com/page2", "https://example.com/page3"};
        String[] userAgents = {
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36"
        };

        for (String url : urls) {
            int randomIndex = (int) (Math.random() * userAgents.length);
            String randomUserAgent = userAgents[randomIndex];

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .header("User-Agent", randomUserAgent)
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response from " + url + ": " + response.body());

            // Random delay between requests
            Thread.sleep(2000 + (int) (Math.random() * 3000));
        }
    }
}

//Example of Fetching Only Necessary Data:
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class EfficientDataExtraction {
    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://example.com"))
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Connection", "keep-alive")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Response: " + response.body());
    }
}

