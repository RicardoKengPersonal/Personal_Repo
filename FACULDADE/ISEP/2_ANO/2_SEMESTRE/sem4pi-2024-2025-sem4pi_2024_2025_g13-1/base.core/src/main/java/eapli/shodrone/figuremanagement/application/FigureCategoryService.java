package eapli.shodrone.figuremanagement.application;

import eapli.framework.application.UseCaseController;
import eapli.shodrone.figuremanagement.domain.FigureCategoryID;
import eapli.shodrone.figuremanagement.repository.FigureRepository;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import eapli.shodrone.figuremanagement.domain.FigureCategory;
import eapli.shodrone.figuremanagement.repository.FigureCategoryRepository;
import java.util.Optional;
import java.util.stream.StreamSupport;

@UseCaseController
public class FigureCategoryService {

    private final FigureCategoryRepository categoryRepository;
    private final FigureRepository figureRepository; // falta isto


    public FigureCategoryService(final FigureCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        this.figureRepository = PersistenceContext.repositories().figure();
    }

    @Transactional
    public void registerCategory(final String name) {
        final Optional<FigureCategory> existing = categoryRepository.findByNameIgnoreCase(name);
        if (existing.isPresent()) {
            throw new IllegalArgumentException("A category with this name already exists.");
        }

        Integer newId = nextAvailableId();
        FigureCategoryID id = FigureCategoryID.valueOf(newId);
        final FigureCategory newCategory = new FigureCategory(id, name);
        categoryRepository.save(newCategory);
    }

    @Transactional
    public void editCategory(String oldName, String newName) {
        final FigureCategory category = categoryRepository.findByNameIgnoreCase(oldName)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        String trimmed = newName.trim();
        if (category.name().equalsIgnoreCase(trimmed)) {
            throw new IllegalArgumentException("The new name is the same as the current one.");
        }

        if (categoryRepository.findByNameIgnoreCase(newName)
                .filter(c -> !c.identity().equals(category.identity())).isPresent()) {
            throw new IllegalArgumentException("A category with this name already exists.");
        }
        category.rename(newName);
        categoryRepository.save(category);
    }

    @Transactional
    public Iterable<FigureCategory> listAllCategories() {
        try {
            Iterable<FigureCategory> categories = categoryRepository.findAll();
            if (categories == null || !categories.iterator().hasNext()) {
                throw new IllegalStateException("No categories found in the system.");
            }
            return categories;
        } catch (Exception e) {
            throw new IllegalStateException("Unable to list categories: " + e.getMessage(), e);
        }
    }


    @Transactional
    public FigureCategory toggleCategoryActivation(String oldName) {
        final FigureCategory category = categoryRepository.findByNameIgnoreCase(oldName)
                .orElseThrow(() -> new IllegalArgumentException("Category not found."));

        if (isCategoryInUse(category.identity())) {
            throw new IllegalStateException("Cannot toggle this category because it is used by one or more figures.");
        }

        if (category.isActive()) {
            category.deactivate();
        } else {
            category.activate();
        }

        return categoryRepository.save(category);
    }


    private Integer nextAvailableId() {
        Integer maxId = categoryRepository.findMaxId();
        return (maxId != null) ? maxId + 1 : 1;
    }

    private boolean isCategoryInUse(FigureCategoryID categoryID) {
        return StreamSupport.stream(figureRepository.findAll().spliterator(), false)
                .anyMatch(fig -> fig.figureCategoryID().equals(categoryID));
    }

}

