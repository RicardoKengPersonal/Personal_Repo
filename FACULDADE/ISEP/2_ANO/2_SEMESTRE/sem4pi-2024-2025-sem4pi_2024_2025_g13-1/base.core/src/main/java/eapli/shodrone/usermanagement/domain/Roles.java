package eapli.shodrone.usermanagement.domain;

import eapli.framework.infrastructure.authz.domain.model.Role;

/**
 * Application-wide definitions of user roles.
 */
public final class Roles {

    public static final Role ADMIN = Role.valueOf("ADMIN");
    public static final Role CRM_MANAGER = Role.valueOf("CRM_MANAGER");
    public static final Role CRM_COLLABORATOR = Role.valueOf("CRM_COLLABORATOR");
    public static final Role SHOW_DESIGNER = Role.valueOf("SHOW_DESIGNER");
    public static final Role DRONE_TECH = Role.valueOf("DRONE_TECH");
    public static final Role CUSTOMER_REPRESENTATIVE = Role.valueOf("CUSTOMER_REPRESENTATIVE");

    private Roles() {
        // utility class
    }

    public static Role[] nonUserValues() { return new Role[] {ADMIN,CRM_MANAGER,CRM_COLLABORATOR,SHOW_DESIGNER,DRONE_TECH,CUSTOMER_REPRESENTATIVE }; }
}
