//HTML Parsing with JSoup

//Fetching and Parsing HTML Docs
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JsoupExample {
    public static void main(String[] args) {
        try {
            Document doc = Jsoup.connect("https://example.com").get();
            System.out.println(doc.title());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//Selecting Elements Using CSS Selectors
Document doc = Jsoup.connect("https://example.com").get();
Elements links = doc.select("a[href]"); // Select all links
Elements paragraphs = doc.select("p"); // Select all paragraphs

for (Element link : links) {
    System.out.println(link.attr("href"));
}

for (Element paragraph : paragraphs) {
    System.out.println(paragraph.text());
}


//Extracting Data from HTML Elements
Element link = doc.select("a").first();
String linkText = link.text(); // Extract link text
String linkHref = link.attr("href"); // Extract link href attribute

System.out.println("Link text: " + linkText);
System.out.println("Link href: " + linkHref);

//Handling Different HTML Structures and Edge Cases
//Nested Elements
Elements articles = doc.select("div.article");
for (Element article : articles) {
    String title = article.select("h2.title").text();
    String content = article.select("div.content").text();
    System.out.println("Title: " + title);
    System.out.println("Content: " + content);
}

//Missing Elements
Element author = doc.select("div.author").first();
if (author != null) {
    System.out.println("Author: " + author.text());
} else {
    System.out.println("Author information not available");
}


//PRACTICAL EXAMPLE
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class NewsScraper {
    public static void main(String[] args) {
        String url = "https://example-site.com";
        try {
            Document doc = Jsoup.connect(url).get();
            Elements articles = doc.select("article");
            
            for (Element article : articles) {
                String title = article.select("h2.title").text();
                String link = article.select("a").attr("href");
                System.out.println("Title: " + title);
                System.out.println("Link: " + link);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
