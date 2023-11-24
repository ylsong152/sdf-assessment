import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static final int FIRST_COL = 0;
    public static final int SECOND_COL = 1;
    public static final int THIRD_COL = 2;
    public static final int FOURTH_COL = 3;

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 2) {
            System.out.println("Please ensure that a CSV file is entered, followed by a template file.");
            System.exit(0);
        }

        try (BufferedReader csvBR = new BufferedReader(new FileReader(args[0]))) {
            String[] headerFields = csvBR.readLine().split(",");
            for (int i = 0; i < headerFields.length; i++) {
                headerFields[i] = "__" + headerFields[i] + "__";
            }

            Map<String, String> csvMap = new HashMap<>();
            String line;
            String templateString;
            while ((line = csvBR.readLine()) != null) {

                String[] dataFields = line.split(",");
                for (int i = 0; i < dataFields.length; i++) {
                    csvMap.put(headerFields[i], dataFields[i]);
                }

                BufferedReader txtBR = new BufferedReader(new FileReader(args[1]));

                while ((templateString = txtBR.readLine()) != null) {
                    String[] templateLine = templateString.split(" ");
                    for (int i = 0; i < templateLine.length; i++) {
                        templateLine[i] = templateLine[i].replaceAll("[^a-zA-Z0-9_ ]", "");
                        for (String key : csvMap.keySet()) {
                            if (templateLine[i].equals(key)) {
                                templateLine[i] = csvMap.get(key);
                            }
                        }
                        System.out.printf("%s ",templateLine[i]);
                        if (templateLine[i].isEmpty()) {
                            System.out.println("\n");
                        }

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}