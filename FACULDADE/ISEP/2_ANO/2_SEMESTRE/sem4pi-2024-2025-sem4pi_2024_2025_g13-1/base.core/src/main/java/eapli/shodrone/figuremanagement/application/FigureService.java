package eapli.shodrone.figuremanagement.application;

import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.shodrone.dronemanagement.domain.DroneModel;
import eapli.shodrone.dronemanagement.domain.DroneType;
import eapli.shodrone.dronemanagement.repository.DroneModelRepository;
import eapli.shodrone.figuremanagement.domain.*;
import eapli.shodrone.figuremanagement.repository.FigureCategoryRepository;
import eapli.shodrone.figuremanagement.repository.FigureRepository;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationService
public class FigureService {

    private final FigureRepository figRepository;
    private final FigureCategoryRepository catRepository;
    private final DroneModelRepository droneModelRepo;

    public FigureService(final FigureRepository figRepository,
                         final FigureCategoryRepository categoryRepository) {
        this.figRepository = figRepository;
        this.catRepository = categoryRepository;
        this.droneModelRepo = PersistenceContext.repositories().droneModel();
    }

    @Transactional
    public Iterable<Figure> listPublicFigures() {
        Iterable<Figure> publicFigures = figRepository.findByVisibility(FigureVisibility.PUBLIC);
        if (publicFigures == null || !publicFigures.iterator().hasNext()) {
            throw new IllegalStateException("No public figures found in the system.");
        }
        return publicFigures;
    }

    public Iterable<FigureCategory> getAllCategories() {
        return catRepository.findAll();
    }

    @Transactional
    public Iterable<Figure> searchByKeywords(List<String> keywords) {
        return figRepository.findByKeywordsAndPublic(keywords, FigureVisibility.PUBLIC);
    }

    @Transactional
    public Iterable<Figure> searchByCategory(FigureCategoryID categoryID) {
        return figRepository.findByCategoryAndPublic(categoryID, FigureVisibility.PUBLIC);
    }

    @Transactional
    public Iterable<Figure> searchByCategoryAndKeywords(FigureCategoryID categoryID, List<String> keywords) {
        return figRepository.findByCategoryAndKeywordsAndPublic(categoryID, keywords, FigureVisibility.PUBLIC);
    }

    public Optional<Figure> findByKeyword(String keyword) {
        return figRepository.findByKeyword(keyword);
    }

    public Optional<Figure> findByName(String figureName) {
        return figRepository.findByName(figureName);
    }

    public List<Figure> findAllFigures() {
        return figRepository.findAllFigures();
    }

    public List<Figure> findByCategory(FigureCategoryID categoryID) {
        return figRepository.findByCategory(categoryID);
    }

    public Optional<FigureCategoryID> findCategoryByName(String categoryName) {
        return catRepository.findByNameIgnoreCase(categoryName).map(FigureCategory::identity);
    }

    public Figure createFigure(String name, String keyword, String description,
                               Optional<FigureCategoryID> category,
                               FigureVisibility visibility) {
        if (figRepository.findByName(name).isPresent()) {
            throw new IllegalArgumentException("A Figure with this name already exists.");
        }
        Integer newId = nextAvailableId();
        FigureID id = FigureID.valueOf(newId);
        return new Figure(id, name, visibility, keyword, description, category.get());
    }


    public FigureVersion addVersionToFigure(Figure figure,
                                            DSL dsl, FigureStatic figureStatic,
                                            FigureDynamic figureDynamic,
                                            SystemUser user) {

        Integer newId = nextAvailableId();
        FigureVersionID id = FigureVersionID.valueOf(newId);
        FigureVersion version = new FigureVersion(id, figure, user, dsl, figureStatic, figureDynamic);
        figure.addVersion(version);
        return version;
    }

    @Transactional
    public void addElementToVersionByType(FigureVersion version,
                                          ElementType type, Movement movement,
                                          DroneType droneType, int totalDrones) {
        FigureElement element = new FigureElement(type, movement, droneType, totalDrones);
        version.addElement(element);
    }

    public void saveFigure(Figure figure) {
        figRepository.save(figure);
    }

    public List<DroneModel> listDroneModels() {
        return droneModelRepo.findByActiveTrue();
    }

    private Integer nextAvailableId() {
        Integer maxId = figRepository.findMaxId();
        return (maxId != null) ? maxId + 1 : 1;
    }

    public Iterable<FigureCategory> getAllCategoriesActive() {
        return catRepository.findByActive();
    }

    public Iterable<FigureVersion> listAllFigureVersions() {
        return figRepository.findAllFigureVersions();
    }

    @Transactional
    public void decommissionFigure(FigureVersionID id) {
        figRepository.deleteFigureVersion(id);
    }
    @Transactional
    public void updateDsl(Figure figure, String dsLPath) {
        figure.addDslPathTo(dsLPath);
        figRepository.save(figure);
    }


}
