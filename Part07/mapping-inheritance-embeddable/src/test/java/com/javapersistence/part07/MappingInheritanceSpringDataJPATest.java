
package com.javapersistence.part07;

import com.javapersistence.part07.configuration.SpringDataConfiguration;
import com.javapersistence.part07.model.Dimensions;
import com.javapersistence.part07.model.Item;
import com.javapersistence.part07.model.Weight;
import com.javapersistence.part07.repositories.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringDataConfiguration.class})
public class MappingInheritanceSpringDataJPATest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void storeLoadEntities() {

        Item item = new Item("Item 1", Dimensions.centimeters(BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE), Weight.kilograms(BigDecimal.ONE));
        itemRepository.save(item);

        List<Item> items = itemRepository.findAll();

        assertAll(
                () -> assertEquals(1, items.size()),
                () -> assertEquals("Item 1", items.get(0).getName())
        );

    }

}
