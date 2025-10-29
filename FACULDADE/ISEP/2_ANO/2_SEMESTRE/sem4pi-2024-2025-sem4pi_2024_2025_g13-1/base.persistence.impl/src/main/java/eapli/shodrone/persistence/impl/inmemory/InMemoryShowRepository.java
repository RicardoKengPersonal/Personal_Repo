package eapli.shodrone.persistence.impl.inmemory;


import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import eapli.shodrone.customermanagement.domain.VatNumber;
import eapli.shodrone.showmanagement.domain.Show;
import eapli.shodrone.showmanagement.domain.ShowID;
import eapli.shodrone.showmanagement.repository.ShowRepository;

import java.util.List;
import java.util.stream.Collectors;

public class InMemoryShowRepository extends InMemoryDomainRepository<Show, ShowID> implements ShowRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public List<Show> findByCustomerRepresentativeEmail(String email) {
        return (List<Show>) match(show ->
                show.representative().email().toString().equalsIgnoreCase(email)
        );
    }

    @Override
    public List<Show> findScheduledShows() {
        return (List<Show>) match(show ->
                show.status().name().equals("SCHEDULED")
        );
    }

    @Override
    public List<Show> findScheduledShowsByCustomer(String customerId) {
        return (List<Show>) match(show ->
                show.status().name().equals("SCHEDULED") &&
                        show.customer().vatNumber().number().equalsIgnoreCase(customerId)
        );
    }
}