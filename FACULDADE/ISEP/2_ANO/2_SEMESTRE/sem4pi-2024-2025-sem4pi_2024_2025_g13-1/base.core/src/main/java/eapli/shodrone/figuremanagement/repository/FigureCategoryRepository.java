package eapli.shodrone.figuremanagement.repository;

import eapli.framework.domain.repositories.DomainRepository;
import eapli.shodrone.figuremanagement.domain.FigureCategory;
import eapli.shodrone.figuremanagement.domain.FigureCategoryID;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FigureCategoryRepository extends DomainRepository<FigureCategoryID, FigureCategory> {

    Optional<FigureCategory> findByNameIgnoreCase(String name);

    Integer findMaxId();

    Iterable<FigureCategory> findByActive();
}
