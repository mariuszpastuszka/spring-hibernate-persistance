
package com.javapersistence.part08.onetomany.ondeletecascade;

import com.javapersistence.part08.configuration.onetomany.ondeletecascade.SpringDataConfiguration;
import com.javapersistence.part08.repositories.onetomany.ondeletecascade.BidRepository;
import com.javapersistence.part08.repositories.onetomany.ondeletecascade.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringDataConfiguration.class})
public class MappingAssociationsSpringDataJPATest {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BidRepository bidRepository;

    @Test
    void storeLoadEntities() {

        Item item = new Item("Foo");

        Bid bid = new Bid(BigDecimal.valueOf(100), item);
        Bid bid2 = new Bid(BigDecimal.valueOf(200), item);
        item.addBid(bid);
        item.addBid(bid2);

        itemRepository.save(item);

        List<Item> items = itemRepository.findAll();
        Set<Bid> bids = bidRepository.findByItem(item);

        assertAll(
                () -> assertEquals(1, items.size()),
                () -> assertEquals(2, bids.size())
        );

        Item item1 = itemRepository.findById(item.getId()).get();

        itemRepository.delete(item1);

        List<Item> items2 = itemRepository.findAll();
        List<Bid> bids2 = bidRepository.findAll();

        assertAll(
                () -> assertEquals(0, items2.size()),
                () -> assertEquals(0, bids2.size())
        );
    }
}
