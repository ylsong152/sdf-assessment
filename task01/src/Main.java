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

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String[] headerFields = br.readLine().split(",");
            List<String> headers = new LinkedList<>();
            for (int i = 0; i < headerFields.length; i++) {
                headers.add(headerFields[i]);
            }

            for (String header : headers) {
                System.out.println(header);
            }

            String line;
            Map<Integer, String> fieldsMap = new HashMap<>();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                // System.out.println(fields[0]);
                // System.out.println(fields[1]);
                // System.out.println(fields[2]);
                // System.out.println(fields[3]);
                for (int i = 0; i < fields.length; i++) {
                    fieldsMap.put(i, fields[i]);
                }
                for (Integer i : fieldsMap.keySet()) {
                    Integer key = i;
                    String value = fieldsMap.get(key);
                    System.out.println(key + " " + value);
                }
        }

          

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}