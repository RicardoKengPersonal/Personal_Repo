package eapli.shodrone.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import eapli.shodrone.Application;

import java.util.Map;

public abstract class ShodroneJpaRepositoryBase<T, K, I>
        extends JpaAutoTxRepository<T, K, I> {

    protected ShodroneJpaRepositoryBase(final String persistenceUnitName, final String identityField) {
        super(persistenceUnitName, Application.settings().extendedPersistenceProperties(), identityField);
    }

    protected ShodroneJpaRepositoryBase(final String identityField) {
        super(Application.settings().persistenceUnitName(),
                Application.settings().extendedPersistenceProperties(), identityField);
    }
}
