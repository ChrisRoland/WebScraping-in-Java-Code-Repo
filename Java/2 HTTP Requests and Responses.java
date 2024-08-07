// HTTP Requests and Responses

// Sending a GET Request:
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;

public class HttpClientExample {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://example.com"))
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }
}

// Sending a POST Request
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.io.IOException;

public class HttpClientExample {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://example.com/api"))
            .header("Content-Type", "application/json")
            .POST(BodyPublishers.ofString("{\"key\":\"value\"}"))
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }
}

// Handling and Parsing JSON Responses using org.json
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;
import java.io.IOException;

public class JsonParsingExample {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://jsonplaceholder.typicode.com/todos/1"))
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject jsonResponse = new JSONObject(response.body());

        System.out.println(jsonResponse.getInt("id"));
        System.out.println(jsonResponse.getString("title"));
    }
}

// Error Handling and Retry Mechanisms

//Basic Error Handling
try {
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    if (response.statusCode() == 200) {
        System.out.println(response.body());
    } else {
        System.err.println("Error: " + response.statusCode());
    }
} catch (IOException | InterruptedException e) {
    e.printStackTrace();
}

//Retry Mechanism
int maxRetries = 3;
int retryCount = 0;
boolean success = false;

while (retryCount < maxRetries && !success) {
    try {
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            System.out.println(response.body());
            success = true;
        } else {
            System.err.println("Error: " + response.statusCode());
            retryCount++;
        }
    } catch (IOException | InterruptedException e) {
        retryCount++;
        e.printStackTrace();
    }

    if (!success && retryCount == maxRetries) {
        System.err.println("Max retries reached. Exiting.");
    }
}
