package eapli.shodrone.app.testing.console;

import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.pubsub.EventDispatcher;
import eapli.shodrone.app.common.console.ShodroneBaseApplication;
import eapli.shodrone.app.common.console.presentation.authz.LoginUI;
import eapli.shodrone.app.testing.console.presentation.MainMenu;
import eapli.shodrone.infrastructure.authz.AuthenticationCredentialHandler;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;
import eapli.shodrone.shodroneusermanagement.application.eventhandlers.NewUserRegisteredFromSignupWatchDog;
import eapli.shodrone.shodroneusermanagement.domain.events.NewUserRegisteredFromSignupEvent;
import eapli.shodrone.shodroneusermanagement.domain.events.SignupAcceptedEvent;
import eapli.shodrone.usermanagement.application.eventhandlers.SignupAcceptedWatchDog;
import eapli.shodrone.usermanagement.domain.Roles;
import eapli.shodrone.usermanagement.domain.ShodronePasswordPolicy;


public class TestingAppMain extends ShodroneBaseApplication {

    private TestingAppMain() {

    }

    public static void main(String[] args) {
        new TestingAppMain().run(args);
    }

    @Override
    protected void doMain(final String[] args) {
        boolean authenticated = false;
        boolean authorized = false;

        do {

            authenticated = new LoginUI(new AuthenticationCredentialHandler()).show();
            if (!authenticated) {
                System.out.println("Login failed. Please try again.");
                continue;
            }

            var authz = AuthzRegistry.authorizationService();

            if (!authz.isAuthenticatedUserAuthorizedTo(Roles.ADMIN, Roles.DRONE_TECH)) {
                System.out.println("Access denied. Only ADMIN or Drone Tech can access this application.");
                authenticated = false;
            } else {
                authorized = true;
            }

        } while (!authenticated || !authorized);

        final var menu = new MainMenu();
        menu.mainLoop();
    }



    @Override
    protected String appTitle() {
        return "Shodrone Drone Tech";
    }

    @Override
    protected String appGoodbye() {
        return "Thank you for using Shodrone Drone Tech App!";
    }

    @Override
    protected void configureAuthz() {
        AuthzRegistry.configure(
                PersistenceContext.repositories().users(),
                new ShodronePasswordPolicy(),
                new PlainTextEncoder()
        );
    }


    @Override
    protected void doSetupEventHandlers(final EventDispatcher dispatcher) {
        dispatcher.subscribe(new NewUserRegisteredFromSignupWatchDog(), NewUserRegisteredFromSignupEvent.class);
        dispatcher.subscribe(new SignupAcceptedWatchDog(), SignupAcceptedEvent.class);
    }
}
