package eapli.shodrone.persistence.impl.inmemory;

import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import eapli.shodrone.figuremanagement.domain.*;
import eapli.shodrone.figuremanagement.repository.FigureRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class InMemoryFigureRepository extends InMemoryDomainRepository<Figure, FigureID> implements FigureRepository {

    @Override
    public List<Figure> findByVisibility(FigureVisibility visibility) {
        return (List<Figure>) match(f -> f.visibility().equals(visibility));
    }

    @Override
    public Iterable<Figure> findByKeywordsAndPublic(List<String> keywords, FigureVisibility visibility) {
        return match(f ->
                f.visibility().equals(visibility) &&
                        keywords.stream().anyMatch(k -> f.keyword().toLowerCase().contains(k.toLowerCase()))
        );
    }

    @Override
    public Iterable<Figure> findByCategoryAndPublic(FigureCategoryID categoryID, FigureVisibility visibility) {
        return match(f -> f.figureCategoryID().equals(categoryID) && f.visibility().equals(visibility));
    }

    @Override
    public Iterable<Figure> findByCategoryAndKeywordsAndPublic(FigureCategoryID categoryID, List<String> keywords, FigureVisibility visibility) {
        return match(f ->
                f.figureCategoryID().equals(categoryID) &&
                        f.visibility().equals(visibility) &&
                        keywords.stream().anyMatch(k -> f.keyword().toLowerCase().contains(k.toLowerCase()))
        );
    }

    @Override
    public Optional<Figure> findByKeyword(String keyword) {
        return matchOne(f -> f.keyword().equalsIgnoreCase(keyword));
    }

    @Override
    public Optional<Figure> findByName(String name) {
        return matchOne(f -> f.figureName().equalsIgnoreCase(name));
    }

    @Override
    public List<Figure> findAllFigures() {
        return (List<Figure>) match(f -> true);
    }

    @Override
    public List<Figure> findByCategory(FigureCategoryID categoryID) {
        return (List<Figure>) match(f -> f.figureCategoryID().equals(categoryID));
    }

    @Override
    public Integer findMaxId() {
        return StreamSupport.stream(findAll().spliterator(), false)
                .map(c -> c.identity().toInteger())
                .max(Integer::compareTo)
                .orElse(0);
    }

    @Override
    public Iterable<FigureVersion> findAllFigureVersions() {
        return StreamSupport.stream(findAll().spliterator(), false)
                .flatMap(f -> f.versions().stream())
                .collect(Collectors.toList());
    }

    @Override
    public void deleteFigureVersion(FigureVersionID id) {
        final Optional<Figure> figureWithVersion = StreamSupport.stream(findAll().spliterator(), false)
                .filter(f -> f.versions().stream().anyMatch(v -> v.getId().equals(id)))
                .findFirst();

        figureWithVersion.ifPresent(f -> {
            List<FigureVersion> updatedVersions = f.versions().stream()
                    .filter(v -> !v.getId().equals(id))
                    .collect(Collectors.toList());
            f.setVersions(updatedVersions);
        });
    }

    @Override
    public Optional<FigureVersion> ofIdentity(FigureVersionID id) {
        return StreamSupport.stream(findAll().spliterator(), false)
                .flatMap(f -> f.versions().stream())
                .filter(v -> v.id().equals(id))
                .findFirst();
    }
}
