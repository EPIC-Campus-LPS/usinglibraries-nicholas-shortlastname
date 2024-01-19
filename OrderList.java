import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class OrderList {
    public LinkedList<String> readFile(String fileName){
        LinkedList<String> list = new LinkedList<>();

        try {
            File txtFile = new File(fileName + ".txt");
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
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list = readFile();
    }
}
