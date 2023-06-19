
package com.javapersistence.part05;

import com.javapersistence.part05.configuration.SpringDataConfiguration;
import com.javapersistence.part05.model.Bid;
import com.javapersistence.part05.model.Item;
import com.javapersistence.part05.model.ItemBidSummary;
import com.javapersistence.part05.repositories.BidRepository;
import com.javapersistence.part05.repositories.ItemBidSummaryRepository;
import com.javapersistence.part05.repositories.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringDataConfiguration.class})
public class ItemBidSummarySpringDataTest {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private ItemBidSummaryRepository itemBidSummaryRepository;

    @Test
    public void itemBidSummaryTest() {

        Item item = new Item();
        item.setName("Some Item");
        item.setAuctionEnd(Helper.tomorrow());

        Bid bid1 = new Bid(new BigDecimal(1000.0), item);
        Bid bid2 = new Bid(new BigDecimal(1100.0), item);

        itemRepository.save(item);
        bidRepository.save(bid1);
        bidRepository.save(bid2);

        Optional<ItemBidSummary> itemBidSummary = itemBidSummaryRepository.findById(1000L);

        assertAll(
                () -> assertEquals(1000, itemBidSummary.get().getItemId()),
                () -> assertEquals("Some Item", itemBidSummary.get().getName()),
                () -> assertEquals(2, itemBidSummary.get().getNumberOfBids())
        );

    }
}