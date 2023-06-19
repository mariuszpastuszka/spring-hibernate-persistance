
package com.javapersistence.part08.sortedmapofstrings;

import com.javapersistence.part08.configuration.sortedmapofstrings.SpringDataConfiguration;
import com.javapersistence.part08.repositories.sortedmapofstrings.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringDataConfiguration.class})
public class MappingCollectionsSpringDataJPATest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void storeLoadEntities() {

        Item item = new Item("Foo");

        item.putImage("Background", "background.jpg");
        item.putImage("Foreground", "foreground.jpg");
        item.putImage("Landscape", "landscape.jpg");
        item.putImage("Portrait", "portrait.jpg");

        itemRepository.save(item);

        Item item2 = itemRepository.findItemWithImages(item.getId());

        List<Item> items2 = itemRepository.findAll();
        Set<String> images = itemRepository.findImagesNative(item.getId());

        assertAll(
                () -> assertEquals(4, item2.getImages().size()),
                () -> assertEquals(1, items2.size()),
                () -> assertEquals(4, images.size()),
                () -> assertEquals("Portrait", item2.getImages().firstKey()),
                () -> assertEquals("Background", item2.getImages().lastKey())
        );


    }
}
