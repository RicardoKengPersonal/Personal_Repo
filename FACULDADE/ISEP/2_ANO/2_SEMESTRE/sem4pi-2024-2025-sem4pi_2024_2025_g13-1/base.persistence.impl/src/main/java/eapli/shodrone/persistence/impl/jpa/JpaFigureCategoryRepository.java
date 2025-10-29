package eapli.shodrone.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import eapli.shodrone.Application;
import eapli.shodrone.figuremanagement.domain.FigureCategory;
import eapli.shodrone.figuremanagement.domain.FigureCategoryID;
import eapli.shodrone.figuremanagement.repository.FigureCategoryRepository;
import jakarta.persistence.TypedQuery;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class JpaFigureCategoryRepository
        extends JpaAutoTxRepository<FigureCategory, FigureCategoryID, String>
        implements FigureCategoryRepository {

    public JpaFigureCategoryRepository(final String puname) {
        super(puname, Application.settings().extendedPersistenceProperties(), "id");
    }

    public JpaFigureCategoryRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    @Override
    public Optional<FigureCategory> findByNameIgnoreCase(String name) {
        final TypedQuery<FigureCategory> query = createQuery(
                "SELECT fc FROM FigureCategory fc WHERE LOWER(fc.name) = LOWER(:name)",
                FigureCategory.class);
        query.setParameter("name", name);
        return query.getResultStream().findFirst();
    }

    @Override
    public Integer findMaxId() {
        final TypedQuery<Integer> query = createQuery(
                "SELECT MAX(fc.id.id) FROM FigureCategory fc", Integer.class
        );
        return query.getSingleResult();
    }

    @Override
    public Iterable<FigureCategory> findByActive() {
        final TypedQuery<FigureCategory> query = createQuery(
                "SELECT fc FROM FigureCategory fc WHERE fc.active = true",
                FigureCategory.class
        );
        return query.getResultList();
    }

    @Override
    public Optional<FigureCategory> ofIdentity(FigureCategoryID id) {
        return Optional.empty();
    }

    @Override
    public void deleteOfIdentity(FigureCategoryID entityId) {

    }


}