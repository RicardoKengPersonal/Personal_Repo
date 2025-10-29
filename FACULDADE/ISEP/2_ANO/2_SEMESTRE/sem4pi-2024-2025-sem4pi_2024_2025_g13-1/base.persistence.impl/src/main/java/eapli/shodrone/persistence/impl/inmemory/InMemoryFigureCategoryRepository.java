package eapli.shodrone.persistence.impl.inmemory;

import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import eapli.shodrone.figuremanagement.domain.FigureCategory;
import eapli.shodrone.figuremanagement.domain.FigureCategoryID;
import eapli.shodrone.figuremanagement.repository.FigureCategoryRepository;

import java.util.Optional;
import java.util.stream.StreamSupport;

public class InMemoryFigureCategoryRepository
        extends InMemoryDomainRepository<FigureCategory, FigureCategoryID>
        implements FigureCategoryRepository {

    @Override
    public Optional<FigureCategory> findByNameIgnoreCase(String name) {
        return matchOne(c -> c.name().equalsIgnoreCase(name));
    }

    @Override
    public Iterable<FigureCategory> findByActive() {
        return match(c -> c.isActive());
    }

    @Override
    public Integer findMaxId() {
        return StreamSupport.stream(findAll().spliterator(), false)
                .map(c -> c.identity().toInteger())
                .max(Integer::compareTo)
                .orElse(0);
    }
}
