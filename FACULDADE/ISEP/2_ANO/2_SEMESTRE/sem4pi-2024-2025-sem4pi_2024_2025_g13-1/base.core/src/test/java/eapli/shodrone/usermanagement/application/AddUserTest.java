package eapli.shodrone.usermanagement.application;

import static org.junit.jupiter.api.Assertions.*;


import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.*;
import eapli.shodrone.shodroneusermanagement.domain.PhoneNumber;
import eapli.shodrone.shodroneusermanagement.domain.ShodroneUser;
import eapli.shodrone.usermanagement.domain.Roles;
import eapli.shodrone.usermanagement.domain.UserBuilderHelper;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Set;

class AddUserTest {


    @Test
    void ensureUserCanBeAddedSuccessfully() {
        // Arrange
        String username = "testUser01";
        Set<Role> roles = Collections.singleton(Roles.ADMIN);
        final var userBuilder = UserBuilderHelper.builder();
        userBuilder.withUsername("testUser01").withPassword("Password1").withName("Test", "User")
                .withEmail("testuser01@showdrone.com").withRoles(Roles.ADMIN);
        final var newUser = userBuilder.build();


        // Assert
        assertNotNull(newUser);
        assertEquals(username, newUser.username().toString());
    }

    @Test
    void ensureSystemUserAndShodroneUserCanBeCreatedSuccessfully() {
        // Arrange
        String username = "testUser01";
        Set<Role> roles = Collections.singleton(Roles.ADMIN);
        final var userBuilder = UserBuilderHelper.builder();
        userBuilder.withUsername("testUser01").withPassword("Password1").withName("Test", "User")
                .withEmail("testuser01@showdrone.com").withRoles(Roles.ADMIN);
        final var newUser = userBuilder.build();

        final PhoneNumber phoneNumber = new PhoneNumber("931234567");
        final ShodroneUser shodroneUser = new ShodroneUser(newUser, phoneNumber);

        assertNotNull(newUser);
        assertEquals(username, newUser.username().toString());

        assertNotNull(shodroneUser);
        assertEquals(newUser, shodroneUser.user());
        assertEquals(phoneNumber, shodroneUser.phoneNumber());
    }

    @Test
    void ensureUserCannotBeCreatedWithEmptyName() {
        final var builder = UserBuilderHelper.builder();
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            builder.withUsername("noNameUser")
                    .withPassword("Password1")
                    .withName("", "")
                    .withEmail("noname@showdrone.com")
                    .withRoles(Roles.ADMIN)
                    .build();
        });
        assertTrue(ex.getMessage().toLowerCase().contains("name"));
    }

    @Test
    void ensureUsernameCannotBeEmpty() {
        final var builder = UserBuilderHelper.builder();
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            builder.withUsername("")
                    .withPassword("Password1")
                    .withName("No", "Username")
                    .withEmail("nousername@showdrone.com")
                    .withRoles(Roles.ADMIN)
                    .build();
        });
        assertTrue(ex.getMessage().toLowerCase().contains("username"));
    }


}
