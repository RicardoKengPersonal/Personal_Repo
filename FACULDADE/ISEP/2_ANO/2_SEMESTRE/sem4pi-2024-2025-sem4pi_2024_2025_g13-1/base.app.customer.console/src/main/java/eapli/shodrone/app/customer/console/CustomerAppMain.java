package eapli.shodrone.app.customer.console;

import eapli.framework.infrastructure.pubsub.EventDispatcher;
import eapli.shodrone.app.common.console.ShodroneBaseApplication;
import eapli.shodrone.app.customer.console.application.ClientSession;
import eapli.shodrone.app.customer.console.presentation.LoginAction;
import eapli.shodrone.app.customer.console.presentation.MainMenu;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;
import eapli.shodrone.shodroneusermanagement.application.eventhandlers.NewUserRegisteredFromSignupWatchDog;
import eapli.shodrone.shodroneusermanagement.domain.events.NewUserRegisteredFromSignupEvent;
import eapli.shodrone.shodroneusermanagement.domain.events.SignupAcceptedEvent;
import eapli.shodrone.usermanagement.application.eventhandlers.SignupAcceptedWatchDog;

public class CustomerAppMain extends ShodroneBaseApplication {

    private CustomerAppMain() {
        // construtor privado para garantir o padrão singleton
    }

    public static void main(String[] args) {
        new CustomerAppMain().run(args);
    }

    @Override
    protected void doMain(final String[] args) {
        boolean wantsToExit = false;
        // O ciclo principal que permite voltar ao login após o logout
        while (!wantsToExit) {
            System.out.println("\n--- Please Login ---");

            if (new LoginAction().execute()) {
                final var menu = new MainMenu();
                menu.mainLoop();

                if (ClientSession.getInstance().isAuthenticated()) {
                    wantsToExit = true;
                }

            } else {
                wantsToExit = true;
            }
        }
    }

    @Override
    protected String appTitle() {
        return "Shodrone Customer";
    }

    @Override
    protected String appGoodbye() {
        return "Thank you for using Shodrone Customer App!";
    }

    @Override
    protected void configureAuthz() {
        // A configuração de autenticação já não é feita no cliente.
        // O servidor é agora responsável por isso.
    }

    @Override
    protected void doSetupEventHandlers(final EventDispatcher dispatcher) {
        dispatcher.subscribe(new NewUserRegisteredFromSignupWatchDog(), NewUserRegisteredFromSignupEvent.class);
        dispatcher.subscribe(new SignupAcceptedWatchDog(), SignupAcceptedEvent.class);
    }
}