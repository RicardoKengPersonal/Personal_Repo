package eapli.shodrone.persistence.impl.jpa;

import eapli.shodrone.Application;
import eapli.shodrone.customermanagement.repository.CustomerRepository;
import eapli.shodrone.dronemanagement.repository.DroneModelRepository;
import eapli.shodrone.dronemanagement.repository.DroneRepository;
import eapli.shodrone.dronemanagement.repository.MaintenanceTypeRepository;
import eapli.shodrone.figuremanagement.repository.FigureCategoryRepository;
import eapli.shodrone.figuremanagement.repository.FigureRepository;
import eapli.shodrone.infrastructure.persistence.RepositoryFactory;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.jpa.JpaAutoTxUserRepository;
import eapli.framework.infrastructure.pubsub.impl.simplepersistent.repositories.EventConsumptionRepository;
import eapli.framework.infrastructure.pubsub.impl.simplepersistent.repositories.EventRecordRepository;
import eapli.framework.infrastructure.pubsub.impl.simplepersistent.repositories.jpa.JpaAutoTxEventConsumptionRepository;
import eapli.framework.infrastructure.pubsub.impl.simplepersistent.repositories.jpa.JpaAutoTxEventRecordRepository;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import eapli.shodrone.shodroneusermanagement.repositories.SignupRequestRepository;
import eapli.shodrone.showmanagement.repository.ShowRepository;
import eapli.shodrone.showproposalmanagement.repository.ShowProposalRepository;
import eapli.shodrone.showrequestmanagement.repository.ShowRequestRepository;

public class JpaRepositoryFactory implements RepositoryFactory {

	@Override
	public TransactionalContext newTransactionalContext() {
		return JpaAutoTxRepository.buildTransactionalContext(
				Application.settings().persistenceUnitName(),
				Application.settings().extendedPersistenceProperties());
	}

	@Override
	public UserRepository users(final TransactionalContext autoTx) {
		return new JpaAutoTxUserRepository(autoTx);
	}

	@Override
	public UserRepository users() {
		return new JpaAutoTxUserRepository(
				Application.settings().persistenceUnitName(),
				Application.settings().extendedPersistenceProperties());
	}

	@Override
	public FigureCategoryRepository figureCategory() {
		return new JpaFigureCategoryRepository(Application.settings().persistenceUnitName());
	}

	@Override
	public FigureCategoryRepository figureCategory(final TransactionalContext autoTx) {
		return new JpaFigureCategoryRepository(autoTx);
	}

	@Override
	public FigureRepository figure() {
		return new JpaFigureRepository(Application.settings().persistenceUnitName());
	}

	@Override
	public FigureRepository figure(TransactionalContext autoTx) {
		return new JpaFigureRepository(autoTx);
	}

	@Override
	public JpaShodroneUserRepository shodroneUsers(final TransactionalContext autoTx) {
		return new JpaShodroneUserRepository(autoTx);
	}

	@Override
	public JpaShodroneUserRepository shodroneUsers() {
		return new JpaShodroneUserRepository(Application.settings().persistenceUnitName());
	}

	@Override
	public SignupRequestRepository signupRequests(final TransactionalContext autoTx) {
		return new JpaSignupRequestRepository(autoTx);
	}

	@Override
	public SignupRequestRepository signupRequests() {
		return new JpaSignupRequestRepository(Application.settings().persistenceUnitName());
	}

	@Override
	public DroneModelRepository droneModel() {
		return new JpaDroneModelRepository(Application.settings().persistenceUnitName());
	}

	@Override
	public DroneModelRepository droneModel (final TransactionalContext autoTx) {
		return new JpaDroneModelRepository(autoTx);
	}

	@Override
	public ShowRepository showRepository() {
		return new JpaShowRepository(Application.settings().persistenceUnitName());
	}

	@Override
	public ShowRepository showRepository (final TransactionalContext autoTx) {
		return new JpaShowRepository(autoTx);
	}

	@Override
	public DroneRepository drone() {
		return new JpaDroneRepository(Application.settings().persistenceUnitName());
	}

	@Override
	public DroneRepository drone (final TransactionalContext autoTx) {
		return new JpaDroneRepository(autoTx);
	}

	@Override
	public MaintenanceTypeRepository maintenanceType() {
		return new JpaMaintenanceTypeRepository(Application.settings().persistenceUnitName());
	}

	@Override
	public MaintenanceTypeRepository maintenanceType (final TransactionalContext autoTx) {
		return new JpaMaintenanceTypeRepository(autoTx);
	}

	@Override
	public CustomerRepository customerRepository() {
		return new JpaCustomerRepository(Application.settings().persistenceUnitName());
	}
	@Override
	public CustomerRepository customerRepository(final TransactionalContext autoTx) {
		return new JpaCustomerRepository(autoTx);
	}

	@Override
	public ShowRequestRepository showRequestRepository() {
		return new JpaShowRequestRepository(Application.settings().persistenceUnitName());
	}

	@Override
	public ShowRequestRepository showRequestRepository(final TransactionalContext autoTx) {
		return new JpaShowRequestRepository(autoTx);
	}
	@Override
	public ShowProposalRepository showProposalRepository() {
		return new JpaShowProposalRepository(Application.settings().persistenceUnitName());
	}

	@Override
	public ShowProposalRepository showProposalRepository(final TransactionalContext autoTx) {
		return new JpaShowProposalRepository(autoTx);
	}

	@Override
	public EventConsumptionRepository eventConsumption() {
		return new JpaAutoTxEventConsumptionRepository(
				Application.settings().persistenceUnitName(),
				Application.settings().extendedPersistenceProperties());
	}

	@Override
	public EventRecordRepository eventRecord() {
		return new JpaAutoTxEventRecordRepository(
				Application.settings().persistenceUnitName(),
				Application.settings().extendedPersistenceProperties());
	}

}
