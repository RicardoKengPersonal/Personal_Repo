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
package eapli.shodrone.infrastructure.persistence;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.pubsub.impl.simplepersistent.repositories.EventConsumptionRepository;
import eapli.framework.infrastructure.pubsub.impl.simplepersistent.repositories.EventRecordRepository;
import eapli.shodrone.dronemanagement.domain.MaintenanceType;
import eapli.shodrone.dronemanagement.repository.DroneModelRepository;
import eapli.shodrone.dronemanagement.repository.DroneRepository;
import eapli.shodrone.dronemanagement.repository.MaintenanceTypeRepository;
import eapli.shodrone.figuremanagement.repository.FigureCategoryRepository;
import eapli.shodrone.figuremanagement.repository.FigureRepository;
import eapli.shodrone.shodroneusermanagement.repositories.ShodroneUserRepository;
import eapli.shodrone.shodroneusermanagement.repositories.SignupRequestRepository;
import eapli.shodrone.customermanagement.repository.CustomerRepository;
import eapli.shodrone.showmanagement.repository.ShowRepository;
import eapli.shodrone.showproposalmanagement.repository.ShowProposalRepository;
import eapli.shodrone.showrequestmanagement.repository.ShowRequestRepository;

/**
 * Abstract factory of repository interfaces for the Shodrone system.
 */
public interface RepositoryFactory {

    EventConsumptionRepository eventConsumption();

    EventRecordRepository eventRecord();

    TransactionalContext newTransactionalContext();

    UserRepository users(TransactionalContext autoTx);

    UserRepository users();

    FigureCategoryRepository figureCategory();

    FigureCategoryRepository figureCategory(TransactionalContext autoTx);

    CustomerRepository customerRepository();

    CustomerRepository customerRepository(TransactionalContext autoTx);

    DroneModelRepository droneModel();

    DroneModelRepository droneModel(TransactionalContext autoTx);

    DroneRepository drone();

    DroneRepository drone(TransactionalContext autoTx);

    MaintenanceTypeRepository maintenanceType();

    MaintenanceTypeRepository maintenanceType(TransactionalContext autoTx);

    ShodroneUserRepository shodroneUsers(TransactionalContext autoTx);

    ShodroneUserRepository shodroneUsers();

    SignupRequestRepository signupRequests(TransactionalContext autoTx);

    SignupRequestRepository signupRequests();

    FigureRepository figure();

    FigureRepository figure(TransactionalContext autoTx);

    ShowRequestRepository showRequestRepository();

    ShowRequestRepository showRequestRepository(TransactionalContext autoTx);

    ShowProposalRepository showProposalRepository();

    ShowProposalRepository showProposalRepository(TransactionalContext autoTx);

    ShowRepository showRepository();

    ShowRepository showRepository(TransactionalContext autoTx);

}
