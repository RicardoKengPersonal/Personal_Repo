package eapli.shodrone.showrequestmanagement.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.shodrone.customermanagement.domain.*;
import eapli.shodrone.figuremanagement.domain.Figure;
import eapli.shodrone.figuremanagement.domain.FigureVersion;
import eapli.shodrone.figuremanagement.domain.FigureVersionID;
import eapli.shodrone.showrequestmanagement.domain.Address;
import eapli.shodrone.customermanagement.repository.CustomerRepository;
import eapli.shodrone.figuremanagement.repository.FigureRepository;
import eapli.shodrone.showrequestmanagement.repository.ShowRequestRepository;
import eapli.shodrone.usermanagement.domain.Roles;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@UseCaseController
public class AddShowRequestController {

    private final AuthorizationService authz;
    private final ShowRequestService service;

    public AddShowRequestController(
            final AuthorizationService authz,
            final CustomerRepository customerRepo,
            final FigureRepository figureRepo,
            final ShowRequestRepository showRequestRepo,
            final UserRepository userRepository) {

        this.authz = authz;
        this.service = new ShowRequestService(customerRepo, figureRepo, showRequestRepo, userRepository);
    }

    public Iterable<Customer> listCustomers() {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.ADMIN, Roles.CRM_COLLABORATOR);
        return service.allCreatedCustomers();
    }

    public Iterable<Figure> listPublicFigures() {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.ADMIN, Roles.CRM_COLLABORATOR);
        return service.listPublicFigures();
    }

    public Iterable<SystemUser> findAllUsers() {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.ADMIN, Roles.CRM_COLLABORATOR);
        return service.findAllUsers();
    }



    public void addShowRequest(
            VatNumber customerVat,
            CustomerRepresentative representative,
            SystemUser author,
            String street, String city, String zip,
            LocalDate eventDate,
            LocalTime eventTime,
            int durationMinutes,
            String description,
            int numDrones,
            Set<FigureVersionID> figureVersions,
            SystemUser designer) {

        authz.ensureAuthenticatedUserHasAnyOf(Roles.ADMIN, Roles.CRM_COLLABORATOR);

        final Address address = new Address(street, city, zip);
        service.registerNewShowRequest(
                customerVat, representative, author, address,
                eventDate, eventTime, durationMinutes,
                description, numDrones, figureVersions, designer
        );
    }

    public Iterable<CustomerRepresentative> representativesForCustomer(VatNumber vatNumber) {
        authz.ensureAuthenticatedUserHasAnyOf(Roles.ADMIN, Roles.CRM_COLLABORATOR);
        return service.representativesForCustomer(vatNumber);
    }
}
