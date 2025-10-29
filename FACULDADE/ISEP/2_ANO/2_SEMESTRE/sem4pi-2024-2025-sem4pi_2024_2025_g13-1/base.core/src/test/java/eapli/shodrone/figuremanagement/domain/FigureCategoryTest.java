package eapli.shodrone.figuremanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FigureCategoryTest {

    @Test
    void testValidConstruction() {
        // Verifica se a construção de um objeto `FigureCategory` com valores válidos funciona corretamente.
        FigureCategoryID id = FigureCategoryID.valueOf(1);
        FigureCategory category = new FigureCategory(id, "Aerobatics");

        assertEquals(id, category.identity());
        assertEquals("Aerobatics", category.name());
        assertTrue(category.isActive());
    }

    @Test
    void testInvalidConstruction_NullID() {
        // Garante que a construção de um `FigureCategory` com um ID nulo lança uma exceção.
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                new FigureCategory(null, "ValidName"));
        assertEquals("ID must not be null.", ex.getMessage());
    }

    @Test
    void testInvalidConstruction_EmptyName() {
        // Garante que a construção de um `FigureCategory` com um nome vazio lança uma exceção.
        FigureCategoryID id = FigureCategoryID.valueOf(2);
        assertThrows(IllegalArgumentException.class, () ->
                new FigureCategory(id, ""));
    }

    @Test
    void testDeactivateAndActivate() {
        // Testa os métodos de ativação e desativação de uma categoria.
        FigureCategory category = new FigureCategory(FigureCategoryID.valueOf(3), "Demo");
        category.deactivate();
        assertFalse(category.isActive());

        category.activate();
        assertTrue(category.isActive());
    }

    @Test
    void testRename_ValidName() {
        // Verifica se o método `rename` atualiza corretamente o nome da categoria com um nome válido.
        FigureCategory category = new FigureCategory(FigureCategoryID.valueOf(4), "Original");
        category.rename("Updated Name");
        assertEquals("Updated Name", category.name());
    }

    @Test
    void testRename_InvalidName() {
        // Garante que o método `rename` lança uma exceção ao tentar definir um nome inválido (vazio ou apenas espaços).
        FigureCategory category = new FigureCategory(FigureCategoryID.valueOf(5), "Name");
        assertThrows(IllegalArgumentException.class, () -> category.rename("  "));
    }

    @Test
    void testSameAsEqualsAndHashCode() {
        // Testa os métodos `sameAs`, `equals` e `hashCode` para verificar a igualdade lógica entre objetos.
        FigureCategoryID id = FigureCategoryID.valueOf(6);
        FigureCategory cat1 = new FigureCategory(id, "Category");
        FigureCategory cat2 = new FigureCategory(FigureCategoryID.valueOf(7), "category"); // same name, different ID

        assertTrue(cat1.sameAs(cat2));
        assertEquals(cat1, cat2);
        assertEquals(cat1.hashCode(), cat2.hashCode());
    }

    @Test
    void testToStringContainsAllFields() {
        // Verifica se o método `toString` inclui todos os campos relevantes da classe.
        FigureCategory category = new FigureCategory(FigureCategoryID.valueOf(8), "Acrobatic");
        String toString = category.toString();

        assertTrue(toString.contains("id="));
        assertTrue(toString.contains("name='Acrobatic'"));
        assertTrue(toString.contains("active=true"));
    }
}
