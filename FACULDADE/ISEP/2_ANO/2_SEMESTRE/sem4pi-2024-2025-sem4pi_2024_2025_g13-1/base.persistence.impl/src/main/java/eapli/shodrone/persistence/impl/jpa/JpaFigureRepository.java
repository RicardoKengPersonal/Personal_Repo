package eapli.shodrone.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import eapli.shodrone.Application;
import eapli.shodrone.figuremanagement.domain.*;
import eapli.shodrone.figuremanagement.repository.FigureRepository;

import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class JpaFigureRepository
        extends JpaAutoTxRepository<Figure, FigureID, String>
        implements FigureRepository {

    public JpaFigureRepository(final String puname) {
        super(puname, Application.settings().extendedPersistenceProperties(), "id");
    }

    public JpaFigureRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    @Override
    public List<Figure> findByVisibility(FigureVisibility visibility) {
        final TypedQuery<Figure> query = createQuery(
                "SELECT f FROM Figure f WHERE f.visibility = :visibility", Figure.class);
        query.setParameter("visibility", visibility);
        return query.getResultList();
    }

    @Override
    public Iterable<Figure> findByKeywordsAndPublic(List<String> keywords, FigureVisibility visibility) {
        final TypedQuery<Figure> query = createQuery(
                "SELECT f FROM Figure f WHERE f.keyword IN :keywords AND f.visibility = :visibility", Figure.class);
        query.setParameter("keywords", keywords);
        query.setParameter("visibility", visibility);
        return query.getResultList();
    }


    @Override
    public Iterable<Figure> findByCategoryAndPublic(FigureCategoryID categoryID, FigureVisibility visibility) {
        final TypedQuery<Figure> query = createQuery(
                "SELECT f FROM Figure f WHERE f.figureCategoryID = :categoryID AND f.visibility = :visibility", Figure.class);
        query.setParameter("categoryID", categoryID);
        query.setParameter("visibility", visibility);
        return query.getResultList();
    }

    @Override
    public Iterable<Figure> findByCategoryAndKeywordsAndPublic(FigureCategoryID categoryID, List<String> keywords, FigureVisibility visibility) {
        final TypedQuery<Figure> query = createQuery(
                "SELECT f FROM Figure f WHERE f.figureCategoryID = :categoryID AND f.keyword IN :keywords AND f.visibility = :visibility", Figure.class);
        query.setParameter("categoryID", categoryID);
        query.setParameter("keywords", keywords);
        query.setParameter("visibility", visibility);
        return query.getResultList();
    }

    @Override
    public Optional<Figure> findByKeyword(String keyword) {
        final TypedQuery<Figure> query = createQuery(
                "SELECT f FROM Figure f WHERE f.keyword = :keyword", Figure.class);
        query.setParameter("keyword", keyword);
        return query.getResultStream().findFirst();
    }

    @Override
    public Optional<Figure> findByName(String name) {
        final TypedQuery<Figure> query = createQuery(
                "SELECT f FROM Figure f WHERE f.figureName = :name", Figure.class);
        query.setParameter("name", name);
        return query.getResultStream().findFirst();
    }

    @Override
    public List<Figure> findAllFigures() {
        final TypedQuery<Figure> query = createQuery("SELECT f FROM Figure f", Figure.class);
        return query.getResultList();
    }

    @Override
    public List<Figure> findByCategory(FigureCategoryID categoryID) {
        final TypedQuery<Figure> query = createQuery(
                "SELECT f FROM Figure f WHERE f.figureCategoryID = :categoryID", Figure.class);
        query.setParameter("categoryID", categoryID);
        return query.getResultList();
    }

    @Override
    public Integer findMaxId() {
        final TypedQuery<Integer> query = createQuery(
                "SELECT MAX(f.id.id) FROM Figure f", Integer.class
        );
        return query.getSingleResult();
    }

    @Override
    public Iterable<FigureVersion> findAllFigureVersions() {
        final TypedQuery<FigureVersion> query = createQuery(
                "SELECT fv FROM FigureVersion fv", FigureVersion.class);
        return query.getResultList();
    }

    @Override
    public void deleteFigureVersion(FigureVersionID id) {
        List<Figure> allFigures = findAllFigures();
        for (Figure fig : allFigures) {
            boolean removed = fig.removeVersionById(id);
            if (removed) {
                save(fig);
                return;
            }
        }
        throw new IllegalArgumentException("FigureVersion with ID " + id + " not found.");
    }


    @Override
    public Optional<Figure> ofIdentity(FigureID id) {
        return Optional.empty();
    }

    @Override
    public Optional<FigureVersion> ofIdentity(FigureVersionID id) {
        final TypedQuery<FigureVersion> query = createQuery(
                "SELECT fv FROM FigureVersion fv WHERE fv.id = :id", FigureVersion.class);
        query.setParameter("id", id);
        return query.getResultStream().findFirst();
    }


    @Override
    public void deleteOfIdentity(FigureID entityId) {

    }
}
