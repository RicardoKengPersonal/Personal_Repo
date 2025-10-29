package Lapr3;

public class BillOfOperation {

    private int operationId;
    private int productId;
    private int operationType;
    private int inputId;
    private int outputId;
    private Integer nextOperationId;  // Use Integer to handle NULL values
    private int sequenceNumber;

    // Constructor
    public BillOfOperation(int operationId, int productId, int operationType,
                           int inputId, int outputId, Integer nextOperationId,
                           int sequenceNumber) {
        this.operationId = operationId;
        this.productId = productId;
        this.operationType = operationType;
        this.inputId = inputId;
        this.outputId = outputId;
        this.nextOperationId = nextOperationId;
        this.sequenceNumber = sequenceNumber;
    }

    // Getters and Setters
    public int getOperationId() {
        return operationId;
    }

    public void setOperationId(int operationId) {
        this.operationId = operationId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOperationType() {
        return operationType;
    }

    public void setOperationType(int operationType) {
        this.operationType = operationType;
    }

    public int getInputId() {
        return inputId;
    }

    public void setInputId(int inputId) {
        this.inputId = inputId;
    }

    public int getOutputId() {
        return outputId;
    }

    public void setOutputId(int outputId) {
        this.outputId = outputId;
    }

    public Integer getNextOperationId() {
        return nextOperationId;
    }

    public void setNextOperationId(Integer nextOperationId) {
        this.nextOperationId = nextOperationId;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    // ToString method for easier display
    @Override
    public String toString() {
        return "BillOfOperation{" +
                "operationId=" + operationId +
                ", productId=" + productId +
                ", operationType=" + operationType +
                ", inputId=" + inputId +
                ", outputId=" + outputId +
                ", nextOperationId=" + nextOperationId +
                ", sequenceNumber=" + sequenceNumber +
                '}';
    }
}
