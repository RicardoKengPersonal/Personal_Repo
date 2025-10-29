# US322 - List maintenance types

## 4. Tests

n/a.

## Construction (Implementation)

Class: MaintenanceType

        public MaintenanceType(final String name, final String description) {
            if (name == null || name.trim().isEmpty())
                throw new IllegalArgumentException("Name must not be null or empty.");
            if (description == null || description.trim().isEmpty())
                throw new IllegalArgumentException("Description must not be null or empty.");
    
            this.name = name.trim();
            this.description = description.trim();
            this.active = true;
        }

Class: DroneInventoryService

        public Iterable<MaintenanceType> findAllMaintenanceTypes()
        {
        return maintenanceTypeRepository.findAllTypes();
        }

Class: ListAllMaintenanceTypesController

            class ListAllMaintenanceTypesController {
        
            private final AuthorizationService authz;
            private final DroneInventoryService service;
        
            public ListAllMaintenanceTypesController(AuthorizationService authz, final DroneRepository droneRepo, final DroneModelRepository modelRepo, final MaintenanceTypeRepository maintenanceTypeRepository) {
                this.authz = authz;
                this.service = new DroneInventoryService(modelRepo,droneRepo,maintenanceTypeRepository);
            }
        
            public Iterable<MaintenanceType> findAllMaintenanceTypes() {
                authz.ensureAuthenticatedUserHasAnyOf(Roles.DRONE_TECH, Roles.ADMIN);
                return service.findAllMaintenanceTypes();
            }


## Integration and Demo

n/a.

## 7. Observations
n/a. 


