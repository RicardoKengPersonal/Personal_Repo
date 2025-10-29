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
package eapli.shodrone.infrastructure.bootstrapers;

import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.shodrone.infrastructure.bootstrapers.demo.BackofficeUsersBootstrapper;
import eapli.shodrone.infrastructure.persistence.PersistenceContext;
import eapli.shodrone.shodroneusermanagement.domain.PhoneNumber;
import eapli.shodrone.shodroneusermanagement.domain.ShodroneUser;
import eapli.shodrone.usermanagement.domain.Roles;
import eapli.shodrone.usermanagement.domain.UserBuilderHelper;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthenticationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.strings.util.Strings;
import eapli.framework.validations.Invariants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * eCafeteria Bootstrapping data. This class bootstraps Master/reference data of
 * the application.
 *
 * @author Paulo Gandra de Sousa
 */
@SuppressWarnings("squid:S106")
public class ShodroneBootstrapper implements Action {
	private static final Logger LOGGER = LogManager.getLogger(ShodroneBootstrapper.class);
	private final AuthorizationService authz = AuthzRegistry.authorizationService();
	private final AuthenticationService authenticationService = AuthzRegistry.authenticationService();
	private final UserRepository userRepository = PersistenceContext.repositories().users();

	@Override
	public boolean execute() {
		// declare bootstrap actions
		final Action[] actions = { new BackofficeUsersBootstrapper() };

		registerShowDesigner();
		registerAdmin();
		registerCrmCollaborator();
		registerCrmManager();
		registerDroneTech();
		registerCustomerRepresentative4();
		registerCustomerRepresentative5();
		registerCustomerRepresentative6();
		authenticateForBootstrapping();

		// execute all bootstrapping
		var ret = true;
		for (final Action boot : actions) {
			System.out.println("Bootstrapping " + nameOfEntity(boot) + "...");
			ret &= boot.execute();
		}
		return ret;
	}


	/**
	 * Register a power user directly in the persistence layer as we need to
	 * circumvent authorizations in the Application Layer.
	 */
	public boolean registerAdmin() {
		final var userBuilder = UserBuilderHelper.builder();
		userBuilder.withUsername("admin").withPassword("Password1").withName("joe", "admin")
				.withEmail("joe@shodrone.com").withRoles(Roles.ADMIN);
		final var newUser = userBuilder.build();

		SystemUser admin;
		try {
			admin = userRepository.save(newUser);
			assert admin != null;
			return true;
		} catch (ConcurrencyException | IntegrityViolationException e) {
			// ignoring exception. assuming it is just a primary key violation
			// due to the tentative of inserting a duplicated user
			LOGGER.warn("Assuming {} already exists (activate trace log for details)", newUser.username());
			LOGGER.trace("Assuming existing record", e);
			return false;
		}
	}
	/**
	 * Register system users with different roles
	 *
	 */
	public boolean registerCrmCollaborator() {
		final var userBuilder = UserBuilderHelper.builder();
		userBuilder.withUsername("crmcg")
				.withPassword("Password1")
				.withName("Carla","Ribeiro")
				.withEmail("crmc@shodrone.com")
				.withRoles(Roles.CRM_COLLABORATOR);
		final var newUser = userBuilder.build();

		try {
			SystemUser systemUser = userRepository.save(newUser);
			assert systemUser != null;

			final var phoneNumber = new PhoneNumber("931234567");
			final var shodroneUser = new ShodroneUser(systemUser, phoneNumber);
			PersistenceContext.repositories().shodroneUsers().save(shodroneUser);

			return true;
		} catch (ConcurrencyException | IntegrityViolationException e) {
			LOGGER.warn("Assuming {} already exists (activate trace log for details)", newUser.username());
			LOGGER.trace("Assuming existing record", e);
			return false;
		}
	}
	public boolean registerCrmManager() {
		final var userBuilder = UserBuilderHelper.builder();
		userBuilder.withUsername("Tiago23")
				.withPassword("Password2")
				.withName("Tiago", "Cruz")
				.withEmail("crmm@shodrone.com")
				.withRoles(Roles.CRM_MANAGER);
		final var newUser = userBuilder.build();

		try {
			SystemUser systemUser = userRepository.save(newUser);
			assert systemUser != null;

			final var phoneNumber = new PhoneNumber("982567432");
			final var shodroneUser = new ShodroneUser(systemUser, phoneNumber);
			PersistenceContext.repositories().shodroneUsers().save(shodroneUser);

			return true;
		} catch (ConcurrencyException | IntegrityViolationException e) {
			LOGGER.warn("Assuming {} already exists (activate trace log for details)", newUser.username());
			LOGGER.trace("Assuming existing record", e);
			return false;
		}
	}
	public boolean registerShowDesigner() {
		final var userBuilder = UserBuilderHelper.builder();
		userBuilder.withUsername("ana.sd")
				.withPassword("Password3")
				.withName("Ana", "Lopes")
				.withEmail("ana.lopes@shodrone.com")
				.withRoles(Roles.SHOW_DESIGNER);
		final var newUser = userBuilder.build();

		try {
			SystemUser systemUser = userRepository.save(newUser);
			assert systemUser != null;

			final var phoneNumber = new PhoneNumber("933221144");
			final var shodroneUser = new ShodroneUser(systemUser, phoneNumber);
			PersistenceContext.repositories().shodroneUsers().save(shodroneUser);

			return true;
		} catch (ConcurrencyException | IntegrityViolationException e) {
			LOGGER.warn("Assuming {} already exists (activate trace log for details)", newUser.username());
			LOGGER.trace("Assuming existing record", e);
			return false;
		}
	}

	public boolean registerDroneTech() {
		final var userBuilder = UserBuilderHelper.builder();
		userBuilder.withUsername("ricardo.dt")
				.withPassword("Password4")
				.withName("Ricardo", "Silva")
				.withEmail("ricardo.silva@shodrone.com")
				.withRoles(Roles.DRONE_TECH);
		final var newUser = userBuilder.build();

		try {
			SystemUser systemUser = userRepository.save(newUser);
			assert systemUser != null;

			final var phoneNumber = new PhoneNumber("934556677");
			final var shodroneUser = new ShodroneUser(systemUser, phoneNumber);
			PersistenceContext.repositories().shodroneUsers().save(shodroneUser);

			return true;
		} catch (ConcurrencyException | IntegrityViolationException e) {
			LOGGER.warn("Assuming {} already exists (activate trace log for details)", newUser.username());
			LOGGER.trace("Assuming existing record", e);
			return false;
		}
	}

	public boolean registerCustomerRepresentative4() {
		final var userBuilder = UserBuilderHelper.builder();
		userBuilder.withUsername("patricia.rep")
				.withPassword("Password5")
				.withName("Patrícia", "Gomes")
				.withEmail("patricia.gomes@gmail.com")
				.withRoles(Roles.CUSTOMER_REPRESENTATIVE);
		final var newUser = userBuilder.build();

		try {
			SystemUser systemUser = userRepository.save(newUser);
			assert systemUser != null;

			final var phoneNumber = new PhoneNumber("936000004");
			final var shodroneUser = new ShodroneUser(systemUser, phoneNumber);
			PersistenceContext.repositories().shodroneUsers().save(shodroneUser);

			return true;
		} catch (ConcurrencyException | IntegrityViolationException e) {
			LOGGER.warn("Assuming {} already exists (activate trace log for details)", newUser.username());
			LOGGER.trace("Assuming existing record", e);
			return false;
		}
	}

	public boolean registerCustomerRepresentative5() {
		final var userBuilder = UserBuilderHelper.builder();
		userBuilder.withUsername("jorge.rep")
				.withPassword("Password6")
				.withName("Jorge", "Almeida")
				.withEmail("jorge.almeida@empresa.pt")
				.withRoles(Roles.CUSTOMER_REPRESENTATIVE);
		final var newUser = userBuilder.build();

		try {
			SystemUser systemUser = userRepository.save(newUser);
			assert systemUser != null;

			final var phoneNumber = new PhoneNumber("936000005");
			final var shodroneUser = new ShodroneUser(systemUser, phoneNumber);
			PersistenceContext.repositories().shodroneUsers().save(shodroneUser);

			return true;
		} catch (ConcurrencyException | IntegrityViolationException e) {
			LOGGER.warn("Assuming {} already exists (activate trace log for details)", newUser.username());
			LOGGER.trace("Assuming existing record", e);
			return false;
		}
	}

	public boolean registerCustomerRepresentative6() {
		final var userBuilder = UserBuilderHelper.builder();
		userBuilder.withUsername("marco.re")
				.withPassword("Password7")
				.withName("Marco", "Teixeira")
				.withEmail("marco.txr@hotmail.com")
				.withRoles(Roles.CUSTOMER_REPRESENTATIVE);
		final var newUser = userBuilder.build();

		try {
			SystemUser systemUser = userRepository.save(newUser);
			assert systemUser != null;

			final var phoneNumber = new PhoneNumber("936000006");
			final var shodroneUser = new ShodroneUser(systemUser, phoneNumber);
			PersistenceContext.repositories().shodroneUsers().save(shodroneUser);

			return true;
		} catch (ConcurrencyException | IntegrityViolationException e) {
			LOGGER.warn("Assuming {} already exists (activate trace log for details)", newUser.username());
			LOGGER.trace("Assuming existing record", e);
			return false;
		}
	}

	/**
	 * authenticate a Admin to be able to register new users
	 *
	 */
	protected void authenticateForBootstrapping() {
		authenticationService.authenticate("admin", "Password1");
		Invariants.ensure(authz.hasSession());
	}

	private String nameOfEntity(final Action boot) {
		final var name = boot.getClass().getSimpleName();
		return Strings.left(name, name.length() - "Bootstrapper".length());
	}
}
