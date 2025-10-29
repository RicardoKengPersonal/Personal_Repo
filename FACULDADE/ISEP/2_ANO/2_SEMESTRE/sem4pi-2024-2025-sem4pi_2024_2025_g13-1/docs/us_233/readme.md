US233 – Show Request
==============================
---
# Analysis

---

## Business Rules

    The use case handles adding a figure to the public catalogue.

## Acceptance Criteria

    - Figures are classified with a category and a set of keywords. 
    - If a figure is custom-made to a customer’s request it is not public and can only be used in shows for that customer.

# Design

---

## Domain

Entity: Figure

    public class Figure implements AggregateRoot<FigureCode> {
    
        private FigureCode code;
        private String description;
        private String version;
        private SystemUser designer;
        private FigureCategory category;
        private String dslVersion;
        private FigureVisibility visibility;
    
        protected Figure() {
            // for ORM
        }
    
        public Figure(FigureCode code, String description, String version, SystemUser designer, String dslVersion, FigureVisibility visibility, FigureCategory category) {
            if (code == null || description == null || version == null || designer == null || dslVersion == null || category == null) {
                throw new IllegalArgumentException("Figure attributes must not be null");
            }
    
            this.code = code;
            this.description = description;
            this.version = version;
            this.designer = designer;
            this.dslVersion = dslVersion;
            this.visibility = visibility != null ? visibility : FigureVisibility.PRIVATE;
            this.category = category;
        }
    
        public boolean isPublic() {
            return this.visibility == FigureVisibility.PUBLIC;
        }
    
        public String description() {
            return description;
        }
    
        public FigureCategory category() {
            return category;
        }
    
        public SystemUser designer() {
            return designer;
        }
    
        public String version() {
            return version;
        }
    
        public String dslVersion() {
            return dslVersion;
        }
    
        public FigureVisibility visibility() {
            return visibility;
        }
    
        @Override
        public FigureCode identity() {
            return code;
        }
    
        @Override
        public boolean sameAs(Object other) {
            if (this == other) return true;
            if (other == null || getClass() != other.getClass()) return false;
            Figure that = (Figure) other;
            return this.code.equals(that.code);
        }
    
        @Override
        public int hashCode() {
            return code.hashCode();
        }
    
        @Override
        public String toString() {
            return String.format("Code: %s | Description: %s | Version: %s | DSL: %s | Visibility: %s | Category: %s | Designer: %s",
                    code, description, version, dslVersion, visibility, category.name(), designer.identity());
        }
    }


Interface: FigureRepository

    public interface FigureRepository extends DomainRepository<FigureCode, Figure> {
        List<Figure> findByVisibility(FigureVisibility visibility);
    }

Domain Service: FigureService

    public class FigureService {
    
        private final FigureRepository figRepository;
        private final FigureCategoryRepository catRepository;
    
        public FigureService(final FigureRepository figRepository, final FigureCategoryRepository categoryRepository) {
            this.figRepository = figRepository;
            this.catRepository = categoryRepository;
        }
        
        @Transactional
        public void registerFigure(final FigureCode code, final String desc, final String version,
                                   final String versionDSL, final FigureVisibility visibility,
                                   final Long categoryId, final List<String> keywords) {
    
            if (figRepository.ofIdentity(code).isPresent()) {
                throw new IllegalArgumentException("A figure with this code already exists.");
            }
    
            final SystemUser designer = AuthzRegistry.authorizationService().session()
                    .orElseThrow(() -> new IllegalStateException("No authenticated user found."))
                    .authenticatedUser();
    
            final FigureCategory category = catRepository.ofIdentity(categoryId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid category ID."));
    
            final Figure newFigure = new Figure(code, desc, version, designer, versionDSL, visibility, category);
            figRepository.save(newFigure);
        }
    
        public Iterable<FigureCategory> getAllCategories() {
            return catRepository.findAll();
        }
    
    }

## Application

---

Controller: AddFigureCatalogueController

    public class AddFigureCatalogueController {
    
        private final AuthorizationService authz;
        private final FigureService service;
    
        private static final Set<Role> ALLOWED_ROLES = Set.of(Roles.SHOW_DESIGNER);
    
        public AddFigureCatalogueController(final AuthorizationService authz, final FigureRepository repo, FigureCategoryRepository categoryRepo) {
            this.authz = authz;
            this.service = new FigureService(repo, categoryRepo);
        }
    
        public void addFigure(final FigureCode code, final String desc, final String version, final String versionDSL, final FigureVisibility visibility, final Long categoryId, final List<String> keywords) {
            authz.ensureAuthenticatedUserHasAnyOf((Role) ALLOWED_ROLES);
            service.registerFigure(code, desc, version, versionDSL, visibility, categoryId, keywords);
        }
    
        public Iterable<FigureCategory> listCategories() {
            authz.ensureAuthenticatedUserHasAnyOf((Role) ALLOWED_ROLES);
            return service.getAllCategories();
        }
    }

## UI (CLI/Backoffice)

---

    - Command in the menu: "Add Figures to the Catalogue"
    - Input: code, description, version, dsl version, visibility, category, keywords


## Testing

---
Unit Tests

    - Ensure that the figure is created correctly
    - Ensure that the figure is private
    - Verify if any attribute is null
    - Check if the identity is the same


## Sequence Diagram

---

![diagram](/docs/us_233/us_233.svg)