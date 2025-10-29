package org.example.Utils;

/**
 * A utility class to hold temporary data related to an operation type and its associated time.
 */
public class Temp {
    // Total time associated with the operation
    public int totalTime;

    // Type of the article or operation
    public String type;

    // Specific operation being represented
    public String operation;

    /**
     * Constructs a Temp instance with specified type and operation.
     *
     * @param type      the type of the article or operation
     * @param operation the specific operation
     */
    public Temp(String type, String operation) {
        this.type = type;
        this.operation = operation;
        this.totalTime = 0; // Initializes total time to zero
    }

    /**
     * Default constructor that initializes type and operation to null.
     */
    public Temp() {
        this.type = null;
        this.operation = null;
    }

    /**
     * Copy constructor that creates a new Temp instance based on another Temp instance.
     *
     * @param temp the Temp instance to copy from
     */
    public Temp(Temp temp) {
        this.type = temp.type;
        this.operation = temp.operation;
        this.totalTime = temp.totalTime;
    }
}
