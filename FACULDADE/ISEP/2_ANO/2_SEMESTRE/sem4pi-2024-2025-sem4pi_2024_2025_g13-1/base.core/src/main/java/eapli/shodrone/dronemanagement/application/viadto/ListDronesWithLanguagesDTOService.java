package eapli.shodrone.dronemanagement.application.viadto;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.shodrone.dronemanagement.domain.Drone;
import eapli.shodrone.dronemanagement.dto.DroneLanguagesDTO;
import eapli.shodrone.dronemanagement.repository.DroneRepository;

import eapli.shodrone.infrastructure.persistence.PersistenceContext;
import eapli.shodrone.usermanagement.domain.Roles;

import java.util.ArrayList;
import java.util.List;

public class ListDronesWithLanguagesDTOService {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final DroneRepository droneRepository = PersistenceContext.repositories().drone();

    public Iterable<DroneLanguagesDTO> listAllDronesWithLanguages() {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.DRONE_TECH, Roles.ADMIN);

        final Iterable<Drone> drones = this.droneRepository.findAllWithProgrammingLanguage();

        final List<DroneLanguagesDTO> ret = new ArrayList<>();
        drones.forEach(e -> ret.add(e.toDTO()));
        return ret;
    }

}
