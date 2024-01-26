package java_classes;

import com.google.common.base.Utf8;
import org.apache.commons.csv.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class readCSV {
    //I tried my best

    public static void main(String[] args) throws IOException {
        LinkedList<List<String>> csvRecords = new LinkedList<>();
        File csvData = new File("C:\\Users\\nicholasu750_lpsk12\\IdeaProjects\\usinglibraries-nicholas-shortlastname\\java_classes\\lego_sets_and_themes.csv");
        CSVParser parser = CSVParser.parse(csvData, Charset.defaultCharset(), CSVFormat.RFC4180);


        for (CSVRecord csvRecord : parser) {
            csvRecords.add(csvRecord.toList());
        }

        int counter = 0;
        float totalPieces = 0;
        for (List<String> record : csvRecords) {
            try {
                if(Float.parseFloat(record.get(3)) != 0.0f) {
                    totalPieces += Float.parseFloat(record.get(3));
                    counter += 1;
                }
            } catch (NumberFormatException e){
                continue;
            }
        }

        System.out.println("Average Pieces per set: " + totalPieces / counter);
        System.out.println("(Not including sets that have 0 pieces, such as DVDs and Books)");
    }
}
