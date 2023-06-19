
package com.javapersistence.part08.embeddablesetofstrings;

import com.javapersistence.part08.configuration.embeddablesetofstrings.SpringDataConfiguration;
import com.javapersistence.part08.repositories.embeddablesetofstrings.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringDataConfiguration.class})
public class MappingCollectionsSpringDataJPATest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void storeLoadEntities() {
        User john = new User("john");
        Address address = new Address("Flowers Street", "01246", "Boston");
        address.addContact("John Smith");
        address.addContact("Jane Smith");
        john.setAddress(address);

        userRepository.save(john);

        List<User> users = userRepository.findAll();

        assertAll(
                () -> assertEquals(1, users.size()),
                () -> assertEquals("Flowers Street", users.get(0).getAddress().getStreet()),
                () -> assertEquals("01246", users.get(0).getAddress().getZipcode()),
                () -> assertEquals("Boston", users.get(0).getAddress().getCity()),
                () -> assertEquals(2, users.get(0).getAddress().getContacts().size())
        );

    }
}
