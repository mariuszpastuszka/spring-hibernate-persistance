
package com.javapersistence.part08.mapofstringsembeddables;

import com.javapersistence.part08.configuration.mapofstringsembeddables.SpringDataConfiguration;
import com.javapersistence.part08.repositories.mapofstringsembeddables.ItemRepository;
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

        item.putImage("Background", new Image("background.jpg", 640, 480));
        item.putImage("Foreground", new Image("foreground.jpg", 640, 480));
        item.putImage("Landscape", new Image("landscape.jpg", 640, 480));
        item.putImage("Portrait", new Image("portrait.jpg", 480, 640));

        itemRepository.save(item);

        Item item2 = itemRepository.findItemWithImages(item.getId());

        List<Item> items2 = itemRepository.findAll();
        Set<String> images = itemRepository.findImagesNative(item.getId());

        assertAll(
                () -> assertEquals(4, item2.getImages().size()),
                () -> assertEquals(1, items2.size()),
                () -> assertEquals(4, images.size())
        );

    }
}
