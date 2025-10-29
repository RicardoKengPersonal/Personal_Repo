package eapli.shodrone.app.bootstrap;

import eapli.framework.collections.util.ArrayPredicates;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.pubsub.EventDispatcher;
import eapli.framework.io.util.Console;
import eapli.shodrone.app.common.console.ShodroneBaseApplication;
import eapli.shodrone.infrastructure.bootstrapers.CustomerBootstrapper;
import eapli.shodrone.infrastructure.bootstrapers.ShodroneBootstrapper;
import eapli.shodrone.infrastructure.bootstrapers.demo.*;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;
import eapli.shodrone.infrastructure.smoketests.ShodroneDemoSmokeTester;
import eapli.shodrone.shodroneusermanagement.application.eventhandlers.NewUserRegisteredFromSignupWatchDog;
import eapli.shodrone.shodroneusermanagement.domain.events.NewUserRegisteredFromSignupEvent;
import eapli.shodrone.shodroneusermanagement.domain.events.SignupAcceptedEvent;
import eapli.shodrone.usermanagement.application.eventhandlers.SignupAcceptedWatchDog;
import eapli.shodrone.usermanagement.domain.ShodronePasswordPolicy;

public final class Bootstrap extends ShodroneBaseApplication {

    private boolean isToBootstrapDemoData;
    private boolean isToRunSampleE2E;
    private boolean isToWaitInTheEnd;

    /**
     * avoid instantiation of this class.
     */
    private Bootstrap() {
    }

    public static void main(final String[] args) {


        new Bootstrap().run(args);


    }

    @Override
    protected void doMain(final String[] args) {
        handleArgs(args);

        System.out.println("\n\n------- MASTER DATA -------");
        new ShodroneBootstrapper().execute();
        new CustomerBootstrapper().execute();
        new DroneModelBootstrapper().execute();
        new DroneBootstrapper().execute();
        new FigureBootstrapper().execute();
        new ShowProposalBootstrapper().execute();
        new ShowBootstrapper().execute();


        if (isToBootstrapDemoData) {
            System.out.println("\n\n------- DEMO DATA -------");
            new DemoBootstrapper().execute();
        }
        if (isToRunSampleE2E) {
            System.out.println("\n\n------- BASIC SCENARIO -------");
            new ShodroneDemoSmokeTester().execute();
        }

        if (isToWaitInTheEnd) {
            Console.readLine("\n\n>>>>>> Enter to finish the program.");
        }

    }

    private void handleArgs(final String[] args) {
        isToRunSampleE2E = ArrayPredicates.contains(args, "-smoke:e2e");
        if (isToRunSampleE2E) {
            isToBootstrapDemoData = true;
        } else {
            isToBootstrapDemoData = ArrayPredicates.contains(args, "-bootstrap:demo");
        }

        isToWaitInTheEnd = ArrayPredicates.contains(args, "-wait");
    }

    @Override
    protected String appTitle() {
        return "Bootstrapping eCafeteria data ";
    }

    @Override
    protected String appGoodbye() {
        return "Bootstrap data done.";
    }

    @Override
    protected void configureAuthz() {
        AuthzRegistry.configure(PersistenceContext.repositories().users(),
                new ShodronePasswordPolicy(),
                new PlainTextEncoder());
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void doSetupEventHandlers(final EventDispatcher dispatcher) {
        dispatcher.subscribe(new NewUserRegisteredFromSignupWatchDog(), NewUserRegisteredFromSignupEvent.class);
        dispatcher.subscribe(new SignupAcceptedWatchDog(), SignupAcceptedEvent.class);
    }


}