package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * The Vehicle class represents a vehicle entity with various attributes such as plate, brand, model, etc.
 * It provides methods to access and manipulate these attributes.
 */
public class Vehicle implements Serializable {
    private String plate;
    private String brand;
    private String model;
    private String type;
    private float tareWeight;
    private float grossWeight;
    private float currentKm;
    private String registerDate;
    private String acquisitionDate;
    private float maintenanceFrequency;

    /**
     * Constructs a new Vehicle object with the specified parameters.
     * @param plate The license plate of the vehicle
     * @param brand The brand of the vehicle
     * @param model The model of the vehicle
     * @param type The type of the vehicle
     * @param tareWeight The tare weight of the vehicle
     * @param grossWeight The gross weight of the vehicle
     * @param currentKm The current kilometers traveled by the vehicle
     * @param registerDate The registration date of the vehicle
     * @param acquisitionDate The acquisition date of the vehicle
     * @param maintenanceFrequency The maintenance frequency of the vehicle
     * @throws IllegalArgumentException if the plate, date format, or any negative number is invalid
     */
    public Vehicle(String plate, String brand, String model, String type, float tareWeight, float grossWeight,
                   float currentKm, String registerDate, String acquisitionDate, float maintenanceFrequency) throws IllegalArgumentException {
        validateDateFormat(registerDate);
        validateDateFormat(acquisitionDate);
        validatePlate(plate, acquisitionDate);
        validateNonNegative(tareWeight, "Tare weight");
        validateNonNegative(grossWeight, "Gross weight");
        validateNonNegative(currentKm, "Current kilometers");
        validateNonNegative(maintenanceFrequency, "Maintenance frequency");

        this.plate = plate;
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.tareWeight = tareWeight;
        this.grossWeight = grossWeight;
        this.currentKm = currentKm;
        this.registerDate = registerDate;
        this.acquisitionDate = acquisitionDate;
        this.maintenanceFrequency = maintenanceFrequency;
    }

    /**
     * Validates the license plate based on the acquisition date.
     * @param plate The license plate to validate
     * @param acquisitionDate The acquisition date to use for validation
     * @throws IllegalArgumentException if the plate format is invalid
     */
    private void validatePlate(String plate, String acquisitionDate) throws IllegalArgumentException {
        int year = Integer.parseInt(acquisitionDate.substring(6, 10));
        String pattern;

        if (year > 2020) {
            pattern = "^[A-Z]{2}-\\d{2}-[A-Z]{2}$"; // AA-00-AA
        } else if (year >= 2005) {
            pattern = "^\\d{2}-[A-Z]{2}-\\d{2}$"; // 00-AA-00
        } else if (year >= 1992) {
            pattern = "^\\d{2}-\\d{2}-[A-Z]{2}$"; // 00-00-XX
        } else {
            throw new IllegalArgumentException("Acquisition date is before 1992, unsupported plate format");
        }

        if (!Pattern.matches(pattern, plate)) {
            throw new IllegalArgumentException("Invalid plate format for the given acquisition date");
        }
    }

    /**
     * Validates the date format.
     * @param dateString The date string to validate
     * @throws IllegalArgumentException if the date format is invalid
     */
    private void validateDateFormat(String dateString) throws IllegalArgumentException {
        if (!isValidDateFormat(dateString)) {
            throw new IllegalArgumentException("Invalid date format. Required format is dd-MM-yyyy");
        }
    }

    /**
     * Checks if the date string is in the valid format dd-MM-yyyy.
     * @param dateString The date string to check
     * @return true if the date format is valid, false otherwise
     */
    private static boolean isValidDateFormat(String dateString) {
        String regex = "^(0[1-9]|[1-2][0-9]|3[01])-(0[1-9]|1[0-2])-(\\d{4})$";
        Pattern p = Pattern.compile(regex);
        if (dateString == null) {
            return false;
        }
        Matcher m = p.matcher(dateString);
        return m.matches();
    }

    /**
     * Validates that a float value is non-negative.
     * @param value The value to validate
     * @param fieldName The name of the field being validated
     * @throws IllegalArgumentException if the value is negative
     */
    private void validateNonNegative(float value, String fieldName) throws IllegalArgumentException {
        if (value < 0) {
            throw new IllegalArgumentException(fieldName + " cannot be negative");
        }
    }

    /**
     * Retrieves the license plate number of the vehicle.
     * @return The license plate number of the vehicle
     */
    public String getPlate() {
        return plate;
    }

    /**
     * Retrieves the brand name of the vehicle.
     * @return The brand name of the vehicle
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Retrieves the model of the vehicle.
     * @return The model of the vehicle
     */
    public String getModel() {
        return model;
    }

    /**
     * Retrieves the type of the vehicle.
     * @return The type of the vehicle
     */
    public String getType() {
        return type;
    }

    /**
     * Retrieves the tare weight of the vehicle.
     * @return The tare weight of the vehicle
     */
    public float getTareWeight() {
        return tareWeight;
    }

    /**
     * Retrieves the gross weight of the vehicle.
     * @return The gross weight of the vehicle
     */
    public float getGrossWeight() {
        return grossWeight;
    }

    /**
     * Retrieves the current kilometers traveled by the vehicle.
     * @return The current kilometers traveled by the vehicle
     */
    public float getCurrentKm() {
        return currentKm;
    }

    /**
     * Retrieves the registration date of the vehicle.
     * @return The registration date of the vehicle
     */
    public String getRegisterDate() {
        return registerDate;
    }

    /**
     * Retrieves the acquisition date of the vehicle.
     * @return The acquisition date of the vehicle
     */
    public String getAcquisitionDate() {
        return acquisitionDate;
    }

    /**
     * Retrieves the maintenance frequency of the vehicle.
     * @return The maintenance frequency of the vehicle
     */
    public float getMaintenanceFrequency() {
        return maintenanceFrequency;
    }

    /**
     * Sets the license plate of the vehicle.
     * @param plate The license plate to set
     * @throws IllegalArgumentException if the plate format is invalid
     */
    public void setPlate(String plate) throws IllegalArgumentException {
        validatePlate(plate, this.acquisitionDate);
        this.plate = plate;
    }

    /**
     * Sets the brand of the vehicle.
     * @param brand The brand to set
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Sets the model of the vehicle.
     * @param model The model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Sets the type of the vehicle.
     * @param type The type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Sets the tare weight of the vehicle.
     * @param tareWeight The tare weight to set
     * @throws IllegalArgumentException if the tare weight is negative
     */
    public void setTareWeight(float tareWeight) throws IllegalArgumentException {
        validateNonNegative(tareWeight, "Tare weight");
        this.tareWeight = tareWeight;
    }

    /**
     * Sets the gross weight of the vehicle.
     * @param grossWeight The gross weight to set
     * @throws IllegalArgumentException if the gross weight is negative
     */
    public void setGrossWeight(float grossWeight) throws IllegalArgumentException {
        validateNonNegative(grossWeight, "Gross weight");
        this.grossWeight = grossWeight;
    }

    /**
     * Sets the current kilometers traveled by the vehicle.
     * @param currentKm The current kilometers to set
     * @throws IllegalArgumentException if the current kilometers are negative
     */
    public void setCurrentKm(float currentKm) throws IllegalArgumentException {
        validateNonNegative(currentKm, "Current kilometers");
        this.currentKm = currentKm;
    }

    /**
     * Sets the registration date of the vehicle.
     * @param registerDate The registration date to set
     * @throws IllegalArgumentException if the date format is invalid
     */
    public void setRegisterDate(String registerDate) throws IllegalArgumentException {
        validateDateFormat(registerDate);
        this.registerDate = registerDate;
    }

    /**
     * Sets the acquisition date of the vehicle.
     * @param acquisitionDate The acquisition date to set
     * @throws IllegalArgumentException if the date format is invalid
     */
    public void setAcquisitionDate(String acquisitionDate) throws IllegalArgumentException {
        validateDateFormat(acquisitionDate);
        validatePlate(this.plate, acquisitionDate);
        this.acquisitionDate = acquisitionDate;
    }

    /**
     * Sets the maintenance frequency of the vehicle.
     * @param maintenanceFrequency The maintenance frequency to set
     * @throws IllegalArgumentException if the maintenance frequency is negative
     */
    public void setMaintenanceFrequency(float maintenanceFrequency) throws IllegalArgumentException {
        validateNonNegative(maintenanceFrequency, "Maintenance frequency");
        this.maintenanceFrequency = maintenanceFrequency;
    }

    @Override
    public String toString() {
        return brand + " - " + model + " - " + plate;
    }
}