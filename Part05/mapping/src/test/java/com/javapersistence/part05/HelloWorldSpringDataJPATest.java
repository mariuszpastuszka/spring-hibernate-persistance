
package com.javapersistence.part05;

import com.javapersistence.part05.configuration.SpringDataConfiguration;
import com.javapersistence.part05.model.Item;
import com.javapersistence.part05.repositories.ItemRepository;
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
public class HelloWorldSpringDataJPATest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void storeLoadItem() {

        Item item = new Item();
        item.setName("Some Item");
        item.setAuctionEnd(Helper.tomorrow());

        itemRepository.save(item);

        List<Item> items = (List<Item>) itemRepository.findAll();

        assertAll(
                () -> assertEquals(1, items.size()),
                () -> assertEquals("Some Item", items.get(0).getName())
        );

    }
}
