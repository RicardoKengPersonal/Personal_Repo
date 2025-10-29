package eapli.shodrone.persistence.impl.jpa;

import eapli.framework.infrastructure.repositories.impl.jpa.JpaTransactionalContext;
import eapli.shodrone.Application;

public class ShodroneJpaReportingRepositoryBase extends JpaTransactionalContext {

    ShodroneJpaReportingRepositoryBase() {
        super(Application.settings().persistenceUnitName(),
                Application.settings().extendedPersistenceProperties());
    }

    ShodroneJpaReportingRepositoryBase(final String persistenceUnitName) {
        super(persistenceUnitName, Application.settings().extendedPersistenceProperties());
    }
}
