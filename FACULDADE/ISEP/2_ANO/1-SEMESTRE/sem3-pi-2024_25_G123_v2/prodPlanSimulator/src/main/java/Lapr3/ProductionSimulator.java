package Lapr3;

import org.example.Domain.Article;
import org.example.Domain.Workstation;
import org.example.ProductionSimulator.Simulator;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class ProductionSimulator {

    private Simulator simulator;

    // Constructor that receives the simulator
    public ProductionSimulator(Simulator simulator) {
        this.simulator = simulator;
    }

    // Method to update the average production times in the database
    public void updateWorkstationExecutionTimes() {
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521/XE";
        String username = "SYS as SYSDBA";
        String password = "password";

        String updateSql = "UPDATE workstationtype SET MAXIMUMEXECUTIONTIME = CASE " +
                "WHEN idWorkstationType = 1 THEN 100 " +
                "WHEN idWorkstationType = 2 THEN 150 " +
                "WHEN idWorkstationType = 3 THEN 200 " +
                "WHEN idWorkstationType = 4 THEN 250 " +
                "WHEN idWorkstationType = 5 THEN 300 " +
                "WHEN idWorkstationType = 6 THEN 350 " +
                "WHEN idWorkstationType = 7 THEN 400 " +
                "WHEN idWorkstationType = 8 THEN 450 " +
                "WHEN idWorkstationType = 9 THEN 500 " +
                "WHEN idWorkstationType = 10 THEN 550 " +
                "WHEN idWorkstationType = 11 THEN 600 " +
                "WHEN idWorkstationType = 12 THEN 650 " +
                "WHEN idWorkstationType = 13 THEN 700 " +
                "ELSE MAXIMUMEXECUTIONTIME " +
                "END";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement pstmt = conn.prepareStatement(updateSql)) {
            int rowsUpdated = pstmt.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated);
        } catch (SQLException e) {
            System.err.println("Error updating workstation execution times: " + e.getMessage());
        }
    }


    /*--------------------------------------Workstation methods--------------------------------------*/

    // Method to create a csv file with the workstation operation times
    public void saveWorkstationOperationTimesOnCSV(Map<String, Workstation> workstationsList) {
        try {
            FileWriter csvWriter = new FileWriter("./prodPlanSimulator/src/main/java/Lapr3/workstations.csv");
            csvWriter.append("Workstation ID;Workstation Name;Workstation Operation Time\n");
            for (Map.Entry<String, Workstation> entry : workstationsList.entrySet()) {
                String workstationId = entry.getKey();
                Workstation workstation = entry.getValue();
                String workstationName = workstation.getOperation();
                int operationTime = workstation.getTime();
                csvWriter.append(workstationId + ";" + workstationName + ";" + operationTime + "\n");
            }
            csvWriter.flush();
            csvWriter.close();
            System.out.println("CSV file created successfully!");
        } catch (IOException e) {
            System.err.println("Error creating CSV file: " + e.getMessage());
        }
    }

    // Method to save workstation operation times in the database
    public Map<String, Workstation> saveWorkstationOperationTimes() {
        // Database connection details
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521/XE";
        String username = "SYS as SYSDBA";
        String password = "password";

        // SQL query to select workstationId and maximumExecutionTime
        String sql = "SELECT w.idWorkstation, w.name ,wt.maximumExecutionTime " +
                "FROM workstation w " +
                "JOIN workstationType wt ON w.idWorkstationType = wt.idWorkstationType";

        Map<String, Workstation> workstationsList = new HashMap<>();
        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String workstationId = rs.getString("idWorkstation");
                String workstationName = rs.getString("name");
                int maxExecutionTime = rs.getInt("maximumExecutionTime");

                // Create a new Workstation object
                Workstation workstation = new Workstation(workstationId, workstationName, maxExecutionTime);

                // Add the workstation to the map
                workstationsList.put(workstationId, workstation);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching workstation times: " + e.getMessage());
        }
        return workstationsList;
    }


    // Method to display workstation ID and corresponding operation times
    public void displayWorkstationOperationTimes(Map<String, Workstation> workstationsList) {
        System.out.println("Workstation ID | Workstation Name | Workstation Operation Time");
        for (Map.Entry<String, Workstation> entry : workstationsList.entrySet()) {
            String workstationId = entry.getKey();
            Workstation workstation = entry.getValue();
            String workstationName = workstation.getOperation();
            int operationTime = workstation.getTime();
            System.out.printf("%s | %s | %d%n", workstationId, workstationName, operationTime);
        }
    }

    /*--------------------------------------Operation methods--------------------------------------*/

    // Method to create a csv file with the operation descriptions
    public void saveOperationDescriptionsOnCSV(Map<String, String> operationDescriptions) {
        try {
            FileWriter csvWriter = new FileWriter("./prodPlanSimulator/src/main/java/Lapr3/operations.csv");
            csvWriter.append("Operation ID;Operation Description\n");
            for (Map.Entry<String, String> entry : operationDescriptions.entrySet()) {
                String operationId = entry.getKey();
                String operationDesc = entry.getValue();
                csvWriter.append(operationId + ";" + operationDesc + "\n");
            }
            csvWriter.flush();
            csvWriter.close();
            System.out.println("CSV file created successfully!");
        } catch (IOException e) {
            System.err.println("Error creating CSV file: " + e.getMessage());
        }
    }

    // Method to save operationId and operation descriptions in the database
    public Map<String, String> saveOperationDescriptions() {
        // Database connection details
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521/XE";
        String username = "SYS as SYSDBA";
        String password = "password";

        // SQL query to select operationId and operation description
        String sql = "SELECT o.idOperation, ot.description " +
                "FROM operation o " +
                "JOIN operationType ot ON o.idOperationType = ot.idOperationType";

        Map<String, String> operationDescriptions = new HashMap<>();
        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String operationId = String.valueOf(rs.getInt("idOperation"));
                String operationDesc = rs.getString("description");

                // Add the operationId and description to the map
                operationDescriptions.put(operationId, operationDesc);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching operation names: " + e.getMessage());
        }

        return operationDescriptions;
    }

    // Method to display OperationId and corresponding operation descriptions
    public void displayOperationDescriptions(Map<String, String> operationDescriptions) {
        System.out.println("Operation ID | Operation Description");
        for (Map.Entry<String, String> entry : operationDescriptions.entrySet()) {
            String operationId = entry.getKey();
            String operationDesc = entry.getValue();
            System.out.printf("%s | %s%n", operationId, operationDesc);
        }
    }

    /*--------------------------------------Product methods--------------------------------------*/

    // Method to create a csv file with the products information
    public void saveProductsNameOnCSV(Map<String, String> productsList) {
        try {
            FileWriter csvWriter = new FileWriter("./prodPlanSimulator/src/main/java/Lapr3/products.csv");
            csvWriter.append("Product ID;Product Description\n");
            for (Map.Entry<String, String> entry : productsList.entrySet()) {
                String productId = entry.getKey();
                String productDesc = entry.getValue();
                csvWriter.append(productId + ";" + productDesc + "\n");
            }
            csvWriter.flush();
            csvWriter.close();
            System.out.println("CSV file created successfully!");
        } catch (IOException e) {
            System.err.println("Error creating CSV file: " + e.getMessage());
        }
    }

    // Method to save productId and product description in the database
    public Map<String, String> saveProductsName() {
        // Database connection details
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521/XE";
        String username = "SYS as SYSDBA";
        String password = "password";

        // SQL query to select productId and product description
        String sql = "SELECT idProduct, description FROM product";

        Map<String, String> productsList = new HashMap<>();
        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String productId = String.valueOf(rs.getInt("idProduct"));
                String productDesc = rs.getString("description");

                // Add the productId and description to the map
                productsList.put(productId, productDesc);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching product names: " + e.getMessage());
        }

        return productsList;
    }


    // Method to display the ProductsId and the respective description
    public void displayProductsName(Map<String, String> productsList) {
        System.out.println("Product ID | Product Description");
        for (Map.Entry<String, String> entry : productsList.entrySet()) {
            String productId = entry.getKey();
            String productDesc = entry.getValue();
            System.out.printf("%s | %s%n", productId, productDesc);
        }
    }

    /*--------------------------------------Order methods--------------------------------------*/

    //Method to create a csv file with the orders information
    public void saveOrdersInformationOnCSV(Map<Integer, Order> ordersList) {
        try {
            FileWriter csvWriter = new FileWriter("./prodPlanSimulator/src/main/java/Lapr3/orders.csv");
            csvWriter.append("Order ID;Product ID;Priority;Quantity\n");
            for (Map.Entry<Integer, Order> entry : ordersList.entrySet()) {
                int orderId = entry.getValue().getOrderId();
                Order order = entry.getValue();
                int productId = order.getProductId();
                String priority = order.getPriority();
                int quantity = order.getQuantity();
                csvWriter.append(orderId + ";" + productId + ";" + priority + ";" + quantity + "\n");
            }
            csvWriter.flush();
            csvWriter.close();
            System.out.println("CSV file created successfully!");
        } catch (IOException e) {
            System.err.println("Error creating CSV file: " + e.getMessage());
        }
    }

    // Method to save order information in the database
    public Map<Integer, Order> saveOrdersInformation() {
        // Database connection details
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521/XE";
        String username = "SYS as SYSDBA";
        String password = "password";

        // SQL query to select order information
        String sql = "SELECT " +
                "    co.idOrder AS orderId, " +
                "    v.idProduct AS productId, " +
                "    po.priority, " +
                "    oi.quantity " +
                "FROM " +
                "    customerOrder co " +
                "JOIN " +
                "    orderedItem oi ON co.idOrder = oi.idOrder " +
                "JOIN " +
                "    productionOrder po ON co.idOrder = po.idOrder " +
                "JOIN " +
                "    variant v ON oi.idVariant = v.idVariant";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            Map<Integer, Order> ordersList = new HashMap<>();
            int count = 0;
            while (rs.next()) {
                count++;
                // Fetch values, handling possible NULL values
                int orderId = rs.getInt("orderId");
                int productId = rs.getInt("productId");
                String priority = rs.getString("priority");
                int quantity = rs.getInt("quantity");

                // Handle NULL checks for the fetched values (in case of any NULL)
                if (rs.wasNull()) {
                    System.err.println("Error: Null value found for one of the columns. Skipping this row.");
                    continue;  // Skip invalid row
                }

                // Create a new Order object with the fetched data
                Order order = new Order(orderId, productId, priority, quantity);

                // Add the order to the map, using orderId as the key
                ordersList.put(count, order);
            }

            return ordersList;

        } catch (SQLException e) {
            System.err.println("Error fetching order information: " + e.getMessage());
        }
        return null;
    }

    // Method to display order information
    public void displayOrdersInformation(Map<Integer, Order> ordersList) {
        System.out.println("Order ID | Product ID | Priority | Quantity");
        for (Map.Entry<Integer, Order> entry : ordersList.entrySet()) {
            int orderId = entry.getValue().getOrderId();
            Order order = entry.getValue();
            int productId = order.getProductId();
            String priority = order.getPriority();
            int quantity = order.getQuantity();
            System.out.printf("%d | %d | %s | %d%n", orderId, productId, priority, quantity);
        }
    }

    /*--------------------------------------Tree methods--------------------------------------*/


}
