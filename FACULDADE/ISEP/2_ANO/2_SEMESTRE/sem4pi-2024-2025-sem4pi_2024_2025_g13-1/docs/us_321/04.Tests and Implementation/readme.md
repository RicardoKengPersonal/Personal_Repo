# US 321 - Add maintenance type

## 4. Tests

**Test 1:** Ensure name and description cannot be null or empty

    @Test
        void nameCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> new MaintenanceType(null, "Valid description"));
    }
    
    @Test
        void descriptionCannotBeEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new MaintenanceType("Inspeção", "  "));
    }

**Test 2:** Ensure name accepts accents and cedilla, but not symbols

    @Test
        void nameCanHaveAccentsAndCedilla() {
        assertDoesNotThrow(() -> new MaintenanceType("Lubrificação", "Descrição válida"));
    }
    
    @Test
        void nameCannotHaveSpecialCharacters() {
        assertThrows(IllegalArgumentException.class, () -> new MaintenanceType("Lubr@cação", "Descrição válida"));
    }

**Test 4:** Ensure editing is blocked if the type is in use

    @Test
        void updateThrowsIfInUse() {
        MaintenanceType type = new MaintenanceType("Calibração", "Calibração de sensores");
        assertThrows(IllegalStateException.class, () -> type.update("Nova", "Nova desc", true));
    }

## Construction (Implementation)

Class: MaintenanceType

        public MaintenanceType(final String name, final String description) {
            if (name == null || name.trim().isEmpty() || !name.matches("[\\p{L}\\p{Zs}]+")) {
            throw new IllegalArgumentException("Invalid name.");
        }
            if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid description.");
        }
            this.name = name.trim();
            this.description = description.trim();
            this.active = true;
        }

Class: DroneInventoryService

        @Transactional
        public void addMaintenanceType(final String name, final String description) {
        final Optional<MaintenanceType> existing = maintenanceTypeRepository.findByNameIgnoreCase(name);
        if (existing.isPresent()) {
        throw new IllegalArgumentException("Maintenance type already exists.");
        }
        
            final MaintenanceType newType = new MaintenanceType(name, description);
            maintenanceTypeRepository.save(newType);
        }

Class: MaintenanceTypeCreationController

        public class MaintenanceTypeCreationController {
        private final AuthorizationService authz;
        private final DroneInventoryService service;
        
            public MaintenanceTypeCreationController(AuthorizationService authz, final DroneModelRepository modelRepo,
                                                     final DroneRepository droneRepo,
                                                     final MaintenanceTypeRepository maintenanceTypeRepository) {
                this.authz = authz;
                this.service = new DroneInventoryService(modelRepo, droneRepo, maintenanceTypeRepository);
            }
        
            public void addMaintenanceType(final String name, final String description) {
                authz.ensureAuthenticatedUserHasAnyOf(Roles.DRONE_TECH, Roles.ADMIN);
                service.addMaintenanceType(name, description);
            }
        }


## Integration and Demo
A new option was added to the Drone Technician/Admin menu: "Add Maintenance Type".

When selecting this option, the UI prompts the user for:

Name

Description

If input is valid and unique, the new maintenance type is saved and displayed.

Attempts to reuse the same name result in a validation error.

## 7. Observations
n/a. 


