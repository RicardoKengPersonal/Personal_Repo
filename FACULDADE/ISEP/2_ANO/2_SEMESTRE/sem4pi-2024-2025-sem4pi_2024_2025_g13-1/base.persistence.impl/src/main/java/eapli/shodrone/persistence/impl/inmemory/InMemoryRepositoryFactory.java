package eapli.shodrone.persistence.impl.inmemory;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.inmemory.InMemoryUserRepository;
import eapli.framework.infrastructure.pubsub.impl.simplepersistent.repositories.EventConsumptionRepository;
import eapli.framework.infrastructure.pubsub.impl.simplepersistent.repositories.EventRecordRepository;
import eapli.shodrone.customermanagement.repository.CustomerRepository;
import eapli.shodrone.dronemanagement.domain.MaintenanceType;
import eapli.shodrone.dronemanagement.repository.DroneModelRepository;
import eapli.shodrone.dronemanagement.repository.MaintenanceTypeRepository;
import eapli.shodrone.figuremanagement.repository.FigureCategoryRepository;
import eapli.shodrone.figuremanagement.repository.FigureRepository;
import eapli.shodrone.infrastructure.bootstrapers.ShodroneBootstrapper;
import eapli.shodrone.infrastructure.persistence.RepositoryFactory;
import eapli.shodrone.shodroneusermanagement.repositories.ShodroneUserRepository;
import eapli.shodrone.shodroneusermanagement.repositories.SignupRequestRepository;
import eapli.shodrone.showmanagement.repository.ShowRepository;
import eapli.shodrone.showproposalmanagement.repository.ShowProposalRepository;
import eapli.shodrone.showrequestmanagement.repository.ShowRequestRepository;
import eapli.shodrone.usermanagement.domain.Roles;
import eapli.shodrone.usermanagement.domain.UserBuilderHelper;
import eapli.shodrone.dronemanagement.repository.DroneRepository;

public class InMemoryRepositoryFactory implements RepositoryFactory {

    @Override
    public TransactionalContext newTransactionalContext() {
        return null;
    }

    @Override
    public UserRepository users(final TransactionalContext tx) {
        final var repo = new InMemoryUserRepository();
        final var userBuilder = UserBuilderHelper.builder();
        userBuilder.withUsername("admin").withPassword("Password1").withName("joe", "admin")
                .withEmail("joe@shodrone.com").withRoles(Roles.ADMIN);
        final var newUser = userBuilder.build();
        repo.save(newUser);
        return repo;
    }

    @Override
    public UserRepository users() {
        return users(null);
    }

    @Override
    public ShodroneUserRepository shodroneUsers(final TransactionalContext tx) {

        return new InMemoryShodroneUserRepository();
    }

    @Override
    public ShodroneUserRepository shodroneUsers() {
        return shodroneUsers(null);
    }

    @Override
    public SignupRequestRepository signupRequests() {
        return signupRequests(null);
    }

    @Override
    public SignupRequestRepository signupRequests(final TransactionalContext tx) {
        return new InMemorySignupRequestRepository();
    }

    @Override
    public FigureCategoryRepository figureCategory() {
        return new InMemoryFigureCategoryRepository();
    }

    @Override
    public FigureCategoryRepository figureCategory(TransactionalContext autoTx) {
        throw new UnsupportedOperationException("Transactions are not supported in in-memory mode.");
    }

    @Override
    public CustomerRepository customerRepository() { return new InMemoryCustomerRepository();}

    @Override
    public CustomerRepository customerRepository(TransactionalContext autoTx) {
        throw new UnsupportedOperationException("Transactions are not supported in in-memory mode.");
    }

        @Override
    public FigureRepository figure() {
        return new InMemoryFigureRepository();
    }

    @Override
    public FigureRepository figure(TransactionalContext autoTx) {
        throw new UnsupportedOperationException("Transactions are not supported in in-memory mode.");
    }

    @Override
    public ShowRequestRepository showRequestRepository() {
        return new InMemoryShowRequestRepository();
    }

    @Override
    public ShowRequestRepository showRequestRepository(TransactionalContext autoTx) {
        throw new UnsupportedOperationException("Transactions are not supported in in-memory mode.");
    }
    @Override
    public ShowProposalRepository showProposalRepository() {
        return new InMemoryShowProposalRepository();
    }

    @Override
    public ShowProposalRepository showProposalRepository(TransactionalContext autoTx) {
        throw new UnsupportedOperationException("Transactions are not supported in in-memory mode.");
    }

    @Override
    public DroneModelRepository droneModel() {
        return new InMemoryDroneModelRepository();
    }

    @Override
    public DroneModelRepository droneModel(TransactionalContext autoTx) {
        throw new UnsupportedOperationException("Transactions are not supported in in-memory mode.");
    }

    @Override
    public DroneRepository drone() {
        return new InMemoryDroneRepository();
    }

    @Override
    public DroneRepository drone(TransactionalContext autoTx) {
        throw new UnsupportedOperationException("Transactions are not supported in in-memory mode.");
    }


    @Override
    public ShowRepository showRepository() {
        return new InMemoryShowRepository();
    }

    @Override
    public ShowRepository showRepository(TransactionalContext autoTx) {
        throw new UnsupportedOperationException("Transactions are not supported in in-memory mode.");
    }


    @Override
    public MaintenanceTypeRepository maintenanceType() {
        return new InMemoryMaintenanceTypeRepository();
    }

    @Override
    public MaintenanceTypeRepository maintenanceType(TransactionalContext autoTx) {
        throw new UnsupportedOperationException("Transactions are not supported in in-memory mode.");
    }


    @Override
    public EventConsumptionRepository eventConsumption() {
        return null;
    }

    @Override
    public EventRecordRepository eventRecord() {
        return null;
    }

}
