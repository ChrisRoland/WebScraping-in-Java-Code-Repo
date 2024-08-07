//Managing Sessions and Cookies

//Using HttpClient to maintain sessions
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.CookieHandler;
import java.net.http.HttpClient.Builder;
import java.util.List;
import java.net.http.HttpHeaders;
import java.net.http.HttpCookie;

public class SessionManagementExample {
    public static void main(String[] args) throws Exception {
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);

        HttpClient client = HttpClient.newBuilder()
                .cookieHandler(cookieManager)
                .build();

        // Login request
        HttpRequest loginRequest = HttpRequest.newBuilder()
                .uri(new URI("https://example.com/login"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString("username=user&password=pass"))
                .build();

        HttpResponse<String> loginResponse = client.send(loginRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println("Login response: " + loginResponse.body());

        // Subsequent request using the same session
        HttpRequest dataRequest = HttpRequest.newBuilder()
                .uri(new URI("https://example.com/data"))
                .build();

        HttpResponse<String> dataResponse = client.send(dataRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println("Data response: " + dataResponse.body());
    }
}


//Extracting and Using Cookies
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpHeaders;
import java.net.http.HttpCookie;
import java.util.List;

public class ManualCookieManagement {
    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        // Initial request to get cookies
        HttpRequest initialRequest = HttpRequest.newBuilder()
                .uri(new URI("https://example.com"))
                .build();

        HttpResponse<String> initialResponse = client.send(initialRequest, HttpResponse.BodyHandlers.ofString());
        HttpHeaders headers = initialResponse.headers();
        List<String> setCookieHeaders = headers.allValues("Set-Cookie");

        // Parse cookies
        StringBuilder cookieHeader = new StringBuilder();
        for (String setCookie : setCookieHeaders) {
            List<HttpCookie> cookies = HttpCookie.parse(setCookie);
            for (HttpCookie cookie : cookies) {
                if (cookieHeader.length() > 0) {
                    cookieHeader.append("; ");
                }
                cookieHeader.append(cookie.toString());
            }
        }

        // Subsequent request using cookies
        HttpRequest dataRequest = HttpRequest.newBuilder()
                .uri(new URI("https://example.com/data"))
                .header("Cookie", cookieHeader.toString())
                .build();

        HttpResponse<String> dataResponse = client.send(dataRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println("Data response: " + dataResponse.body());
    }
}

//Using CookieHandler
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CookieHandlerExample {
    public static void main(String[] args) throws Exception {
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);

        HttpClient client = HttpClient.newBuilder()
                .cookieHandler(cookieManager)
                .build();

        // Initial request to login
        HttpRequest loginRequest = HttpRequest.newBuilder()
                .uri(new URI("https://example.com/login"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString("username=user&password=pass"))
                .build();

        HttpResponse<String> loginResponse = client.send(loginRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println("Login response: " + loginResponse.body());

        // Subsequent request using the same session
        HttpRequest dataRequest = HttpRequest.newBuilder()
                .uri(new URI("https://example.com/data"))
                .build();

        HttpResponse<String> dataResponse = client.send(dataRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println("Data response: " + dataResponse.body());
    }
}
