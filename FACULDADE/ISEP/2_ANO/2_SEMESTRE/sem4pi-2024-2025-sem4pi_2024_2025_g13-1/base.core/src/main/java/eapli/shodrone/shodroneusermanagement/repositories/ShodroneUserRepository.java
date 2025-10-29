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
package eapli.shodrone.shodroneusermanagement.repositories;

import eapli.shodrone.shodroneusermanagement.domain.PhoneNumber;
import eapli.shodrone.shodroneusermanagement.domain.ShodroneUser;
import eapli.shodrone.shodroneusermanagement.domain.MecanographicNumber;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.shodrone.shodroneusermanagement.domain.ShodroneUser;

import java.util.Optional;

/**
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
public interface ShodroneUserRepository extends DomainRepository<PhoneNumber, ShodroneUser> {

    /**
     * Returns the cafeteria user (utente) whose username is given.
     *
     * @param name
     *            the username to search for
     * @return
     */
    Optional<ShodroneUser> findByUsername(Username name);

    /**
     * Returns the cafeteria user (utente) with the given mecanographic number.
     *
     * @param number
     * @return
     */
    default Optional<ShodroneUser> findByPhoneNumber(
            final PhoneNumber number) {
        return ofIdentity(number);
    }

    /**
     *
     * @return
     */
    Iterable<ShodroneUser> findAllActive();
}
