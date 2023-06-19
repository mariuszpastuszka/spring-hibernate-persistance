
package com.javapersistence.part09.onetomany.jointable;

import com.javapersistence.part09.repositories.onetomany.jointable.ItemRepository;
import com.javapersistence.part09.repositories.onetomany.jointable.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

@Service
public class TestService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void storeLoadEntities() {

        Item someItem = new Item("Some Item");
        itemRepository.save(someItem);
        Item otherItem = new Item("Other Item");
        itemRepository.save(otherItem);

        User someUser = new User("John Smith");
        someUser.getBoughtItems().add(someItem); // Link
        someItem.setBuyer(someUser); // Link
        someUser.getBoughtItems().add(otherItem);
        otherItem.setBuyer(someUser);
        userRepository.save(someUser);

        Item unsoldItem = new Item("Unsold Item");
        itemRepository.save(unsoldItem);

        Item item = itemRepository.findById(someItem.getId()).get();
        Item item2 = itemRepository.findById(unsoldItem.getId()).get();

        assertAll(
                () -> assertEquals("John Smith", item.getBuyer().getUsername()),
                () -> assertTrue(item.getBuyer().getBoughtItems().contains(item)),
                () -> assertNull(item2.getBuyer())
        );

    }
}
