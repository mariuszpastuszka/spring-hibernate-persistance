
package com.javapersistence.part09.maps.mapkey;

import com.javapersistence.part09.repositories.maps.mapkey.ItemRepository;
import com.javapersistence.part09.repositories.maps.mapkey.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Service
public class TestService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BidRepository bidRepository;

    @Transactional
    public void storeLoadEntities() {
        Item someItem = new Item("Some Item");
        itemRepository.save(someItem);

        Bid someBid = new Bid(new BigDecimal("123.00"), someItem);
        bidRepository.save(someBid);
        someItem.addBid(someBid.getId(), someBid);

        Bid secondBid = new Bid(new BigDecimal("456.00"), someItem);
        bidRepository.save(secondBid);
        someItem.addBid(secondBid.getId(), secondBid);

        Item item = itemRepository.findById(someItem.getId()).get();

        assertEquals(2, item.getBids().size());

        for (Map.Entry<Long, Bid> entry : item.getBids().entrySet()) {
            // The key is the identifier of each Bid
            assertEquals(entry.getKey(), entry.getValue().getId());
        }
    }


}
