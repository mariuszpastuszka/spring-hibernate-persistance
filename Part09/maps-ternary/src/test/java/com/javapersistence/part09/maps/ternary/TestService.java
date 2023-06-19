
package com.javapersistence.part09.maps.ternary;

import com.javapersistence.part09.repositories.maps.ternary.CategoryRepository;
import com.javapersistence.part09.repositories.maps.ternary.ItemRepository;
import com.javapersistence.part09.repositories.maps.ternary.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Service
public class TestService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

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

        someCategory.putItemAddedBy(someItem, someUser);
        someCategory.putItemAddedBy(otherItem, someUser);
        otherCategory.putItemAddedBy(someItem, someUser);

        Category category1 = categoryRepository.findById(someCategory.getId()).get();
        Category category2 = categoryRepository.findById(otherCategory.getId()).get();

        Item item1 = itemRepository.findById(someItem.getId()).get();

        User user = userRepository.findById(someUser.getId()).get();

        assertAll(
                () -> assertEquals(2, category1.getItemAddedBy().size()),
                () -> assertEquals(1, category2.getItemAddedBy().size()),
                () -> assertEquals(item1, category2.getItemAddedBy().keySet().iterator().next()),
                () -> assertEquals(user, category2.getItemAddedBy().values().iterator().next())
        );
    }


}
