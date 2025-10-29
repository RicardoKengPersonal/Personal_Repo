/*
 * Copyright (c) 2013-2024 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package eapli.shodrone.app.backoffice.console;

import eapli.framework.infrastructure.pubsub.EventDispatcher;
import eapli.shodrone.app.common.console.ShodroneBaseApplication;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.shodrone.app.backoffice.console.presentation.MainMenu;
import eapli.shodrone.app.common.console.presentation.authz.LoginUI;
import eapli.shodrone.infrastructure.authz.AuthenticationCredentialHandler;
import eapli.shodrone.shodroneusermanagement.application.eventhandlers.NewUserRegisteredFromSignupWatchDog;
import eapli.shodrone.shodroneusermanagement.domain.events.NewUserRegisteredFromSignupEvent;
import eapli.shodrone.shodroneusermanagement.domain.events.SignupAcceptedEvent;
import eapli.shodrone.usermanagement.application.eventhandlers.SignupAcceptedWatchDog;
import eapli.shodrone.usermanagement.domain.ShodronePasswordPolicy;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;

public final class BackofficeMain extends ShodroneBaseApplication {

    private BackofficeMain() {
        // avoid instantiation
    }

    public static void main(final String[] args) {
        new BackofficeMain().run(args);
    }

    @Override
    protected void doMain(final String[] args) {

        final boolean authenticated = new LoginUI(new AuthenticationCredentialHandler()).show();
         if (authenticated) {
             final var menu = new MainMenu();
             menu.mainLoop();
         }
    }

    @Override
    protected String appTitle() {
        return "Shodrone Backoffice";
    }

    @Override
    protected String appGoodbye() {
        return "Thank you for using Shodrone!";
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
