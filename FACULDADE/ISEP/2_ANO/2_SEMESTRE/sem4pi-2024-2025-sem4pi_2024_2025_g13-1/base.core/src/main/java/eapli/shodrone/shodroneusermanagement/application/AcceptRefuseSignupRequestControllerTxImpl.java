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

import eapli.shodrone.shodroneusermanagement.domain.ShodroneUserBuilder;
import eapli.shodrone.shodroneusermanagement.domain.SignupRequest;
import eapli.shodrone.shodroneusermanagement.repositories.ShodroneUserRepository;
import eapli.shodrone.shodroneusermanagement.repositories.SignupRequestRepository;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;
import eapli.shodrone.usermanagement.domain.Roles;
import eapli.framework.application.UseCaseController;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.HashSet;
import java.util.Set;

/**
 * The transactional controller for the use case "accept/refuse a signup
 * request".
 * <p>
 * Following the guideline that a controller should only change one Aggregate,
 * we shouldn't be changing all these entities here, but should instead use
 * asynchronous events. However in this case we will take advantage of
 * TransactionalContext
 *
 * @author AJS on 08/04/2016.
 */
@UseCaseController
public class AcceptRefuseSignupRequestControllerTxImpl implements AcceptRefuseSignupRequestController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final UserManagementService userService = AuthzRegistry.userService();

    private final TransactionalContext txCtx = PersistenceContext.repositories().newTransactionalContext();
    private final ShodroneUserRepository cafeteriaUserRepository = PersistenceContext.repositories()
            .shodroneUsers(txCtx);
    private final SignupRequestRepository signupRequestsRepository = PersistenceContext.repositories()
            .signupRequests(txCtx);


    @Override
    public SignupRequest acceptSignupRequest(SignupRequest theSignupRequest) {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.ADMIN);

        if (theSignupRequest == null) {
            throw new IllegalArgumentException();
        }

        // explicitly begin a transaction
        txCtx.beginTransaction();

        final SystemUser newUser = createSystemUserForShodroneUser(theSignupRequest);
        createShodroneUser(theSignupRequest, newUser);
        theSignupRequest = acceptTheSignupRequest(theSignupRequest);

        // explicitly commit the transaction
        txCtx.commit();

        return theSignupRequest;
    }

    private SignupRequest acceptTheSignupRequest(final SignupRequest theSignupRequest) {
        theSignupRequest.accept();
        return signupRequestsRepository.save(theSignupRequest);
    }

    private void createShodroneUser(final SignupRequest theSignupRequest, final SystemUser newUser) {
        final var cafeteriaUserBuilder = new ShodroneUserBuilder();
        cafeteriaUserBuilder.withPhoneNumber(theSignupRequest.phoneNumber()).withSystemUser(newUser);
        cafeteriaUserRepository.save(cafeteriaUserBuilder.build());
    }


    private SystemUser createSystemUserForShodroneUser(final SignupRequest theSignupRequest) {
        final Set<Role> roles = new HashSet<>();
        roles.add(Roles.ADMIN);
        return userService.registerUser(theSignupRequest.username(), theSignupRequest.password(),
                theSignupRequest.name(), theSignupRequest.email(), roles);
    }


    @Override
    public SignupRequest refuseSignupRequest(SignupRequest theSignupRequest) {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.ADMIN);

        if (theSignupRequest == null) {
            throw new IllegalArgumentException();
        }

        txCtx.beginTransaction();

        theSignupRequest.refuse();
        theSignupRequest = signupRequestsRepository.save(theSignupRequest);


        txCtx.commit();

        return theSignupRequest;
    }


    @Override
    public Iterable<SignupRequest> listPendingSignupRequests() {
        return signupRequestsRepository.pendingSignupRequests();
    }
}
