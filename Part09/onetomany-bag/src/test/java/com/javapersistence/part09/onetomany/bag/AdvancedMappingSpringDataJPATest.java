
package com.javapersistence.part09.onetomany.bag;

import com.javapersistence.part09.configuration.onetomany.bag.SpringDataConfiguration;
import com.javapersistence.part09.repositories.onetomany.bag.BidRepository;
import com.javapersistence.part09.repositories.onetomany.bag.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringDataConfiguration.class})
public class AdvancedMappingSpringDataJPATest {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BidRepository bidRepository;

    @Test
    void testStoreLoadEntities() {

        Item item = new Item("Foo");
        itemRepository.save(item);

        Bid someBid = new Bid(new BigDecimal("123.00"), item);
        item.addBid(someBid);
        item.addBid(someBid);
        bidRepository.save(someBid);

        Item item2 = itemRepository.findItemWithBids(item.getId());

        assertAll(
                () -> assertEquals(2, item.getBids().size()),
                () -> assertEquals(1, item2.getBids().size())
        );

        Bid bid = new Bid(new BigDecimal("456.00"), item);
        item.addBid(bid); // No SELECT!
        bidRepository.save(bid);

        Item item3 = itemRepository.findItemWithBids(item.getId());

        assertEquals(2, item3.getBids().size());

    }
}
