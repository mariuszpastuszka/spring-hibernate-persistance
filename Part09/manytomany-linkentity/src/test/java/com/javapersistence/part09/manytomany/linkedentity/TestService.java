
package com.javapersistence.part09.manytomany.linkedentity;

import com.javapersistence.part09.repositories.manytomany.linkedentity.CategorizedItemRepository;
import com.javapersistence.part09.repositories.manytomany.linkedentity.CategoryRepository;
import com.javapersistence.part09.repositories.manytomany.linkedentity.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Service
public class TestService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategorizedItemRepository categorizedItemRepository;

    @Transactional
    public void storeLoadEntities() {
        Category someCategory = new Category("Some Category");
        Category otherCategory = new Category("Other Category");

        categoryRepository.save(someCategory);
        categoryRepository.save(otherCategory);

        Item someItem = new Item("Some Item");
        Item otherItem = new Item("Other Item");

        itemRepository.save(someItem);
        itemRepository.save(otherItem);

        CategorizedItem linkOne = new CategorizedItem(
                "John Smith", someCategory, someItem
        );

        CategorizedItem linkTwo = new CategorizedItem(
                "John Smith", someCategory, otherItem
        );

        CategorizedItem linkThree = new CategorizedItem(
                "John Smith", otherCategory, someItem
        );

        categorizedItemRepository.save(linkOne);
        categorizedItemRepository.save(linkTwo);
        categorizedItemRepository.save(linkThree);

        Category category1 = categoryRepository.findById(someCategory.getId()).get();
        Category category2 = categoryRepository.findById(otherCategory.getId()).get();

        Item item1 = itemRepository.findById(someItem.getId()).get();
        Item item2 = itemRepository.findById(otherItem.getId()).get();

        assertAll(
                () -> assertEquals(2, category1.getCategorizedItems().size()),
                () -> assertEquals(2, item1.getCategorizedItems().size()),
                () -> assertEquals(1, category2.getCategorizedItems().size()),
                () -> assertEquals(1, item2.getCategorizedItems().size()),
                () -> assertEquals(item1, category2.getCategorizedItems().iterator().next().getItem()),
                () -> assertEquals(category1, item2.getCategorizedItems().iterator().next().getCategory())
        );
    }

}
