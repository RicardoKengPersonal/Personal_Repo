package eapli.shodrone.dronemanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaintenanceTypeTest {

    @Test
    void createValidMaintenanceType() {
        MaintenanceType type = new MaintenanceType("Lubrificação", "Lubrificação das hélices");
        assertEquals("Lubrificação", type.name());
        assertEquals("Lubrificação das hélices", type.description());
        assertTrue(type.isActive());
    }

    @Test
    void nameCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> new MaintenanceType(null, "Valid description"));
    }

    @Test
    void nameCannotBeEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new MaintenanceType("   ", "Valid description"));
    }

    @Test
    void descriptionCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> new MaintenanceType("Inspeção", null));
    }

    @Test
    void descriptionCannotBeEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new MaintenanceType("Inspeção", "  "));
    }

    @Test
    void nameCanHaveAccentsAndCedilla() {
        assertDoesNotThrow(() -> new MaintenanceType("Manutenção com ç e á é í ó ú", "Descrição válida"));
    }

    @Test
    void nameCannotHaveOtherSpecialCharacters() {
        String[] invalidNames = {"Manut@ção", "Inspeção!", "Limpeza#", "Test$%^&*"};
        for (String name : invalidNames) {
            assertThrows(IllegalArgumentException.class, () -> {
                if (!name.matches("[\\p{L}\\p{Zs}]+")) {
                    throw new IllegalArgumentException("Invalid characters in name.");
                }
                new MaintenanceType(name, "Descrição válida");
            }, "Expected exception for: " + name);
        }
    }

    @Test
    void updateWhenNotInUse() {
        MaintenanceType type = new MaintenanceType("Limpeza", "Limpeza básica");
        type.update("Limpeza Avançada", "Limpeza completa das peças", false);
        assertEquals("Limpeza Avançada", type.name());
        assertEquals("Limpeza completa das peças", type.description());
    }

    @Test
    void updateThrowsIfInUse() {
        MaintenanceType type = new MaintenanceType("Calibração", "Calibração de sensores");
        assertThrows(IllegalStateException.class, () -> type.update("Nova", "Nova desc", true));
    }

    @Test
    void updateThrowsIfNewNameIsInvalid() {
        MaintenanceType type = new MaintenanceType("Calibração", "Calibração de sensores");
        assertThrows(IllegalArgumentException.class, () -> type.update(" ", "Nova desc", false));
    }

    @Test
    void updateThrowsIfNewDescriptionIsInvalid() {
        MaintenanceType type = new MaintenanceType("Calibração", "Calibração de sensores");
        assertThrows(IllegalArgumentException.class, () -> type.update("Nova", "", false));
    }

    @Test
    void testEqualsAndHashCode() {
        MaintenanceType a = new MaintenanceType("Revisão", "Descrição A");
        MaintenanceType b = new MaintenanceType("revisão", "Descrição B");
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void testToString() {
        MaintenanceType type = new MaintenanceType("Teste", "Descrição teste");
        assertTrue(type.toString().contains("Teste"));
        assertTrue(type.toString().contains("Descrição"));
    }
}
