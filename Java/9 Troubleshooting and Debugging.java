//Troubleshooting and Debugging

//Log HTTP Requests and Responses:
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpLogger {
    private static final Logger logger = Logger.getLogger(HttpLogger.class.getName());

    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://example.com"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        logger.log(Level.INFO, "Request URI: " + request.uri());
        logger.log(Level.INFO, "Response Status Code: " + response.statusCode());
        logger.log(Level.INFO, "Response Body: " + response.body());
    }
}

//Handle Exceptions
try {
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    System.out.println(response.body());
} catch (IOException | InterruptedException e) {
    e.printStackTrace();
    logger.log(Level.SEVERE, "Error occurred: " + e.getMessage());
}

//Log4j for Logging:

//Maven dependency
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-core</artifactId>
    <version>2.14.1</version>
</dependency>

//Log4j Configuration (log4j2.xml):
<Configuration status="WARN">
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n" />
        </Console>
        <File name="File" fileName="logs/app.log">
            <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n" />
        </File>
    </Appenders>
    <Loggers>
        <Root level="info">
            <AppenderRef ref="Console" />
            <AppenderRef ref="File" />
        </Root>
    </Loggers>
</Configuration>
