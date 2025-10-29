import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class dataBaseManagementUI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Data Base Management System");

        while (true) {
            System.out.println("\nPlease select an option:");
            System.out.println("1. Deactivate a Customer");
            System.out.println("2. Get Product Parts");
            System.out.println("3. Get Product Operations");
            System.out.println("4. Get Variants Using All Workstations");
            System.out.println("5. Export Data to CSV (Filtered by Variant ID)");
            System.out.println("6. Register a Workstation");
            System.out.println("7. Get which product has more operations in its BOO");
            System.out.println("8. Create Order");
            System.out.println("9. Register Product");
            System.out.println("SPRINT 3");
            System.out.println("10. Get Product Operations V2");
            System.out.println("11. Get Reserved materials and components");
            System.out.println("12. Deduct amount from stock");
            System.out.println("13. Check order stock");
            System.out.println("14. Exit");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter the Customer ID to deactivate: ");
                    int customerId;
                    try {
                        customerId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Customer ID must be a number.");
                        continue;
                    }
                    String result = dataBaseManagementUtils.deactivateCustomer(customerId);
                    System.out.println(result);
                    break;

                case 2:
                    System.out.print("Enter the Product Variant ID to get parts: ");
                    int productVariantId;
                    try {
                        productVariantId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Product Variant ID must be a number.");
                        continue;
                    }
                    dataBaseManagementUtils.getProductParts(productVariantId);
                    break;

                case 3:
                    System.out.print("Enter the Product ID to get operations: ");
                    int productId;
                    try {
                        productId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Product ID must be a number.");
                        continue;
                    }
                    dataBaseManagementUtils.getProductOperations(productId);
                    break;

                case 4:
                    dataBaseManagementUtils.getVariantsUsingAllWorkstations();
                    break;

                case 5:
                    System.out.print("Enter the Variant ID to export data: ");
                    int variantId;
                    try {
                        variantId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Variant ID must be a number.");
                        continue;
                    }

                    dataBaseManagementUtils.exportDataToCSV(variantId);

                    break;

                case 6:
                    System.out.println("Enter the Workstation name to register: ");
                    String workstationName = scanner.nextLine();

                    System.out.println("Enter the Workstation Description: ");
                    String workstationDescription = scanner.nextLine();

                    System.out.println("Enter the Workstation Type ID: ");
                    int workstationTypeId;
                    try {
                        workstationTypeId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Workstation Type ID must be a number.");
                        continue;
                    }

                    System.out.println("Enter the Workstation Legacy ID: ");
                    String workstationLegacyId = scanner.nextLine();

                    dataBaseManagementUtils.registerWorkstation(workstationName, workstationDescription, workstationTypeId, workstationLegacyId);
                    break;

                case 7:
                    dataBaseManagementUtils.GetTopProductByOperations();
                    break;

                case 8:
                    int customerId1;
                    int variantId1;
                    int quantity;
                    String orderDate;
                    String deliveryDate;

// Prompt and validate Customer ID
                    while (true) {
                        System.out.print("Enter the Customer ID (must be a positive number): ");
                        try {
                            customerId1 = Integer.parseInt(scanner.nextLine());
                            if (customerId1 > 0) {
                                break;
                            } else {
                                System.out.println("Customer ID must be a positive number. Please try again.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Customer ID must be a number. Please try again.");
                        }
                    }

// Prompt and validate Variant ID
                    while (true) {
                        System.out.print("Enter the Product ID (Variant ID) for the order (must be a positive number): ");
                        try {
                            variantId1 = Integer.parseInt(scanner.nextLine());
                            if (variantId1 > 0) {
                                break;
                            } else {
                                System.out.println("Variant ID must be a positive number. Please try again.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Variant ID must be a number. Please try again.");
                        }
                    }

// Prompt and validate Quantity
                    while (true) {
                        System.out.print("Enter the Quantity for the order (must be a positive number): ");
                        try {
                            quantity = Integer.parseInt(scanner.nextLine());
                            if (quantity > 0) {
                                break;
                            } else {
                                System.out.println("Quantity must be a positive number. Please try again.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Quantity must be a number. Please try again.");
                        }
                    }

// Prompt and validate Order Date
                    while (true) {
                        System.out.print("Enter the Order Date (YYYY-MM-DD): ");
                        orderDate = scanner.nextLine();
                        if (dataBaseManagementUtils.isValidDate(orderDate)) {
                            break;
                        } else {
                            System.out.println("Invalid date format. Please enter a valid date in the format YYYY-MM-DD.");
                        }
                    }

// Prompt and validate Delivery Date
                    while (true) {
                        System.out.print("Enter the Delivery Date (YYYY-MM-DD): ");
                        deliveryDate = scanner.nextLine();
                        if (dataBaseManagementUtils.isValidDate(deliveryDate) && dataBaseManagementUtils.deliveryDateIsAfter(orderDate, deliveryDate)) {
                            break;
                        } else if (!dataBaseManagementUtils.isValidDate(deliveryDate)) {
                            System.out.println("Invalid date format. Please enter a valid date in the format YYYY-MM-DD.");
                        } else {
                            System.out.println("Delivery date must be after the order date. Please try again.");
                        }
                    }

// Display the order details
                    System.out.println("\nOrder details:");
                    System.out.println("Customer ID: " + customerId1);
                    System.out.println("Order Date: " + orderDate);
                    System.out.println("Delivery Date: " + deliveryDate);
                    System.out.println("Product Ordered: Variant ID: " + variantId1 + ", Quantity: " + quantity);

// Call the function to create an order and display the result
                    System.out.println(dataBaseManagementUtils.createOrder(customerId1, orderDate, deliveryDate, variantId1, quantity));

// After placing the order, return to the main menu
                    break;

                case 9:
                    String productName = null;
                    System.out.println("Enter the Product Name to register: ");
                    while (true) {
                        productName = scanner.nextLine();
                        if (productName.isEmpty()) {
                            System.out.println("Product Name cannot be null. Please enter a valid name.");
                        } else {
                            break;
                        }
                    }

                    System.out.println("Enter the Family ID: ");
                    String fID = null;
                    while (true) {
                        int familyID;
                        try {
                            fID = scanner.nextLine().trim();
                            familyID = Integer.parseInt(fID);
                            break;
                        } catch (NumberFormatException e) {
                            if (Objects.equals(fID, "")) {
                                break;
                            } else {
                                System.out.println("Invalid input. Family ID must be a number.");
                            }
                        }
                    }

                    System.out.println("Enter the Product Description: ");
                    String productDescription = scanner.nextLine();

                    dataBaseManagementUtils.RegisterProduct(productName, fID, productDescription);

                    break;

                case 10:
                    System.out.println("Enter the Product ID to get operations: ");
                    int productId2;
                    while (true) {
                        try {
                            productId2 = Integer.parseInt(scanner.nextLine().trim());
                            if (productId2 > 0) {
                                break;
                            } else {
                                System.out.println("Product ID must be a positive number. Please try again.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Product ID must be a number. Please try again.");
                        }
                    }

                    try {
                        dataBaseManagementUtils.getProductOperationsDetails(productId2);
                    } catch (Exception e) {
                        System.out.println("An error occurred while fetching product operations: " + e.getMessage());
                    }
                    break;

                case 11:
                    dataBaseManagementUtils.callFetchReservedProducts();
                    break;
                case 12: // Consume Material
                    int productId3;
                    int quantity3;

                    // Prompt and validate Product ID
                    while (true) {
                        System.out.print("Enter the Product ID to consume material: ");
                        try {
                            productId3 = Integer.parseInt(scanner.nextLine());
                            if (productId3 > 0) {
                                break;
                            } else {
                                System.out.println("Product ID must be a positive number. Please try again.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Product ID must be a number. Please try again.");
                        }
                    }

                    // Prompt and validate Quantity
                    while (true) {
                        System.out.print("Enter the quantity to consume: ");
                        try {
                            quantity3 = Integer.parseInt(scanner.nextLine());
                            if (quantity3 > 0) {
                                break;
                            } else {
                                System.out.println("Quantity must be a positive number. Please try again.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Quantity must be a number. Please try again.");
                        }
                    }

                    // Call the consumeMaterial method
                    try {
                        dataBaseManagementUtils.consumeMaterial(productId3, quantity3);
                    } catch (Exception e) {
                        System.out.println("An error occurred while consuming material: " + e.getMessage());
                    }
                    break;
                case 13:
                    System.out.println("Enter the Order ID: ");
                    int orderid1;
                    while (true) {
                        try {
                            orderid1 = Integer.parseInt(scanner.nextLine().trim());
                            if (orderid1 > 0) {
                                break;
                            } else {
                                System.out.println("Order ID must be a positive number. Please try again.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Order ID must be a number. Please try again.");
                        }
                    }

                    try {
                        dataBaseManagementUtils.checkOrderStock(orderid1);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 14:
                    System.out.println("Exiting the system. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }
    }
}
