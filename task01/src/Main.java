import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {
    public static final int FIRST_COL = 0;
    public static final int SECOND_COL = 1;
    public static final int THIRD_COL = 2;
    public static final int FOURTH_COL = 3;
    
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Please ensure that a CSV file is entered, followed by a template file.");
            System.exit(0);
        }

        try(BufferedReader br = new BufferedReader(new FileReader(args[1]))) {
            String key = "";
            String line = "";

            while ((line = br.readLine()) != null) {
                key += line;
            }
            System.out.println(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}