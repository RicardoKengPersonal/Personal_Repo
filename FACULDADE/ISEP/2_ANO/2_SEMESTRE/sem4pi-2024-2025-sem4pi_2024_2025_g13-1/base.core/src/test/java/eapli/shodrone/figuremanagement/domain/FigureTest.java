package eapli.shodrone.figuremanagement.domain;

import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.shodrone.usermanagement.domain.Roles;
import eapli.shodrone.usermanagement.domain.UserBuilderHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FigureTest {

    private SystemUser createDummyDesigner() {
        return UserBuilderHelper.builder()
                .withUsername("designer1")
                .withPassword("Password1")
                .withName("Designer", "One")
                .withEmail("designer1@shodrone.com")
                .withRoles(Roles.SHOW_DESIGNER)
                .build();
    }

    @Test
    void ensureFigureIsCreatedSuccessfully() {
        FigureID id = FigureID.valueOf(1);
        FigureCategoryID categoryID = FigureCategoryID.valueOf(1);
        Figure figure = new Figure(id, "Test Figure", FigureVisibility.PUBLIC, "keyword", "description", categoryID);

        assertNotNull(figure);
        assertEquals("Test Figure", figure.figureName());
        assertEquals(FigureVisibility.PUBLIC, figure.visibility());
        assertEquals("keyword", figure.keyword());
        assertEquals("description", figure.description());
        assertEquals(categoryID, figure.figureCategoryID());
    }

    @Test
    void ensureFigureNameCannotBeNull() {
        FigureID id = FigureID.valueOf(1);
        FigureCategoryID categoryID = FigureCategoryID.valueOf(1);

        assertThrows(NullPointerException.class, () ->
                new Figure(id, null, FigureVisibility.PUBLIC, "keyword", "description", categoryID)
        );
    }

    @Test
    void ensureVisibilityCannotBeNull() {
        FigureID id = FigureID.valueOf(1);
        FigureCategoryID categoryID = FigureCategoryID.valueOf(1);

        assertThrows(NullPointerException.class, () ->
                new Figure(id, "Test Figure", null, "keyword", "description", categoryID)
        );
    }

    @Test
    void ensureKeywordCannotBeNull() {
        FigureID id = FigureID.valueOf(1);
        FigureCategoryID categoryID = FigureCategoryID.valueOf(1);

        assertThrows(NullPointerException.class, () ->
                new Figure(id, "Test Figure", FigureVisibility.PUBLIC, null, "description", categoryID)
        );
    }

    @Test
    void ensureDescriptionCannotBeNull() {
        FigureID id = FigureID.valueOf(1);
        FigureCategoryID categoryID = FigureCategoryID.valueOf(1);

        assertThrows(NullPointerException.class, () ->
                new Figure(id, "Test Figure", FigureVisibility.PUBLIC, "keyword", null, categoryID)
        );
    }

    @Test
    void ensureCategoryIDCannotBeNull() {
        FigureID id = FigureID.valueOf(1);

        assertThrows(NullPointerException.class, () ->
                new Figure(id, "Test Figure", FigureVisibility.PUBLIC, "keyword", "description", null)
        );
    }

    @Test
    void ensureAddVersionWorks() {
        FigureID id = FigureID.valueOf(1);
        FigureCategoryID categoryID = FigureCategoryID.valueOf(1);
        Figure figure = new Figure(id, "Test Figure", FigureVisibility.PUBLIC, "keyword", "description", categoryID);

        FigureVersionID versionID = FigureVersionID.valueOf(1);
        DSL dsl = new DSL("test script");
        FigureStatic figureStatic = new FigureStatic("yes");
        FigureDynamic figureDynamic = new FigureDynamic("no");

        SystemUser designer = createDummyDesigner();

        FigureVersion version = new FigureVersion(versionID, figure, designer, dsl, figureStatic, figureDynamic);
        figure.addVersion(version);

        assertEquals(1, figure.versions().size());
    }

    @Test
    void ensureRemoveVersionByIdWorks() {
        FigureID id = FigureID.valueOf(1);
        FigureCategoryID categoryID = FigureCategoryID.valueOf(1);
        Figure figure = new Figure(id, "Test Figure", FigureVisibility.PUBLIC, "keyword", "description", categoryID);

        FigureVersionID versionID = FigureVersionID.valueOf(1);
        DSL dsl = new DSL("test script");
        FigureStatic figureStatic = new FigureStatic("yes");
        FigureDynamic figureDynamic = new FigureDynamic("no");

        SystemUser designer = createDummyDesigner();

        FigureVersion version = new FigureVersion(versionID, figure, designer, dsl, figureStatic, figureDynamic);
        figure.addVersion(version);

        assertTrue(figure.removeVersionById(versionID));
        assertEquals(0, figure.versions().size());
    }
}
