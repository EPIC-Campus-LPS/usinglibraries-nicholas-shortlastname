package java_classes;

import org.apache.commons.csv.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class readCSV {
    public static void main(String[] args) throws IOException {
        List<String> allData = null;
        File csvData = new File("C:\\Users\\nicholasu750_lpsk12\\IdeaProjects\\usinglibraries-nicholas-shortlastname\\java_classes\\lego_sets_and_themes.csv");
        CSVParser parser = CSVParser.parse(String.valueOf(csvData), CSVFormat.RFC4180);
        for (CSVRecord csvRecord : parser) {
            allData = csvRecord.toList();
        }

        for (String str:allData) {
            System.out.println(str);
        }
    }
}
