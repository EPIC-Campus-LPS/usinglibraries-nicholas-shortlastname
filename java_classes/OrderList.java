package java_classes;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Scanner;

public class OrderList {
    public static LinkedList<String> readFile(String fileName){
        LinkedList<String> list = new LinkedList<>();

        try {
            File txtFile = new File("C:\\Users\\nicholasu750_lpsk12\\IdeaProjects\\usinglibraries-nicholas-shortlastname\\"+fileName+".txt");
            Scanner reader = new Scanner(txtFile);

            while (reader.hasNextLine()){
                list.add(reader.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return list;
    }
    public static void writeFile(String sortType, LinkedList<String> input){
        try{
            File output = new File("C:\\Users\\nicholasu750_lpsk12\\IdeaProjects\\usinglibraries-nicholas-shortlastname\\java_classes\\orderingOutput\\"+sortType+".txt");
            if(output.createNewFile()){
                System.out.println("File created: " + output.getAbsoluteFile());
            } else {
                output.delete();
                output.createNewFile();
                System.out.println("File re-created: " + output.getAbsoluteFile());
            }

            FileWriter writer = new FileWriter(output.getAbsoluteFile());
            for (String str:input) {
                writer.write(str+"\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        LinkedList asciiList = new LinkedList();
        asciiList = readFile("input");

        Ordering<String> asciiOrder = Ordering
                .natural();


        asciiList.sort(asciiOrder);

        System.out.println(asciiList);

        writeFile("ascii", asciiList);
    }
}
