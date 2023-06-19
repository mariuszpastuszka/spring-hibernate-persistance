
package com.javapersistence.part09.manytomany.ternary;

import com.javapersistence.part09.repositories.manytomany.ternary.CategoryRepository;
import com.javapersistence.part09.repositories.manytomany.ternary.ItemRepository;
import com.javapersistence.part09.repositories.manytomany.ternary.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Service
public class TestService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

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

        User someUser = new User("John Smith");
        userRepository.save(someUser);

        CategorizedItem linkOne = new CategorizedItem(
                someUser, someItem
        );
        someCategory.addCategorizedItem(linkOne);

        CategorizedItem linkTwo = new CategorizedItem(
                someUser, otherItem
        );
        someCategory.addCategorizedItem(linkTwo);

        CategorizedItem linkThree = new CategorizedItem(
                someUser, someItem
        );
        otherCategory.addCategorizedItem(linkThree);

        Category category1 = categoryRepository.findById(someCategory.getId()).get();
        Category category2 = categoryRepository.findById(otherCategory.getId()).get();

        Item item1 = itemRepository.findById(someItem.getId()).get();
        User john = userRepository.findById(someUser.getId()).get();

        List<Category> categoriesOfItem = categoryRepository.findCategoryWithCategorizedItems(item1);

        assertAll(
                () -> assertEquals(2, category1.getCategorizedItems().size()),
                () -> assertEquals(1, category2.getCategorizedItems().size()),
                () -> assertEquals(item1, category2.getCategorizedItems().iterator().next().getItem()),
                () -> assertEquals(john, category2.getCategorizedItems().iterator().next().getAddedBy()),
                () -> assertEquals(2, categoriesOfItem.size())
        );
    }


}
