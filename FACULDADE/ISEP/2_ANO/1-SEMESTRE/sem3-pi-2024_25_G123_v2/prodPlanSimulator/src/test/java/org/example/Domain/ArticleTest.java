package org.example.Domain;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Article class.
 */
class ArticleTest {

    @Test
    void testArticleConstructorAndGetters() {
        List<String> operations = Arrays.asList("Operation1", "Operation2");
        Article article = new Article(1, "A1", 1, operations);

        assertEquals(1, article.getId());
        assertEquals("A1", article.getIdItem());
        assertEquals(1, article.getPriority());
        assertEquals(operations, article.getOperations());
    }

    @Test
    void testSetters() {
        List<String> operations = Arrays.asList("Operation1", "Operation2");
        Article article = new Article(1, "A1", 1, operations);

        article.setId(2);
        article.setIdItem("A2");
        article.setPriority(2);
        article.setOperations(Arrays.asList("Operation3"));

        assertEquals(2, article.getId());
        assertEquals("A2", article.getIdItem());
        assertEquals(2, article.getPriority());
        assertEquals(Arrays.asList("Operation3"), article.getOperations());
    }

    @Test
    void testEqualsAndHashCode() {
        List<String> operations1 = Arrays.asList("Operation1", "Operation2");
        List<String> operations2 = Arrays.asList("Operation1", "Operation2");
        Article article1 = new Article(1, "A1", 1, operations1);
        Article article2 = new Article(2, "A1", 1, operations2);
        Article article3 = new Article(3, "A2", 2, operations1);

        // Test equality
        assertEquals(article1, article2);
        assertNotEquals(article1, article3);

        // Test hash codes
        assertEquals(article1.hashCode(), article2.hashCode());
        assertNotEquals(article1.hashCode(), article3.hashCode());
    }

    @Test
    void testToString() {
        List<String> operations = Arrays.asList("Operation1", "Operation2");
        Article article = new Article(1, "A1", 1, operations);

        String expectedString = "ProductionOrder{" +
                "Id=1" +
                " idItem='A1'" +
                ", priority=1" +
                ", operations=" + operations +
                '}';
        assertEquals(expectedString, article.toString());
    }
}
