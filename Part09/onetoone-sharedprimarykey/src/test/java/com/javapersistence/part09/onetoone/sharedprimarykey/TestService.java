
package com.javapersistence.part09.onetoone.sharedprimarykey;

import com.javapersistence.part09.repositories.onetoone.sharedprimarykey.AddressRepository;
import com.javapersistence.part09.repositories.onetoone.sharedprimarykey.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Service
public class TestService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Transactional
    public void storeLoadEntities() {

        Address address =
                new Address("Flowers Street", "01246", "Boston");
        addressRepository.save(address);

        User john = new User(address.getId(), // Assign same identifier value
                "John Smith"
        );
        john.setShippingAddress(address);
        userRepository.save(john);

        User user = userRepository.findById(john.getId()).get();
        Address address2 = addressRepository.findById(address.getId()).get();

        assertAll(
                () -> assertEquals("Flowers Street", user.getShippingAddress().getStreet()),
                () -> assertEquals("01246", user.getShippingAddress().getZipcode()),
                () -> assertEquals("Boston", user.getShippingAddress().getCity()),
                () -> assertEquals("Flowers Street", address2.getStreet()),
                () -> assertEquals("01246", address2.getZipcode()),
                () -> assertEquals("Boston", address2.getCity())
        );

    }
}
