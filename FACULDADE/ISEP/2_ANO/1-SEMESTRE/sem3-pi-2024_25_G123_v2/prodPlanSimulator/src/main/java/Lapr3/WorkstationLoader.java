package Lapr3;

import org.example.Domain.Workstation;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WorkstationLoader {

    // Method to load workstations from a CSV file
    public static Map<String, Workstation> loadWorkstations(String filePath) {
        Map<String, Workstation> workstationsList = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Skip the header line if your CSV contains column headers
            br.readLine();

            // Read each line in the CSV file
            while ((line = br.readLine()) != null) {
                // Split the line into columns (assuming semicolon delimiter)
                String[] columns = line.split(";");

                if (columns.length == 3) {
                    // Extract the data from the columns
                    String workstationId = columns[0].trim();
                    String workstationName = columns[1].trim();
                    int operationTime = Integer.parseInt(columns[2].trim());

                    // Create a new Workstation object
                    Workstation workstation = new Workstation(workstationId, workstationName, operationTime);

                    // Put the workstation in the map with the workstation ID as the key
                    workstationsList.put(workstationId, workstation);
                }
            }

        } catch (IOException e) {
            System.err.println("Error loading workstations from file: " + e.getMessage());
        }

        return workstationsList;
    }
}
