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
package eapli.shodrone.shodroneusermanagement.application;

import eapli.shodrone.shodroneusermanagement.domain.SignupRequest;
import eapli.shodrone.shodroneusermanagement.domain.events.SignupAcceptedEvent;
import eapli.shodrone.shodroneusermanagement.repositories.SignupRequestRepository;
import eapli.shodrone.usermanagement.domain.Roles;
import eapli.framework.application.UseCaseController;
import eapli.framework.domain.events.DomainEvent;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.pubsub.EventPublisher;
import eapli.framework.validations.Preconditions;
import lombok.RequiredArgsConstructor;


@UseCaseController
public class AcceptRefuseSignupRequestControllerEventfulImpl implements AcceptRefuseSignupRequestController {

    private final SignupRequestRepository signupRequestsRepository;
    private final AuthorizationService authorizationService;
    private final EventPublisher dispatcher;

    /**
     * Constructor.
     * <p>
     * We are using constructor dependency injection to facilitate testing of this controller.
     * <p>
     * This boilerplate code could be avoided by leveraging Lombok's {@link RequiredArgsConstructor}
     *
     * @param signupRequestsRepository
     * @param authorizationService
     * @param dispatcher
     */
    public AcceptRefuseSignupRequestControllerEventfulImpl(final SignupRequestRepository signupRequestsRepository,
            final AuthorizationService authorizationService, final EventPublisher dispatcher) {

        this.signupRequestsRepository = signupRequestsRepository;
        this.authorizationService = authorizationService;
        this.dispatcher = dispatcher;
    }

    @Override
    @SuppressWarnings("squid:S1226")
    public SignupRequest acceptSignupRequest(SignupRequest theSignupRequest) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(Roles.ADMIN);

        Preconditions.nonNull(theSignupRequest);

        theSignupRequest = markSignupRequestAsAccepted(theSignupRequest);
        return theSignupRequest;
    }

    /**
     * Modify Signup Request to accepted.
     *
     * @param theSignupRequest
     *
     * @return
     *
     * @throws ConcurrencyException
     * @throws IntegrityViolationException
     */
    @SuppressWarnings("squid:S1226")
    private SignupRequest markSignupRequestAsAccepted(SignupRequest theSignupRequest) {
        // do just what is needed in the scope of this use case
        theSignupRequest.accept();
        theSignupRequest = signupRequestsRepository.save(theSignupRequest);

        // notify interested parties (if any)
        final DomainEvent event = new SignupAcceptedEvent(theSignupRequest);
        dispatcher.publish(event);

        return theSignupRequest;
    }

    @Override
    public SignupRequest refuseSignupRequest(final SignupRequest theSignupRequest) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(Roles.ADMIN);

        Preconditions.nonNull(theSignupRequest);

        theSignupRequest.refuse();
        return signupRequestsRepository.save(theSignupRequest);
    }

    /**
     * @return
     */
    @Override
    public Iterable<SignupRequest> listPendingSignupRequests() {
        return signupRequestsRepository.pendingSignupRequests();
    }
}
