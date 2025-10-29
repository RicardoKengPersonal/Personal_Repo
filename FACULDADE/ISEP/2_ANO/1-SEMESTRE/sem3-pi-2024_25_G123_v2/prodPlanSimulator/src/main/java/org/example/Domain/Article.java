package org.example.Domain;

import java.util.List;
import java.util.Objects;

/**
 * Represents an article in a production order system.
 * Each article has an ID, a priority level, and a list of operations.
 */
public class Article {
    private String idItem;
    private int priority;
    private List<String> operations;
    private int id;

    /**
     * Constructs an Article with the specified parameters.
     *
     * @param id         the unique identifier for the article
     * @param idItem     the item identifier
     * @param priority   the priority level of the article
     * @param operations the list of operations associated with the article
     */
    public Article(int id, String idItem, int priority, List<String> operations) {
        this.id = id;
        this.idItem = idItem;
        this.priority = priority;
        this.operations = operations;
    }

    /**
     * Returns the ID of the article.
     *
     * @return the article's unique identifier
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the article.
     *
     * @param id the new identifier for the article
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the item identifier of the article.
     *
     * @return the item's identifier
     */
    public String getIdItem() {
        return idItem;
    }

    /**
     * Sets the item identifier for the article.
     *
     * @param idItem the new item identifier
     */
    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }

    /**
     * Returns the priority level of the article.
     *
     * @return the priority level
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Sets the priority level of the article.
     *
     * @param priority the new priority level
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Returns the list of operations associated with the article.
     *
     * @return a list of operations
     */
    public List<String> getOperations() {
        return operations;
    }

    /**
     * Sets the list of operations for the article.
     *
     * @param operations the new list of operations
     */
    public void setOperations(List<String> operations) {
        this.operations = operations;
    }

    /**
     * Returns a string representation of the article, including ID, item identifier,
     * priority, and operations.
     *
     * @return a string representation of the article
     */
    @Override
    public String toString() {
        return "ProductionOrder{" +
                "Id=" + id +
                " idItem='" + idItem + '\'' +
                ", priority=" + priority +
                ", operations=" + operations +
                '}';
    }

    /**
     * Compares this article with the specified object for equality.
     * Returns true if both articles have the same item identifier, priority, and operations.
     *
     * @param o the object to compare
     * @return true if the articles are equal, otherwise false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article that = (Article) o;
        return priority == that.priority && Objects.equals(idItem, that.idItem) && Objects.equals(operations, that.operations);
    }

    /**
     * Returns a hash code for this article.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(idItem, priority, operations);
    }
}
