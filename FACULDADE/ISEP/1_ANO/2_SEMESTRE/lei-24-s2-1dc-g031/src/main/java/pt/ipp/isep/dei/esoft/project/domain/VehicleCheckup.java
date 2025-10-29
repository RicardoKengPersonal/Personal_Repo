package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * The VehicleCheckup class represents a checkup performed on a vehicle at a specific date and kilometers.
 * It stores information about the vehicle's license plate, the date of the checkup, and the kilometers at the checkup.
 */
public class VehicleCheckup implements Serializable {
    private String plateNumber;
    private String date;
    private String kmAtCheckup;

    /**
     * Constructs a new VehicleCheckup object with the specified parameters.
     * @param plateNumber The license plate number of the vehicle
     * @param date The date of the checkup
     * @param kmAtCheckup The kilometers at the time of the checkup
     * @throws IllegalArgumentException if the plate, date, or kilometers at checkup format is invalid
     */
    public VehicleCheckup(String plateNumber, String date, String kmAtCheckup) throws IllegalArgumentException {
        validateDateFormat(date);
        validateKmAtCheckup(kmAtCheckup);

        this.plateNumber = plateNumber;
        this.date = date;
        this.kmAtCheckup = kmAtCheckup;
    }

    /**
     * Retrieves the license plate number of the vehicle associated with this checkup.
     * @return The license plate number of the vehicle
     */
    public String getPlate() {
        return plateNumber;
    }

    /**
     * Sets the license plate number of the vehicle associated with this checkup.
     * @param plate The license plate number to set
     * @throws IllegalArgumentException if the plate format is invalid
     */
    public void setPlate(String plate) {
        this.plateNumber = plate;
    }

    /**
     * Retrieves the date of the checkup.
     * @return The date of the checkup
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date of the checkup.
     * @param date The date of the checkup to set
     * @throws IllegalArgumentException if the date format is invalid
     */
    public void setDate(String date) throws IllegalArgumentException {
        validateDateFormat(date);
        this.date = date;
    }

    /**
     * Retrieves the kilometers at the time of the checkup.
     * @return The kilometers at the time of the checkup
     */
    public String getKmAtCheckup() {
        return kmAtCheckup;
    }

    /**
     * Sets the kilometers at the time of the checkup.
     * @param kmAtCheckup The kilometers at the time of the checkup to set
     * @throws IllegalArgumentException if the kilometers at checkup is negative
     */
    public void setKmAtCheckup(String kmAtCheckup) throws IllegalArgumentException {
        validateKmAtCheckup(kmAtCheckup);
        this.kmAtCheckup = kmAtCheckup;
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
     * Validates the kilometers at checkup to ensure it is non-negative.
     * @param kmAtCheckup The kilometers at the time of the checkup to validate
     * @throws IllegalArgumentException if the kilometers at checkup is negative
     */
    private void validateKmAtCheckup(String kmAtCheckup) throws IllegalArgumentException {
        try {
            int km = Integer.parseInt(kmAtCheckup);
            if (km < 0) {
                throw new IllegalArgumentException("Kilometers at checkup cannot be negative");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Kilometers at checkup must be a valid number", e);
        }
    }
}

