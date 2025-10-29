package eapli.shodrone.usermanagement.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.shodrone.usermanagement.domain.Roles;

@UseCaseController
public class ReactivateUserController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final UserManagementService userSvc = AuthzRegistry.userService();

    public Iterable<SystemUser> inactiveUsers() {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.ADMIN);
        return userSvc.deactivatedUsers();
    }

    public void activateUser(final SystemUser user) {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.ADMIN);
        userSvc.activateUser(user);
    }
}
