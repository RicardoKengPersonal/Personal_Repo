package eapli.shodrone.figuremanagement.repository;

import eapli.framework.domain.repositories.DomainRepository;
import eapli.shodrone.figuremanagement.domain.*;

import java.util.List;
import java.util.Optional;

public interface FigureRepository extends DomainRepository<FigureID, Figure> {

    List<Figure> findByVisibility(FigureVisibility visibility);

    Iterable<Figure> findByKeywordsAndPublic(List<String> keywords, FigureVisibility visibility);

    Iterable<Figure> findByCategoryAndPublic(FigureCategoryID categoryID, FigureVisibility visibility);

    Iterable<Figure> findByCategoryAndKeywordsAndPublic(FigureCategoryID categoryID, List<String> keywords, FigureVisibility visibility);

    Optional<Figure> findByKeyword(String keyword);

    Optional<Figure> findByName(String name);

    List<Figure> findAllFigures();

    List<Figure> findByCategory(FigureCategoryID categoryID);

    Integer findMaxId();

    Iterable<FigureVersion> findAllFigureVersions();

    Optional<FigureVersion> ofIdentity(FigureVersionID id);

    void deleteFigureVersion(FigureVersionID id);
}
