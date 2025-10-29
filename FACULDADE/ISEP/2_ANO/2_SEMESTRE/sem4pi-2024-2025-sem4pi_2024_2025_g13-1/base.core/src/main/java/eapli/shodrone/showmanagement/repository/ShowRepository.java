package eapli.shodrone.showmanagement.repository;


import eapli.framework.domain.repositories.DomainRepository;
import eapli.shodrone.showmanagement.domain.Show;
import eapli.shodrone.showmanagement.domain.ShowID;

import java.util.List;

public interface ShowRepository extends DomainRepository<ShowID, Show> {

    List<Show> findByCustomerRepresentativeEmail(String email);

    List<Show> findScheduledShows();

    List<Show> findScheduledShowsByCustomer(String customerId);
}