package eapli.shodrone.dronemanagement.application;

import eapli.framework.application.UseCaseController;
import eapli.shodrone.dronemanagement.domain.*;
import eapli.shodrone.dronemanagement.repository.DroneModelRepository;
import eapli.shodrone.dronemanagement.repository.DroneRepository;
import eapli.shodrone.dronemanagement.repository.MaintenanceTypeRepository;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Optional;
import java.util.Set;

@UseCaseController
public class DroneInventoryService {

    private final DroneModelRepository categoryRepository;
    private final DroneRepository droneRepository;
    private final MaintenanceTypeRepository maintenanceTypeRepository;

    public DroneInventoryService(final DroneModelRepository categoryRepository, final DroneRepository droneRepository,final MaintenanceTypeRepository maintenanceTypeRepository) {
        this.categoryRepository = categoryRepository;
        this.droneRepository = droneRepository;
        this.maintenanceTypeRepository = maintenanceTypeRepository;
    }

    @Transactional
    public void registerDroneModel(
            final String name,
            final String manufacturer,
            final float xAxisTolerance,
            final float yAxisTolerance,
            final float zAxisTolerance,
            final Calendar ofDay,
            final float maxSpeed,
            final float maxRotationSpeed,
            final Set<DroneType> lightingOptions
    ) {
        final Optional<DroneModel> existing = categoryRepository.findByNameIgnoreCase(name);
        if (existing.isPresent()) {
            throw new IllegalArgumentException("A drone model with this name already exists.");
        }

        final DroneModel newModel = new DroneModel(
                name,
                manufacturer,
                xAxisTolerance,
                yAxisTolerance,
                zAxisTolerance,
                ofDay,
                maxSpeed,
                maxRotationSpeed,
                lightingOptions
        );

        categoryRepository.save(newModel);
    }

    @Transactional
    public void addToInventoryDrone(DroneID id,String programmingLanguage, String name, DroneModel model, LocalDate acquisitionDate, DroneStatus status) {
        final Optional<Drone> existing = droneRepository.findByDroneNameIgnoreCase(name);
        if (existing.isPresent()) {
            throw new IllegalArgumentException("A Drone with this Serial Number already exists.");
        }

        Drone drone = new Drone(id,programmingLanguage,name, model, acquisitionDate);

        // Set the status
        switch (status) {
            case ACTIVE -> drone.markAsActive();
            case IN_REPAIR -> drone.markAsInRepair();
            case DECOMMISSIONED -> drone.markAsDecommissioned();
        }

        droneRepository.save(drone);
    }

    @Transactional
    public void removeFromInventory(String droneName, String reason, LocalDate date, DroneStatus newStatus) {
        final Optional<Drone> existing = droneRepository.findByDroneNameIgnoreCase(droneName);

        if (existing.isEmpty()) {
            throw new IllegalArgumentException("Drone not found.");
        }

        Drone drone = existing.get();
        drone.removeFromInventory(reason, date,newStatus);
        droneRepository.save(drone);
    }

    public Optional<DroneModel> findDroneModelByName(final String name) {
        return categoryRepository.findByNameIgnoreCase(name);
    }

    public Optional<Drone> findDroneByName(final String name) {
        return droneRepository.findByDroneNameIgnoreCase(name);
    }

    public Iterable<Drone> findByModelAndStatus(DroneModel model, DroneStatus status) {
        return droneRepository.findByModelAndStatus(model,status);
    }

    @Transactional
    public boolean isDroneModelRepositoryEmpty() {
        return !categoryRepository.findAll().iterator().hasNext();
    }

    public Iterable<Drone> findAllDrones() {
        return droneRepository.findAll();
    }

    public Iterable<DroneModel> findAllDronesModel() {
        return categoryRepository.findAll();
    }

    public boolean isDroneRepositoryEmpty() {
        return !droneRepository.findAll().iterator().hasNext();
    }

    @Transactional
    public void addMaintenanceType(final String name, final String description) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Maintenance type name cannot be null or empty.");
        }

        final Optional<MaintenanceType> existing = maintenanceTypeRepository.findByNameIgnoreCase(name);
        if (existing.isPresent()) {
            throw new IllegalArgumentException("A maintenance type with this name already exists.");
        }

        final MaintenanceType newType = new MaintenanceType(name.trim(), description != null ? description.trim() : "");
        maintenanceTypeRepository.save(newType);
    }

    public Iterable<MaintenanceType> findAllMaintenanceTypes()
    {
        return maintenanceTypeRepository.findAllTypes();
    }

    @Transactional
    public void editMaintenanceType(Long id, String newName, String newDescription) {
        MaintenanceType maintenanceType = maintenanceTypeRepository.ofIdentity(id)
                .orElseThrow(() -> new IllegalArgumentException("Maintenance Type not found."));

        boolean isInUse = droneRepository.existsMaintenanceRecordsByType(maintenanceType).isPresent();
        if (isInUse) {
            throw new IllegalStateException("Cannot edit MaintenanceType because it is already in use.");
        }
        maintenanceType.update(newName, newDescription, isInUse);
        maintenanceTypeRepository.save(maintenanceType);
    }

    public Optional<MaintenanceType> findMaintenanceTypeById(Long id) {
        return maintenanceTypeRepository.ofIdentity(id);
    }

    public boolean existsMaintenanceRecordsByType(MaintenanceType maintenanceType) {
        return droneRepository.existsMaintenanceRecordsByType(maintenanceType).isPresent();
    }

    @Transactional
    public void registerMaintenanceRecord(Drone drone, MaintenanceType type, LocalDate date) {
        DroneMaintenance record = new DroneMaintenance(drone, type, date);
        drone.addMaintenanceRecord(record);
        droneRepository.save(drone);  // Will cascade and persist DroneMaintenance
    }

    @Transactional
    public void setDroneProgrammingLanguage(Drone drone, String language) {
        drone.update(language);
        droneRepository.save(drone);
    }

    @Transactional
    public boolean validateDroneProgram(String filePath, String languageType) {
        switch (languageType.toLowerCase()) {
            case "one":
                return drones.Drone.droneOnePlugin(filePath);
            case "two":
                return drones.Drone.droneTwoPlugin(filePath);
            default:
                throw new IllegalArgumentException("Unknown drone language: " + languageType);
        }
    }


}
