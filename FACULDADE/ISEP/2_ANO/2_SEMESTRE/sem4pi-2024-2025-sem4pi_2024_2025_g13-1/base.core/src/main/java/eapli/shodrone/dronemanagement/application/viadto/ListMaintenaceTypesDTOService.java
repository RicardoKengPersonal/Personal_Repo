package eapli.shodrone.dronemanagement.application.viadto;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.shodrone.dronemanagement.domain.MaintenanceType;
import eapli.shodrone.dronemanagement.dto.MaintenanceTypeDTO;
import eapli.shodrone.dronemanagement.repository.MaintenanceTypeRepository;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;
import eapli.shodrone.usermanagement.domain.Roles;

import java.util.ArrayList;
import java.util.List;

public class ListMaintenaceTypesDTOService {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final MaintenanceTypeRepository maintenanceTypeRepository = PersistenceContext.repositories().maintenanceType();

    public Iterable<MaintenanceTypeDTO> listAllMaintenanceTypes() {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.DRONE_TECH, Roles.ADMIN);

        final Iterable<MaintenanceType> types = this.maintenanceTypeRepository.findAll();

        final List<MaintenanceTypeDTO> ret = new ArrayList<>();
        types.forEach(e -> ret.add(e.toDTO()));
        return ret;
    }

}
