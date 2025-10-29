/*
 * Copyright (c) 2013-2024 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package eapli.shodrone.app.backoffice.console.presentation.authz;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;
import eapli.shodrone.shodroneusermanagement.domain.PhoneNumber;
import eapli.shodrone.shodroneusermanagement.domain.ShodroneUser;
import eapli.shodrone.usermanagement.application.AddUserController;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * UI for adding a user to the application.
 *
 * Created by nuno on 22/03/16.
 */
@SuppressWarnings("java:S106")
public class AddUserUI extends AbstractUI {

    private final AddUserController theController = new AddUserController();
    private static final Pattern VALID_NAME_REGEX = Pattern.compile("^[\\pL\\pM\\p{Nl}][\\pL\\pM\\p{Nl} ',.\\-]*$", Pattern.UNICODE_CASE);
    private static final Pattern EMAIL_REGEX = Pattern.compile("^[\\w.+\\-]+@shodrone\\.com$");
    private static final Pattern USERNAME_REGEX = Pattern.compile("^\\S+$");
    private static final Pattern PASSWORD_DIGIT = Pattern.compile(".*\\d.*");
    private static final Pattern PASSWORD_UPPER = Pattern.compile(".*[A-Z].*");
    private static final Pattern PHONE_REGEX = Pattern.compile("^\\+?\\d{9,15}$");

    @Override
    protected boolean doShow() {
        // FIXME avoid duplication with SignUpUI. reuse UserDataWidget from
        // UtenteApp
        final String username = promptUsername();
        final String password = promptPassword();
        final String firstName = promptName("First Name");
        final String lastName = promptName("Last Name");
        final String email = promptEmail();
        final String phone = promptPhone();
        System.out.println("\nChoose a Role for the new user:");
        final Set<Role> roleTypes = new HashSet<>();
        boolean show;
        do {
            show = showRoles(roleTypes);
        } while (!show);

        try {
            final SystemUser systemUser = theController.addUser(username, password, firstName, lastName, email, roleTypes);
            assert systemUser != null;

            final var phoneNumber = new PhoneNumber(phone);
            final var shodroneUser = new ShodroneUser(systemUser, phoneNumber);
            PersistenceContext.repositories().shodroneUsers().save(shodroneUser);
        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("That username is already in use.");
        }

        return false;
    }

    private boolean showRoles(final Set<Role> roleTypes) {
        // TODO we could also use the "widget" classes from the framework...
        final Menu rolesMenu = buildRolesMenu(roleTypes);
        final MenuRenderer renderer = new VerticalMenuRenderer(rolesMenu, MenuItemRenderer.DEFAULT);
        return renderer.render();
    }

    private Menu buildRolesMenu(final Set<Role> roleTypes) {
        final Menu rolesMenu = new Menu();
        int counter = 0;
        rolesMenu.addItem(MenuItem.of(counter++, "No Role", Actions.SUCCESS));
        for (final Role roleType : theController.getRoleTypes()) {
            rolesMenu.addItem(
                    MenuItem.of(counter++, roleType.toString(), () -> roleTypes.add(roleType)));
        }
        return rolesMenu;
    }

    @Override
    public String headline() {
        return "Add User";
    }
    private String promptUsername() {
        while (true) {
            final String input = Console.readLine("Username:");
            if (input == null || input.trim().isEmpty()) {
                System.out.println(" Username cannot be empty.");
                continue;
            }
            if (!USERNAME_REGEX.matcher(input).matches()) {
                System.out.println(" Username must be a single word without spaces.");
                continue;
            }
            return input.trim();
        }
    }

    private String promptPassword() {
        while (true) {
            final String input = Console.readLine("Password:");
            if (input.length() < 6) {
                System.out.println(" Password must be at least 6 characters long.");
                continue;
            }
            if (!PASSWORD_DIGIT.matcher(input).matches()) {
                System.out.println(" Password must contain at least one digit.");
                continue;
            }
            if (!PASSWORD_UPPER.matcher(input).matches()) {
                System.out.println(" Password must contain at least one uppercase letter.");
                continue;
            }
            return input;
        }
    }

    private String promptName(String type) {
        while (true) {
            final String input = Console.readLine(type);
            if (input == null || input.trim().isEmpty()) {
                System.out.println( type + " cannot be empty.");
                continue;
            }
            if (!VALID_NAME_REGEX.matcher(input.trim()).matches()) {
                System.out.println( type + " contains invalid characters.");
                continue;
            }
            return input.trim();
        }
    }

    private String promptEmail() {
        while (true) {
            final String input = Console.readLine("E-Mail:");
            if (input == null || input.trim().isEmpty()) {
                System.out.println(" Email cannot be empty.");
                continue;
            }
            if (!EMAIL_REGEX.matcher(input.trim()).matches()) {
                System.out.println(" Invalid email. It must end with @shodrone.com.");
                continue;
            }
            return input.trim();
        }
    }

    private String promptPhone() {
        while (true) {
            final String input = Console.readLine("Phone Number:");
            if (input == null || input.trim().isEmpty()) {
                System.out.println(" Phone number cannot be empty.");
                continue;
            }
            if (!PHONE_REGEX.matcher(input.trim()).matches()) {
                System.out.println(" Invalid phone number format.");
                continue;
            }
            return input.trim();
        }
    }

}
