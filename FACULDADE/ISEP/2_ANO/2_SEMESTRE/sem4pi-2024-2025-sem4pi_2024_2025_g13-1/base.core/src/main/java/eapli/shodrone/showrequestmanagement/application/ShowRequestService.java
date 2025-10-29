package eapli.shodrone.showrequestmanagement.application;

import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.shodrone.customermanagement.domain.*;
import eapli.shodrone.figuremanagement.domain.Figure;
import eapli.shodrone.figuremanagement.domain.FigureVisibility;
import eapli.shodrone.showrequestmanagement.domain.Address;
import eapli.shodrone.figuremanagement.domain.FigureVersion;
import eapli.shodrone.figuremanagement.domain.FigureVersionID;
import eapli.shodrone.showrequestmanagement.domain.*;
import eapli.shodrone.customermanagement.repository.CustomerRepository;
import eapli.shodrone.figuremanagement.repository.FigureRepository;
import eapli.shodrone.showrequestmanagement.domain.Date;
import eapli.shodrone.showrequestmanagement.repository.ShowRequestRepository;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class ShowRequestService {

    private final CustomerRepository customerRepo;
    private final FigureRepository figureRepo;
    private final ShowRequestRepository showRequestRepo;
    private final UserRepository userRepo;

    public ShowRequestService(
            CustomerRepository customerRepo,
            FigureRepository figureRepo,
            ShowRequestRepository showRequestRepo,
            UserRepository userRepo) {
        this.customerRepo = customerRepo;
        this.figureRepo = figureRepo;
        this.showRequestRepo = showRequestRepo;
        this.userRepo = userRepo;
    }

    public Iterable<Customer> allCreatedCustomers() {
        return customerRepo.findAll();
    }

    @Transactional
    public Iterable<Figure> listPublicFigures() {
        Iterable<Figure> publicFigures = figureRepo.findByVisibility(FigureVisibility.PUBLIC);
        return (publicFigures != null) ? publicFigures : Collections.emptyList();
    }


    public Iterable<SystemUser> findAllUsers() {
        return userRepo.findAll();
    }


    public Iterable<CustomerRepresentative> representativesForCustomer(VatNumber vatNumber) {
        return customerRepo.findEnableRepresentatives(vatNumber);
    }

    public void registerNewShowRequest(
            VatNumber customerVat,
            CustomerRepresentative representative,
            SystemUser author,
            Address address,
            LocalDate eventDate,
            LocalTime eventTime,
            int durationMinutes,
            String description,
            int numDrones,
            Set<FigureVersionID> figureVersions,
            SystemUser designer) {

        final Customer customer = customerRepo.findByVATNumber(customerVat)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found: " + customerVat));

        if (!representative.customer().equals(customer)) {
            throw new IllegalArgumentException("Representative does not belong to the specified customer.");
        }

        Integer newId = nextAvailableId();
        final ShowRequestID id = ShowRequestID.valueOf(newId);
        final ShowRequestDuration duration = new ShowRequestDuration(durationMinutes);
        final ShowRequestDescription desc = new ShowRequestDescription(description);
        final Date submissionDate = Date.from(LocalDateTime.now());

        final Date eventDateValue = Date.from(eventDate);
        final Time eventTimeValue = Time.from(eventTime);

        final ShowRequest request = new ShowRequest(
                id,
                address,
                duration,
                desc,
                submissionDate,
                eventDateValue,
                eventTimeValue,
                customer,
                figureVersions,
                numDrones,
                designer,
                representative,
                author
        );

        showRequestRepo.save(request);
    }


    private Integer nextAvailableId() {
        Integer maxId = showRequestRepo.findMaxId();
        return (maxId != null) ? maxId + 1 : 1;
    }
}
