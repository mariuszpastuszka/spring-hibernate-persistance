
package com.javapersistence.part09.onetomany.list;

import com.javapersistence.part09.repositories.onetomany.list.BidRepository;
import com.javapersistence.part09.repositories.onetomany.list.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Service
public class TestService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BidRepository bidRepository;

    @Transactional
    public void storeLoadEntities() {

        Item item = new Item("Foo");
        itemRepository.save(item);

        Bid someBid = new Bid(new BigDecimal("123.00"), item);
        item.addBid(someBid);
        bidRepository.save(someBid);

        Bid secondBid = new Bid(new BigDecimal("456.00"), item);
        item.addBid(secondBid);
        bidRepository.save(secondBid);

        Item item2 = itemRepository.findItemWithBids(item.getId());

        assertAll(
                () -> assertEquals(2, item2.getBids().size()),
                () -> assertEquals(0, item2.getBids().get(0).getAmount().compareTo(new BigDecimal("123"))),
                () -> assertEquals(0, item2.getBids().get(1).getAmount().compareTo(new BigDecimal("456")))
        );

    }
}
