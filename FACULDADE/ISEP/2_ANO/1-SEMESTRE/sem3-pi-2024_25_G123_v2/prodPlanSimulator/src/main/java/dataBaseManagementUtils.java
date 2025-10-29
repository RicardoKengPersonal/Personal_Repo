import oracle.jdbc.OracleTypes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class dataBaseManagementUtils {
    // Utility method to get database connection
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521/XE",
                "SYS as SYSDBA",
                "password"
        );
    }



    public static String createOrder(int customerId, String orderDate, String deliveryDate, int variant_Id, int quantity) {
        String resultMessage = "";
        Connection connection = null;
        CallableStatement callableStatement = null;

        try {
            connection = getConnection();

            // Step 1: Validate that the variant ID exists in the Variants table
            String sqlCheckVariant = "SELECT 1 FROM Variants WHERE variant_id = ?";
            callableStatement = connection.prepareCall(sqlCheckVariant);
            callableStatement.setInt(1, variant_Id);
            ResultSet rs = callableStatement.executeQuery();
            if (!rs.next()) {
                rs.close(); // Ensure ResultSet is closed
                callableStatement.close(); // Close CallableStatement after use
                resultMessage = "Error: Variant ID " + variant_Id + " does not exist in the Variants table.";
                return resultMessage;
            }
            rs.close();
            callableStatement.close();

            // Step 2: Call the Create_Order_Function to generate the order
            String sqlFunctionCall = "{ ? = call CreateOrderForCustomer(?, ?, ?, ?, ?) }";
            callableStatement = connection.prepareCall(sqlFunctionCall);
            callableStatement.registerOutParameter(1, Types.VARCHAR); // Register return type (Result message)
            callableStatement.setInt(2, customerId); // Customer ID
            callableStatement.setDate(3, java.sql.Date.valueOf(orderDate)); // Order date
            callableStatement.setDate(4, java.sql.Date.valueOf(deliveryDate)); // Delivery date
            callableStatement.setInt(5, variant_Id); // Variant ID
            callableStatement.setInt(6, quantity); // Quantity

            // Execute the stored function
            callableStatement.execute();

            // Get the result message from the function
            resultMessage = callableStatement.getString(1);

        } catch (SQLException e) {
            resultMessage = "Error: Unable to create the order - " + e.getMessage();
        } finally {
            try {
                if (callableStatement != null) callableStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println("Error: Unable to close database resources - " + e.getMessage());
            }
        }

        return resultMessage;
    }

    // Method to deactivate a customer
    public static String deactivateCustomer(int customerId) {
        String resultMessage = "";
        Connection connection = null;
        CallableStatement callableStatement = null;

        try {
            connection = getConnection();

            String sqlFunctionCall = "{ ? = call Deactivate_Customer_Function(?) }";

            callableStatement = connection.prepareCall(sqlFunctionCall);
            callableStatement.registerOutParameter(1, Types.VARCHAR);
            callableStatement.setInt(2, customerId);
            callableStatement.execute();

            resultMessage = callableStatement.getString(1);

        } catch (SQLException e) {
            resultMessage = "Error: Unable to process the request - " + e.getMessage();
        } finally {
            try {
                if (callableStatement != null) callableStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println("Error: Unable to close database resources - " + e.getMessage());
            }
        }

        return resultMessage;
    }

    // Method to get parts of a product
    public static void getProductParts(int productVariantId) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();

            String sqlFunctionCall = "{ ? = call GET_PRODUCT_PARTS(?) }";

            callableStatement = connection.prepareCall(sqlFunctionCall);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setInt(2, productVariantId);
            callableStatement.execute();

            resultSet = (ResultSet) callableStatement.getObject(1);

            System.out.println("Parts List for Product Variant ID: " + productVariantId);
            System.out.println("----------------------------------------------------");
            while (resultSet.next()) {
                int partId = resultSet.getInt("part_id");
                int totalQuantity = resultSet.getInt("total_quantity");
                String unit = resultSet.getString("unit");
                System.out.println("Part ID: " + partId + " | Quantity: " + totalQuantity + " | Unit: " + unit);
            }

        } catch (SQLException e) {
            System.out.println("Error: Unable to retrieve parts list - " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (callableStatement != null) callableStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println("Error: Unable to close database resources - " + e.getMessage());
            }
        }
    }

    // Method to get operations and workstation types for a product
    public static void getProductOperations(int productId) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();

            String sqlFunctionCall = "{ ? = call GETPRODUCTOPERATIONS(?) }";

            callableStatement = connection.prepareCall(sqlFunctionCall);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.setInt(2, productId);
            callableStatement.execute();

            resultSet = (ResultSet) callableStatement.getObject(1);

            System.out.println("Operations and Workstation Types for Product ID: " + productId);
            System.out.println("-------------------------------------------------------------");
            System.out.println("Operation ID | Description           | Workstation Type ID | Workstation Type Name");
            System.out.println("-------------------------------------------------------------");
            while (resultSet.next()) {
                int operationId = resultSet.getInt("operation_id");
                String description = resultSet.getString("operation_description");
                int workstationTypeId = resultSet.getInt("workstation_type_id");
                String workstationTypeName = resultSet.getString("workstation_type_name");

                System.out.printf("%12d | %-20s | %18d | %-20s\n",
                        operationId, description, workstationTypeId, workstationTypeName);
            }

        } catch (SQLException e) {
            System.out.println("Error: Unable to retrieve product operations - " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (callableStatement != null) callableStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println("Error: Unable to close database resources - " + e.getMessage());
            }
        }
    }

    // Method to get variants that use all workstation types
    public static void getVariantsUsingAllWorkstations() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();

            // Calling the PL/SQL function to get variants using all workstation types
            String sqlFunctionCall = "{ ? = call Get_Variant_Using_All_Workstations() }";

            callableStatement = connection.prepareCall(sqlFunctionCall);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR); // Output as a cursor
            callableStatement.execute();

            // Get the result set
            resultSet = (ResultSet) callableStatement.getObject(1);

            System.out.println("Variants using all workstation types:");
            System.out.println("-------------------------------------");
            System.out.println("Variant ID | Variant Description");
            System.out.println("-------------------------------------");

            while (resultSet.next()) {
                int variantId = resultSet.getInt("variant_id");
                String description = resultSet.getString("variant_description");
                System.out.printf("%10d | %-20s\n", variantId, description);
            }

        } catch (SQLException e) {
            System.out.println("Error: Unable to retrieve variants - " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (callableStatement != null) callableStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println("Error: Unable to close database resources - " + e.getMessage());
            }
        }
    }

    // Method to export data to CSV files based on variantId
    public static void exportDataToCSV(int variantId) {
        Connection connection = null;

        try {
            connection = getConnection();

            exportOperationsToCSV(connection, variantId);
            exportItemsToCSV(connection, variantId);
            exportBOOToCSV(connection, variantId);

            System.out.println("CSV files generated successfully for Variant ID: " + variantId);
        } catch (SQLException | IOException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println("Error: Unable to close database resources - " + e.getMessage());
            }
        }
    }

    private static void exportOperationsToCSV(Connection connection, int variantId) throws SQLException, IOException {
        String query = """
                SELECT DISTINCT op.operation_id AS op_id, op.operation_description AS op_name
                FROM "Bill of Operations" boo
                JOIN Operation op ON boo.operation_id = op.operation_id
                WHERE boo.finished_variant_id = ?
                """;

        try (PreparedStatement stmt = connection.prepareStatement(query);
             BufferedWriter writer = new BufferedWriter(new FileWriter("operations.csv"))) {
            stmt.setInt(1, variantId);
            ResultSet rs = stmt.executeQuery();

            writer.write("op_id,op_name\n");
            while (rs.next()) {
                writer.write(rs.getInt("op_id") + "," + rs.getString("op_name") + "\n");
            }
        }
    }

    private static void exportItemsToCSV(Connection connection, int variantId) throws SQLException, IOException {
        String query = """
                SELECT DISTINCT v.variant_id AS id_item, v.variant_description AS item_name
                FROM "Inputted Variants" iv
                JOIN Variants v ON iv.variant_id = v.variant_id
                JOIN "Bill of Operations" boo ON iv.input_id = boo.input_id
                WHERE boo.finished_variant_id = ?
                """;

        try (PreparedStatement stmt = connection.prepareStatement(query);
             BufferedWriter writer = new BufferedWriter(new FileWriter("items.csv"))) {
            stmt.setInt(1, variantId);
            ResultSet rs = stmt.executeQuery();

            writer.write("id_item,item_name\n");
            while (rs.next()) {
                writer.write(rs.getInt("id_item") + "," + rs.getString("item_name") + "\n");
            }
        }
    }

    private static void exportBOOToCSV(Connection connection, int variantId) throws SQLException, IOException {
        String query = """
                WITH BOOData AS (
                  SELECT
                    boo.finished_variant_id AS op_id,
                    iv.variant_id AS item_id,
                    iv.quantity AS item_qtd,
                    LISTAGG(op.operation_id || ',' || boo.sequence_number, ',')\s
                      WITHIN GROUP (ORDER BY op.operation_id) AS operations,
                    LISTAGG(iv.variant_id || ',' || iv.quantity, ',')\s
                      WITHIN GROUP (ORDER BY iv.variant_id) AS items
                  FROM "Bill of Operations" boo
                  JOIN "Inputted Variants" iv ON boo.input_id = iv.input_id
                  JOIN Operation op ON boo.operation_id = op.operation_id
                  WHERE boo.finished_variant_id = ?
                  GROUP BY boo.finished_variant_id, iv.variant_id, iv.quantity
                )
                SELECT op_id, item_id, item_qtd, operations, items
                FROM BOOData
                """;

        try (PreparedStatement stmt = connection.prepareStatement(query);
             BufferedWriter writer = new BufferedWriter(new FileWriter("boo.csv"))) {

            // Set the variantId as a parameter
            stmt.setInt(1, variantId);

            // Execute query and process results
            ResultSet rs = stmt.executeQuery();

            // Write the header row
            writer.write("op_id,item_id,item_qtd,(op_id1,op_qtd1,...,op_idN,op_qtdN),(item_id1,item_qtd1,...,item_idN,item_qtdN)\n");

            // Iterate through the result set and write rows
            while (rs.next()) {
                writer.write(rs.getInt("op_id") + "," +
                        rs.getInt("item_id") + "," +
                        rs.getInt("item_qtd") + ",(" +
                        rs.getString("operations") + "),(" +
                        rs.getString("items") + ")\n");
            }
        }
    }

    // Method to register a workstation || USBD15
    public static void registerWorkstation(String workstationName, String workstationDescription, int workstationTypeId, String workstationLegacyId) {
        Connection connection = null;
        CallableStatement callableStatement = null;

        try {
            connection = getConnection();

            String sqlFunctionCall = "{ ? = call registerWorkstation(?, ?, ?, ?) }";

            callableStatement = connection.prepareCall(sqlFunctionCall);
            callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
            callableStatement.setString(2, workstationName);
            callableStatement.setString(3, workstationDescription);
            callableStatement.setInt(4, workstationTypeId);
            if (workstationLegacyId != null) {
                callableStatement.setString(5, workstationLegacyId);
            } else {
                callableStatement.setNull(5, java.sql.Types.VARCHAR); // Set to NULL if no legacy ID
            }

            callableStatement.execute();

            String result = callableStatement.getString(1);
            System.out.println("Result: " + result);

        } catch (SQLException e) {
            System.err.println("Error: Unable to register workstation - " + e.getMessage());
        } finally {
            try {
                if (callableStatement != null) callableStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println("Error: Unable to close database resources - " + e.getMessage());
            }
        }
    }

    public static void GetTopProductByOperations() {
        Connection connection = null;
        CallableStatement enableOutput = null;
        CallableStatement callableStatement = null;

        try {
            connection = getConnection();

            enableOutput = connection.prepareCall("{CALL DBMS_OUTPUT.ENABLE()}");
            enableOutput.execute();

            // chama o procedimento "GetTopProductByOperations" e executa
            callableStatement = connection.prepareCall("{CALL GetTopProductByOperations}");
            callableStatement.execute();

            String line;
            CallableStatement getOutput = connection.prepareCall("{CALL DBMS_OUTPUT.GET_LINE(?, ?)}");
            // variável que armazena a string(output)
            getOutput.registerOutParameter(1, Types.VARCHAR);
            // variável que armazena o status(0 ou 1, onde 0 indica que ainda há linhas para ler)
            getOutput.registerOutParameter(2, Types.INTEGER);

            while (true) {
                getOutput.execute();
                // guarda a linha de output e o status
                line = getOutput.getString(1);
                int status = getOutput.getInt(2);

                // se status for diferente de 0, não há mais linhas para ler
                if (status != 0) {
                    break;
                }
                // imprime a linha
                System.out.println(line);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (callableStatement != null) callableStatement.close();
                if (enableOutput != null) enableOutput.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void RegisterProduct(String productName, String familyID, String productDescription) {
        Connection connection = null;
        CallableStatement callableStatement = null;

        try {
            connection = getConnection();

            String sqlFunctionCall = "{ ? = call RegisterProduct(?, ?, ?) }";

            callableStatement = connection.prepareCall(sqlFunctionCall);
            callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
            callableStatement.setString(2, productName);
            if (!Objects.equals(familyID, "")) {
                callableStatement.setInt(3, Integer.parseInt(familyID));
            } else {
                callableStatement.setNull(3, java.sql.Types.INTEGER); // Set to NULL if no legacy ID
            }
            if (productDescription != null) {
                callableStatement.setString(4, productDescription);
            } else {
                callableStatement.setNull(4, java.sql.Types.VARCHAR); // Set to NULL if no legacy ID
            }

            callableStatement.execute();

            String result = callableStatement.getString(1);
            System.out.println("Result: " + result);

        } catch (SQLException e) {
            System.err.println("Error: Unable to register workstation - " + e.getMessage());
        } finally {
            try {
                if (callableStatement != null) callableStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println("Error: Unable to close database resources - " + e.getMessage());
            }
        }
    }

    public static boolean isValidDate(String date) {
        try {
            LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean deliveryDateIsAfter(String orderDate, String deliveryDate) {
        LocalDate order = LocalDate.parse(orderDate);
        LocalDate delivery = LocalDate.parse(deliveryDate);
        return delivery.isAfter(order);
    }

    // Method to retrieve operations for a product
    public static void getProductOperationsDetails(int productId) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();

            // Define the SQL function call
            String sqlFunctionCall = "{ ? = call get_product_operations(?) }";

            callableStatement = connection.prepareCall(sqlFunctionCall);

            // Register the output parameter and set the input parameter
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR); // Output cursor
            callableStatement.setInt(2, productId); // Input product ID

            // Execute the function
            callableStatement.execute();

            // Retrieve the cursor from the output parameter
            resultSet = (ResultSet) callableStatement.getObject(1);

            // Process the result set
            System.out.println("Operations for Product ID: " + productId);
            System.out.println("------------------------------------------------");
            System.out.println("Product ID | Operation ID | Sequence Number");
            System.out.println("------------------------------------------------");

            while (resultSet.next()) {
                int idProduct = resultSet.getInt("idProduct");
                int idOperation = resultSet.getInt("idOperation");
                int sequenceNumber = resultSet.getInt("sequenceNumber");

                System.out.printf("%10d | %12d | %15d\n", idProduct, idOperation, sequenceNumber);
            }
        } catch (SQLException e) {
            System.err.println("Error: Unable to retrieve product operations - " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (callableStatement != null) callableStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println("Error: Unable to close database resources - " + e.getMessage());
            }
        }
    }

    // Method to check the stock for the given order ID
    public static void checkOrderStock(int orderId) {
        String sql = "{? = call check_order_stock(?)}"; // SQL function call

        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            // Register the output parameter (function return value)
            stmt.registerOutParameter(1, Types.VARCHAR);
            // Set the input parameter (order ID)
            stmt.setInt(2, orderId);

            // Execute the function
            stmt.execute();

            // Get the result
            String result = stmt.getString(1);

            // Print the exact message returned by the database
            System.out.println(result);

        } catch (SQLException e) {
            // Print SQL exception messages
            System.err.println("SQL Exception: " + e.getMessage());
        }
    }



    //USBD 28

    public static void callFetchReservedProducts() {
        try (Connection connection = getConnection()) {
            // Prepare the callable statement for the procedure
            String procedureCall = "{CALL fetch_reserved_products(?, ?)}";
            try (CallableStatement callableStatement = connection.prepareCall(procedureCall)) {

                // Register the OUT parameters
                callableStatement.registerOutParameter(1, OracleTypes.CURSOR); // For result_cursor
                callableStatement.registerOutParameter(2, Types.VARCHAR);     // For status_message

                // Execute the procedure
                callableStatement.execute();

                // Retrieve the status message
                String statusMessage = callableStatement.getString(2);
                System.out.println("Status Message: " + statusMessage);

                // Retrieve and process the cursor
                try (ResultSet resultSet = (ResultSet) callableStatement.getObject(1)) {
                    System.out.println("Reserved Products:");
                    while (resultSet.next()) {
                        int reservedProductID = resultSet.getInt("ReservedProductID");
                        String productDescription = resultSet.getString("ProductDescription");
                        int reservedQuantity = resultSet.getInt("ReservedQuantity");
                        int supplierID = resultSet.getInt("SupplierID");
                        String supplierName = resultSet.getString("SupplierName");

                        System.out.printf("ID: %d, Description: %s, Quantity: %d, Supplier ID: %d, Supplier Name: %s%n",
                                reservedProductID, productDescription, reservedQuantity, supplierID, supplierName);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Database error: " + e.getMessage());
        }
    }


    // Method to call the consume_material procedure
    public static void consumeMaterial(int productId, int quantity) {
        try (Connection connection = getConnection()) {
            String call = "{CALL consume_material(?, ?)}";
            try (CallableStatement callableStatement = connection.prepareCall(call)) {
                // Set the input parameters
                callableStatement.setInt(1, productId);
                callableStatement.setInt(2, quantity);

                // Execute the procedure
                callableStatement.execute();
                System.out.println("Material consumed successfully.");
            } catch (SQLException e) {
                if (e.getErrorCode() == 20001) {
                    System.err.println("Error: " + e.getMessage());
                } else {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
