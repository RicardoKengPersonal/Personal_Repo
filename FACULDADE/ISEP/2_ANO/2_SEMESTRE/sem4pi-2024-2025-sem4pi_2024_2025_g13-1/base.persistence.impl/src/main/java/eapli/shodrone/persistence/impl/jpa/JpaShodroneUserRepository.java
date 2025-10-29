package eapli.shodrone.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import eapli.shodrone.Application;
import eapli.shodrone.shodroneusermanagement.domain.PhoneNumber;
import eapli.shodrone.shodroneusermanagement.domain.ShodroneUser;
import eapli.shodrone.shodroneusermanagement.domain.MecanographicNumber;
import eapli.shodrone.shodroneusermanagement.repositories.ShodroneUserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class JpaShodroneUserRepository extends JpaAutoTxRepository<ShodroneUser, PhoneNumber,PhoneNumber>
        implements ShodroneUserRepository {

    public JpaShodroneUserRepository(final TransactionalContext autoTx) {
        super(autoTx, "mecanographicNumber");
    }

    public JpaShodroneUserRepository(final String puname) {
        super(puname, Application.settings().extendedPersistenceProperties(), "mecanographicNumber");
    }

    @Override
    public Optional<ShodroneUser> findByUsername(final Username name) {
        final Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        return matchOne("e.systemUser.username=:name", params);
    }

    @Override
    public Optional<ShodroneUser> findByPhoneNumber(final PhoneNumber number) {
        final Map<String, Object> params = new HashMap<>();
        params.put("number", number);
        return matchOne("e.mecanographicNumber=:number", params);
    }

    @Override
    public Iterable<ShodroneUser> findAllActive() {
        return match("e.systemUser.active = true");
    }
}
