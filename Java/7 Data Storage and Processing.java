//Data Storage and Processing

//Storing Data in CSV:
import java.io.FileWriter;
import java.io.IOException;
import com.opencsv.CSVWriter;

public class CSVStorage {
    public static void main(String[] args) {
        String[] header = { "Name", "Age", "Country" };
        String[] data1 = { "John Doe", "30", "USA" };
        String[] data2 = { "Jane Smith", "25", "UK" };

        try (CSVWriter writer = new CSVWriter(new FileWriter("data.csv"))) {
            writer.writeNext(header);
            writer.writeNext(data1);
            writer.writeNext(data2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//Storing Data in JSON:
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

public class JSONStorage {
    public static void main(String[] args) {
        JSONArray jsonArray = new JSONArray();

        JSONObject person1 = new JSONObject();
        person1.put("name", "John Doe");
        person1.put("age", 30);
        person1.put("country", "USA");

        JSONObject person2 = new JSONObject();
        person2.put("name", "Jane Smith");
        person2.put("age", 25);
        person2.put("country", "UK");

        jsonArray.put(person1);
        jsonArray.put(person2);

        try (FileWriter file = new FileWriter("data.json")) {
            file.write(jsonArray.toString(4)); // Indentation for readability
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Storing Data in a Database:
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseStorage {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String user = "root";
        String password = "password";

        String insertSQL = "INSERT INTO people (name, age, country) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            pstmt.setString(1, "John Doe");
            pstmt.setInt(2, 30);
            pstmt.setString(3, "USA");
            pstmt.executeUpdate();

            pstmt.setString(1, "Jane Smith");
            pstmt.setInt(2, 25);
            pstmt.setString(3, "UK");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

//Example of Data Cleaning:
import java.util.ArrayList;
import java.util.List;

public class DataCleaningExample {
    public static void main(String[] args) {
        List<String> rawData = List.of("  John Doe  ", " Jane Smith ", "   ");

        List<String> cleanedData = new ArrayList<>();
        for (String data : rawData) {
            String cleaned = data.trim(); // Remove leading and trailing spaces
            if (!cleaned.isEmpty()) { // Skip empty strings
                cleanedData.add(cleaned);
            }
        }

        cleanedData.forEach(System.out::println);
    }
}

//Using Apache POI for Handling Excel Files

// Maven Dependency
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>5.0.0</version>
</dependency>

//Writing Data to an Excel File:
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelStorageExample {
    public static void main(String[] args) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("People");

        String[] header = { "Name", "Age", "Country" };
        String[][] data = {
            { "John Doe", "30", "USA" },
            { "Jane Smith", "25", "UK" }
        };

        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < header.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(header[i]);
        }

        for (int i = 0; i < data.length; i++) {
            Row row = sheet.createRow(i + 1);
            for (int j = 0; j < data[i].length; j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(data[i][j]);
            }
        }

        try (FileOutputStream fileOut = new FileOutputStream("people.xlsx")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


