US231 – Show Request
==============================
---
# Analysis

---

## Business Rules

    - This use case handles the listing of all active public figures in the catalogue by a CRM Collaborator.

## Acceptance Criteria

    - The catalogue must display only active public figures.
    - Each entry must display: figure code, description, version, designer, category, DSL version and visibility

# Design

---

## Domain

Entity: Figure

    public class Figure {
    
        private FigureCode code;
        private String description;
        private String version;
        private SystemUser designer;
        private FigureCategory category;
        private String dslVersion;
        private FigureVisibility visibility;
    
        protected Figure() {
            this.code = new FigureCode();
            this.description = "";
            this.version = "";
            this.dslVersion = "";
            this.visibility = FigureVisibility.PRIVATE;
        }
    
        public Figure(FigureCode code, String description, String version, SystemUser designer, String dslVersion, FigureVisibility visibility, FigureCategory category) {
            if (code == null || description == null || version == null || designer == null || dslVersion == null || visibility == null || category == null) {
                throw new IllegalArgumentException("Figure attributes must not be null");
            }
            this.code = code;
            this.description = description;
            this.version = version;
            this.designer = designer;
            this.dslVersion = dslVersion;
            this.visibility = visibility;
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
            return String.format("Code: %s | Description: %s | Version: %s | DSL: %s | Visibility: %s | Category: %s | Designer: %s", code, description, version, dslVersion, visibility, description, designer.identity());
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
        public Iterable<Figure> listPublicFigures() {
            try {
                Iterable<Figure> publicFigures = figRepository.findByVisibility(FigureVisibility.PUBLIC);
                if (publicFigures == null || !publicFigures.iterator().hasNext()) {
                    throw new IllegalStateException("No public figures found in the system.");
                }
                return publicFigures;
            } catch (Exception e) {
                throw new IllegalStateException("Unable to list public figures: " + e.getMessage(), e);
            }
        }
    }

## Application

---

Controller: ListPublicFiguresController

    public class ListPublicFiguresController {
    
        private final AuthorizationService authz;
        private final FigureRepository figureRepository;
    
        private static final Set<Role> ALLOWED_ROLES = Set.of(Roles.CRM_COLLABORATOR, Roles.SHOW_DESIGNER);
    
        public ListPublicFiguresController(AuthorizationService authz, FigureRepository figureRepository) {
            this.authz = authz;
            this.figureRepository = figureRepository;
        }
    
        public List<Figure> listPublicFigures() {
            authz.ensureAuthenticatedUserHasAnyOf((Role) ALLOWED_ROLES);
            return figureRepository.findByVisibility(FigureVisibility.PUBLIC);
        }
    }



## UI (CLI/Backoffice)

---

    - Command in the menu: "List Figures in Catalogue"
    - Input: none
    - Output: info about the code, description, version, designer, dsl version and visibility


## Testing

---
Unit Tests

    - Check if the visibility is public
    - Check if the visibility is private
    - Check if the visibility is exclusive


## Sequence Diagram

---

![diagram](/docs/us_231/us_231.svg)