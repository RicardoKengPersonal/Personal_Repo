package org.example.Domain;

/**
 * Class to hold the schedule information for each operation in the simulation.
 * Each scheduled operation includes details about its production order, workstation,
 * timing, sequence in the article, and type.
 */
public class OperationScheduled {
    private int productionOrderId;
    private String operation;
    private String workstationId;
    private int startTime;
    private int endTime;
    private int operationNumber;
    private String type;

    /**
     * Constructs a scheduled operation with the specified parameters.
     *
     * @param productionOrderId the ID of the production order
     * @param operation         the name of the operation
     * @param workstationId     the ID of the workstation assigned to this operation
     * @param startTime         the start time of the operation
     * @param endTime           the end time of the operation
     * @param operationNumber   the sequence number of the operation within the article
     * @param type              the type of article associated with this operation
     */
    public OperationScheduled(int productionOrderId, String operation, String workstationId, int startTime, int endTime, int operationNumber, String type) {
        this.productionOrderId = productionOrderId;
        this.operation = operation;
        this.workstationId = workstationId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.operationNumber = operationNumber;
        this.type = type;
    }

    /**
     * Gets the production order ID associated with this scheduled operation.
     *
     * @return the production order ID
     */
    public int getProductionOrderId() {
        return productionOrderId;
    }

    /**
     * Gets the name of the operation.
     *
     * @return the operation name
     */
    public String getOperation() {
        return operation;
    }

    /**
     * Gets the ID of the workstation assigned to this operation.
     *
     * @return the workstation ID
     */
    public String getWorkstationId() {
        return workstationId;
    }

    /**
     * Gets the start time of the operation.
     *
     * @return the start time
     */
    public int getStartTime() {
        return startTime;
    }

    /**
     * Gets the end time of the operation.
     *
     * @return the end time
     */
    public int getEndTime() {
        return endTime;
    }

    /**
     * Gets the sequence number of the operation within the article.
     *
     * @return the operation number
     */
    public int getOperationNumber() {
        return operationNumber;
    }

    /**
     * Gets the type of article associated with this operation.
     *
     * @return the article type
     */
    public String getType() {
        return type;
    }
}
