
package com.javapersistence.part08.onetomany.bidirectional;

import com.javapersistence.part08.configuration.onetomany.bidirectional.SpringDataConfiguration;
import com.javapersistence.part08.repositories.onetomany.bidirectional.BidRepository;
import com.javapersistence.part08.repositories.onetomany.bidirectional.ItemRepository;
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

        itemRepository.save(item);
        item.addBid(bid);
        item.addBid(bid2);
        bidRepository.save(bid);
        bidRepository.save(bid2);

        List<Item> items = itemRepository.findAll();
        Set<Bid> bids = bidRepository.findByItem(item);

        assertAll(
                () -> assertEquals(1, items.size()),
                () -> assertEquals(2, bids.size())
        );

    }
}
