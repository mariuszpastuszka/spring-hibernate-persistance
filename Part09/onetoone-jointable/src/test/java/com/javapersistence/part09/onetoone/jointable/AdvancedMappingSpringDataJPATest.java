
package com.javapersistence.part09.onetoone.jointable;

import com.javapersistence.part09.configuration.onetoone.jointable.SpringDataConfiguration;
import com.javapersistence.part09.repositories.onetoone.jointable.ItemRepository;
import com.javapersistence.part09.repositories.onetoone.jointable.ShipmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringDataConfiguration.class})
public class AdvancedMappingSpringDataJPATest {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void testStoreLoadEntities() {

        Shipment shipment = new Shipment();
        shipmentRepository.save(shipment);

        Item item = new Item("Foo");
        itemRepository.save(item);

        Shipment auctionShipment = new Shipment(item);
        shipmentRepository.save(auctionShipment);

        Item item2 = itemRepository.findById(item.getId()).get();
        Shipment shipment2 = shipmentRepository.findById(shipment.getId()).get();
        Shipment auctionShipment2 = shipmentRepository.findShipmentWithItem(auctionShipment.getId());

        assertAll(
                () -> assertNull(shipment2.getAuction()),
                () -> assertEquals(item2.getId(), auctionShipment2.getAuction().getId()),
                () -> assertEquals(item2.getName(), auctionShipment2.getAuction().getName())
        );

    }
}
