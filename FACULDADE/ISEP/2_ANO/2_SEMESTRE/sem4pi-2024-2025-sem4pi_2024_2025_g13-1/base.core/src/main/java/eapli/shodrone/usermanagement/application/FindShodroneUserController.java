package eapli.shodrone.usermanagement.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;
import eapli.shodrone.shodroneusermanagement.domain.ShodroneUser;
import eapli.shodrone.shodroneusermanagement.repositories.ShodroneUserRepository;
import eapli.shodrone.usermanagement.domain.Roles;

import java.util.Optional;

@UseCaseController
public class FindShodroneUserController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ShodroneUserRepository repo = PersistenceContext.repositories().shodroneUsers();


    public Optional<ShodroneUser> findBySystemUser(final SystemUser user) {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.ADMIN);
        return repo.findByUsername(user.username());
    }
}
