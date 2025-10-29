package Lapr3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OperationLoader {

    /**
     * Loads operation data from a CSV file and returns a map of operation descriptions.
     *
     * @param filePath the path of the CSV file to read
     * @return a map with operation IDs as keys and operation descriptions as values
     * @throws IOException if there is an issue with reading the file
     */
    public static Map<String, String> loadOperations(String filePath) throws IOException {
        Map<String, String> operationDescriptions = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            // Skip the header if present (Optional)
            br.readLine();

            // Read each line and extract operation data
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(";");
                if (fields.length == 2) {
                    String operationId = fields[0].trim();    // operation ID
                    String description = fields[1].trim();    // operation description

                    // Store operationId as key and description as value
                    operationDescriptions.put(operationId, description);
                }
            }
        }

        return operationDescriptions;
    }
}
