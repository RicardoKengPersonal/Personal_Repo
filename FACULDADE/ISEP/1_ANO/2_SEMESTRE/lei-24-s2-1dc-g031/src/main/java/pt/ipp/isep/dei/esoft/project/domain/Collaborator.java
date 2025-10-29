package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Period;

/**
 * This class represents a Collaborator in the system.
 * It contains information about the collaborator such as name, birth date, admission date, address, mobile number, email, tax payer number, identification document type, document identification number, skills and job.
 */
public class Collaborator implements Serializable {
    private String name;
    private String birthDate;
    private String admissionDate;
    private String address;
    private int mobileNumber;
    private String email;
    private String taxPayerNumber;
    private DocumentType identificationDocumentType;
    private String documentIdentificationNumber;
    private ArrayList<Skill> skills;
    private Job job;

    /**
     * Constructor for the Collaborator class.
     * @param name The name of the collaborator.
     * @param documentIdentificationNumber The document identification number of the collaborator.
     * @param taxPayerNumber The tax payer number of the collaborator.
     * @param email The email of the collaborator.
     * @param mobileNumber The mobile number of the collaborator.
     * @param address The address of the collaborator.
     * @param admissionDate The admission date of the collaborator.
     * @param birthDate The birth date of the collaborator.
     * @param identificationDocumentType The identification document type of the collaborator.
     * @param job The job of the collaborator.
     * @throws IllegalArgumentException if any of the parameters are not valid.
     */
    public Collaborator(String name, String documentIdentificationNumber, String taxPayerNumber, String email, int mobileNumber, String address, String admissionDate, String birthDate, DocumentType identificationDocumentType, Job job) {
        try{
            setName(name);
            setBirthDate(birthDate);
            setAdmissionDate(admissionDate);
            setAddress(address);
            setMobileNumber(mobileNumber);
            setEmail(email);
            setTaxPayerNumber(taxPayerNumber);
            setIdentificationDocumentType(identificationDocumentType);
            setDocumentIdentificationNumber(documentIdentificationNumber);
            this.job = job;
            this.skills = new ArrayList<>();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * Adds a skill to the collaborator's skills.
     * @param skill The skill to be added.
     */
    public void addSkillToCollaboratorSkills(Skill skill) {
        if (!skillAlreadyAssigned(skill)) {
            this.skills.add(skill);
        }
    }

    /**
     * Checks if the collaborator is at least 18 years old.
     * @param dateString The date to be checked.
     * @return true if the collaborator is at least 18 years old, false otherwise.
     */
    public static boolean isAtLeast18YearsOld(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(dateString, formatter);
        LocalDate date18YearsAgo = LocalDate.now().minusYears(18);
        return !date.isAfter(date18YearsAgo);
    }

    /**
     * Checks if a skill is already assigned to the collaborator.
     * @param skill The skill to be checked.
     * @return true if the skill is already assigned, false otherwise.
     */
    public boolean skillAlreadyAssigned(Skill skill) {
        for (Skill sk : this.skills) {
            if (sk.getNameOfTheSkill().equals(skill.getNameOfTheSkill())) {
                System.out.println(skill.getNameOfTheSkill() + " - Skill already assigned");
                return true;
            }
        }
        return false;
    }

    // Getters and setters with Javadoc comments go here...

    /**
     * Checks if a phone number is valid.
     * A valid phone number is a 9-digit number.
     * @param phone The phone number to be checked.
     * @return true if the phone number is valid, false otherwise.
     */
    private static boolean isValidPhone(int phone) {
        String phoneStr = Integer.toString(phone);
        return phoneStr.length() == 9 && isNumeric(phoneStr);
    }

    /**
     * Checks if a date is in the correct format (dd-MM-yyyy).
     * @param dateString The date to be checked.
     * @return true if the date is in the correct format, false otherwise.
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
     * Checks if two dates are at least 18 years apart.
     * @param firstDateString The first date.
     * @param secondDateString The second date.
     * @return true if the dates are at least 18 years apart, false otherwise.
     */
    public static boolean isAtLeast18YearsApart(String firstDateString, String secondDateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate firstDate = LocalDate.parse(firstDateString, formatter);
        LocalDate secondDate = LocalDate.parse(secondDateString, formatter);
        return Period.between(firstDate, secondDate).getYears() >= 18;
    }

    /**
     * Checks if a string is numeric.
     * @param str The string to be checked.
     * @return true if the string is numeric, false otherwise.
     */
    private static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a name is valid.
     * A valid name contains only letters and spaces.
     * @param name The name to be checked.
     * @return true if the name is valid, false otherwise.
     */
    private static boolean isValidName(String name) {
        String regex = "^[a-zA-Z ]+$";
        Pattern p = Pattern.compile(regex);

        if (name == null) {
            return false;
        }

        Matcher m = p.matcher(name);
        return m.matches();
    }

    /**
     * Checks if a tax payer number is valid.
     * A valid tax payer number is a 9-digit number.
     * @param number The tax payer number to be checked.
     * @return true if the tax payer number is valid, false otherwise.
     */
    private static boolean isValidTaxPayerNumber(String number) {
        return number != null && number.matches("[0-9]{9}");
    }

    /**
     * Sets the name of the collaborator.
     * @param name The new name of the collaborator.
     * @throws IllegalArgumentException if the name is not valid.
     */
    public void setName(String name) {
        if (!isValidName(name)) {
            throw new IllegalArgumentException("Invalid name. Name should only contain letters and spaces.");
        }
        this.name = name;
    }

    /**
     * Sets the birth date of the collaborator.
     * @param birthDate The new birth date of the collaborator.
     * @throws IllegalArgumentException if the birth date is not valid.
     */
    public void setBirthDate(String birthDate) {
        if (!isValidDateFormat(birthDate) || !isAtLeast18YearsOld(birthDate)) {
            throw new IllegalArgumentException("Invalid birth date. Birth date should be in the format dd-MM-yyyy and the collaborator should be at least 18 years old.");
        }
        this.birthDate = birthDate;
    }

    /**
     * Sets the admission date of the collaborator.
     * @param admissionDate The new admission date of the collaborator.
     * @throws IllegalArgumentException if the admission date is not valid.
     */
    public void setAdmissionDate(String admissionDate) {
        if (!isValidDateFormat(admissionDate) || !isAtLeast18YearsApart(this.birthDate, admissionDate)) {
            throw new IllegalArgumentException("Invalid admission date. Admission date should be in the format dd-MM-yyyy and the collaborator should be at least 18 years old at the time of admission.");
        }
        this.admissionDate = admissionDate;
    }

    /**
     * Sets the address of the collaborator.
     * @param address The new address of the collaborator.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Sets the mobile number of the collaborator.
     * @param mobileNumber The new mobile number of the collaborator.
     * @throws IllegalArgumentException if the mobile number is not valid.
     */
    public void setMobileNumber(int mobileNumber) {
        if (!isValidPhone(mobileNumber)) {
            throw new IllegalArgumentException("Invalid mobile number. Mobile number should be a 9-digit number.");
        }
        this.mobileNumber = mobileNumber;
    }

    /**
     * Sets the email of the collaborator.
     * @param email The new email of the collaborator.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the tax payer number of the collaborator.
     * @param taxPayerNumber The new tax payer number of the collaborator.
     * @throws IllegalArgumentException if the tax payer number is not valid.
     */
    public void setTaxPayerNumber(String taxPayerNumber) {
        if (!isValidTaxPayerNumber(taxPayerNumber)) {
            throw new IllegalArgumentException("Invalid tax payer number. Tax payer number should be a 9-digit number.");
        }
        this.taxPayerNumber = taxPayerNumber;
    }

    /**
     * Sets the identification document type of the collaborator.
     * @param identificationDocumentType The new identification document type of the collaborator.
     */
    public void setIdentificationDocumentType(DocumentType identificationDocumentType) {
        this.identificationDocumentType = identificationDocumentType;
    }

    /**
     * Validates a Portuguese Citizen Card number.
     * @param citizenCardNumber The citizen card number to be validated.
     * @return true if the citizen card number is valid, false otherwise.
     */
    private static boolean isValidCitizenCardNumber(String citizenCardNumber) {
        return citizenCardNumber != null && citizenCardNumber.matches("[0-9]{8}[0-9][a-zA-Z]{2}[0-9]");
    }

    /**
     * Validates a Portuguese Passport number.
     * @param passportNumber The passport number to be validated.
     * @return true if the passport number is valid, false otherwise.
     */
    private static boolean isValidPassportNumber(String passportNumber) {
        return passportNumber != null && passportNumber.matches("[0-9]{7}");
    }

    /**
     * Validates a Portuguese Driver's License number.
     * @param driversLicenseNumber The driver's license number to be validated.
     * @return true if the driver's license number is valid, false otherwise.
     */
    private static boolean isValidDriversLicenseNumber(String driversLicenseNumber) {
        return driversLicenseNumber != null && driversLicenseNumber.matches("[0-9]{9}");
    }

    /**
     * Sets the document identification number of the collaborator.
     * @param documentIdentificationNumber The new document identification number of the collaborator.
     * @throws IllegalArgumentException if the document identification number is not valid.
     */
    public void setDocumentIdentificationNumber(String documentIdentificationNumber) {
        switch (this.identificationDocumentType) {
            case ID_CARD:
                if (!isValidCitizenCardNumber(documentIdentificationNumber)) {
                    throw new IllegalArgumentException("Invalid Citizen Card number. It should have 12 alphanumeric characters: 8 digits, 2 letters, 2 digits.");
                }
                break;
            case PASSPORT:
                if (!isValidPassportNumber(documentIdentificationNumber)) {
                    throw new IllegalArgumentException("Invalid Passport number. It should have 7 digits.");
                }
                break;
            case DRIVERS_LICENSE:
                if (!isValidDriversLicenseNumber(documentIdentificationNumber)) {
                    throw new IllegalArgumentException("Invalid Driver's License number. It should have 9 digits.");
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid document type.");
        }
        this.documentIdentificationNumber = documentIdentificationNumber;
    }

    /**
     * Gets the name of the collaborator.
     * @return The name of the collaborator.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the birth date of the collaborator.
     * @return The birth date of the collaborator.
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * Gets the admission date of the collaborator.
     * @return The admission date of the collaborator.
     */
    public String getAdmissionDate() {
        return admissionDate;
    }

    /**
     * Gets the address of the collaborator.
     * @return The address of the collaborator.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets the mobile number of the collaborator.
     * @return The mobile number of the collaborator.
     */
    public int getMobileNumber() {
        return mobileNumber;
    }

    /**
     * Gets the email of the collaborator.
     * @return The email of the collaborator.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the tax payer number of the collaborator.
     * @return The tax payer number of the collaborator.
     */
    public String getTaxPayerNumber() {
        return taxPayerNumber;
    }

    /**
     * Gets the identification document type of the collaborator.
     * @return The identification document type of the collaborator.
     */
    public DocumentType getIdentificationDocumentType() {
        return identificationDocumentType;
    }

    /**
     * Gets the document identification number of the collaborator.
     * @return The document identification number of the collaborator.
     */
    public String getDocumentIdentificationNumber() {
        return documentIdentificationNumber;
    }

    /**
     * Gets the skills of the collaborator.
     * @return The skills of the collaborator.
     */
    public ArrayList<Skill> getSkills() {
        return skills;
    }

    /**
     * Sets the skills of the collaborator.
     * @param skills The new skills of the collaborator.
     */
    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    /**
     * Gets the job of the collaborator.
     * @return The job of the collaborator.
     */
    public Job getJob() {
        return job;
    }

    /**
     * Sets the job of the collaborator.
     * @param job The new job of the collaborator.
     */
    public void setJob(Job job) {
        this.job = job;
    }
}