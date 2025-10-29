package eapli.shodrone.infrastructure.bootstrapers.demo;

import eapli.framework.actions.Action;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.shodrone.customermanagement.domain.Customer;
import eapli.shodrone.customermanagement.domain.CustomerRepresentative;
import eapli.shodrone.customermanagement.domain.VatNumber;
import eapli.shodrone.customermanagement.repository.CustomerRepository;
import eapli.shodrone.dronemanagement.domain.DroneModel;
import eapli.shodrone.dronemanagement.repository.DroneModelRepository;
import eapli.shodrone.figuremanagement.domain.FigureVersionID;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;
import eapli.shodrone.showproposalmanagement.domain.ShowProposal;
import eapli.shodrone.showproposalmanagement.domain.ShowProposalID;
import eapli.shodrone.showproposalmanagement.repository.ShowProposalRepository;
import eapli.shodrone.showrequestmanagement.domain.*;
import eapli.shodrone.showrequestmanagement.domain.Date;
import eapli.shodrone.showrequestmanagement.repository.ShowRequestRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class ShowProposalBootstrapper implements Action {

    @Override
    public boolean execute() {
        final ShowRequestRepository showRequestRepo = PersistenceContext.repositories().showRequestRepository();
        final ShowProposalRepository proposalRepo = PersistenceContext.repositories().showProposalRepository();
        final CustomerRepository customerRepo = PersistenceContext.repositories().customerRepository();
        final var userRepo = PersistenceContext.repositories().users();
        final DroneModelRepository droneModelRepository = PersistenceContext.repositories().droneModel();

        try {
            // Dados comuns
            Address address = new Address("Rua Exemplo", "Porto", "4444-123");
            ShowRequestDuration duration = new ShowRequestDuration(30);
            ShowRequestDescription description = new ShowRequestDescription("Demonstração de show automático com drones.");
            Date submissionDate = Date.from(LocalDateTime.now());
            Time eventTime = new Time(21, 30);
            Set<FigureVersionID> figureVersions = Set.of(new FigureVersionID(1));
            int numDrones = 5;

            SystemUser designer = userRepo.ofIdentity(Username.valueOf("ShowDesigner"))
                    .orElseThrow(() -> new IllegalArgumentException("Designer não encontrado"));

            SystemUser author = userRepo.ofIdentity(Username.valueOf("admin"))
                    .orElseThrow(() -> new IllegalArgumentException("Autor não encontrado"));

            Customer customer = customerRepo.findByVATNumber(new VatNumber("PT123456789"))
                    .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

            Iterable<CustomerRepresentative> activeReps = customerRepo.findEnableRepresentatives(customer.vatNumber());
            CustomerRepresentative representative = null;

            for (CustomerRepresentative rep : activeReps) {
                if (rep.email().toString().equalsIgnoreCase("rodrigo.silva@shodrone.pt")) {
                    representative = rep;
                    break;
                }
            }

            if (representative == null) {
                throw new IllegalArgumentException("Representante com esse email não encontrado para o cliente.");
            }

            DroneModel drone = droneModelRepository.findByNameIgnoreCase("DroneModel2")
                    .orElseThrow(() -> new IllegalStateException("Modelo de drone DroneModel2 não encontrado."));


            // === ShowRequest 1 ===
            ShowRequestID requestId1 = new ShowRequestID(1);
            Date eventDate1 = Date.from(LocalDate.now().plusDays(10));
            ShowRequest request1 = new ShowRequest(
                    requestId1, address, duration, description, submissionDate, eventDate1,
                    eventTime, customer, figureVersions, numDrones, designer, representative, author
            );
            showRequestRepo.save(request1);
            System.out.println("✅ ShowRequest 1 criada com sucesso.");
            ShowProposalID proposalId1 = new ShowProposalID(1);
            ShowProposal proposal1 = new ShowProposal(proposalId1, request1);
            proposal1.addDronesOfModel(drone, 1);
            Map<String, String> mapping1 = new HashMap<>();
            mapping1.put("LED", "DroneModel2");
            proposal1.addFigureToShow(new FigureVersionID(1), mapping1);
            proposal1.setVideoLink("https://server.local/simulations/proposal1.mp4");
            proposalRepo.save(proposal1);
            request1.markProposalCreated();
            showRequestRepo.save(request1);
            System.out.println("✅ ShowProposal 1 criada com sucesso.");

            // === ShowRequest 2 ===
            ShowRequestID requestId2 = new ShowRequestID(2);
            Date eventDate2 = Date.from(LocalDate.now().plusDays(20));
            ShowRequest request2 = new ShowRequest(
                    requestId2, address, duration, description, submissionDate, eventDate2,
                    eventTime, customer, figureVersions, numDrones, designer, representative, author
            );
            showRequestRepo.save(request2);
            System.out.println("✅ ShowRequest 2 criada com sucesso.");

            ShowProposalID proposalId2 = new ShowProposalID(2);
            ShowProposal proposal2 = new ShowProposal(proposalId2, request2);
            proposal2.addDronesOfModel(drone, 1);
            Map<String, String> mapping2 = new HashMap<>();
            mapping2.put("LED", "DroneModel2");
            proposal2.addFigureToShow(new FigureVersionID(1), mapping2);
            proposal2.setVideoLink("https://server.local/simulations/proposal2.mp4");
            proposalRepo.save(proposal2);
            request2.markProposalCreated();
            showRequestRepo.save(request2);
            System.out.println("✅ ShowProposal 2 criada com sucesso.");

            // === ShowRequest 3 ===
            ShowRequestID requestId3 = new ShowRequestID(3);
            Date eventDate3 = Date.from(LocalDate.now().plusDays(30));
            ShowRequest request3 = new ShowRequest(
                    requestId3, address, duration, description, submissionDate, eventDate3,
                    eventTime, customer, figureVersions, numDrones, designer, representative, author
            );
            showRequestRepo.save(request3);
            System.out.println("✅ ShowRequest 3 criada com sucesso.");

            ShowProposalID proposalId3 = new ShowProposalID(3);
            ShowProposal proposal3 = new ShowProposal(proposalId3, request3);
            proposalRepo.save(proposal3);
            request3.markProposalCreated();
            showRequestRepo.save(request3);
            System.out.println("✅ ShowProposal 3 criada com sucesso.");

            // === ShowRequest 4 ===
            ShowRequestID requestId4 = new ShowRequestID(4);
            Date eventDate4 = Date.from(LocalDate.now().plusDays(40));
            ShowRequest request4 = new ShowRequest(
                    requestId4, address, duration, description, submissionDate, eventDate4,
                    eventTime, customer, figureVersions, numDrones, designer, representative, author
            );
            showRequestRepo.save(request4);
            System.out.println("✅ ShowRequest 4 criada com sucesso.");

            ShowProposalID proposalId4 = new ShowProposalID(4);
            ShowProposal proposal4 = new ShowProposal(proposalId4, request4);
            proposalRepo.save(proposal4);
            request4.markProposalCreated();
            showRequestRepo.save(request4);
            System.out.println("✅ ShowProposal 4 criada com sucesso.");

            // === ShowRequest 5 ===
            ShowRequestID requestId5 = new ShowRequestID(5);
            Date eventDate5 = Date.from(LocalDate.now().plusDays(10));
            ShowRequest request5 = new ShowRequest(
                    requestId5, address, duration, description, submissionDate, eventDate5,
                    eventTime, customer, figureVersions, numDrones, designer, representative, author
            );
            showRequestRepo.save(request5);
            System.out.println("✅ ShowRequest 5 criada com sucesso.");
            ShowProposalID proposalId5 = new ShowProposalID(5);
            ShowProposal proposal5 = new ShowProposal(proposalId5, request5);
            proposal5.addDronesOfModel(drone, 1);
            Map<String, String> mapping5 = new HashMap<>();
            mapping5.put("LED", "DroneModel2");
            proposal5.addFigureToShow(new FigureVersionID(1), mapping5);
            proposal5.setVideoLink("https://server.local/simulations/proposal5.mp4");
            proposal5.markAsReadyToTest();
            proposal5.assignTemplate(proposal5.identity().toString());
            proposalRepo.save(proposal5);
            request5.markProposalCreated();
            showRequestRepo.save(request5);
            System.out.println("✅ ShowProposal 5 criada com sucesso.");

            return true;

        } catch (Exception e) {
            System.out.println("⚠ Erro ao criar ShowProposal: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}