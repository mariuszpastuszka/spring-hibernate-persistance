
package com.javapersistence.part09.onetomany.embeddable;

import com.javapersistence.part09.repositories.onetomany.embeddable.ShipmentRepository;
import com.javapersistence.part09.repositories.onetomany.embeddable.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Service
public class TestService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Transactional
    public void storeLoadEntities() {
        User user = new User("John Smith");
        Address deliveryAddress = new Address("Flowers Street", "01246", "Boston");
        user.setShippingAddress(deliveryAddress);
        userRepository.save(user);

        Shipment firstShipment = new Shipment();
        deliveryAddress.addDelivery(firstShipment);
        shipmentRepository.save(firstShipment);

        Shipment secondShipment = new Shipment();
        deliveryAddress.addDelivery(secondShipment);
        shipmentRepository.save(secondShipment);

        User johnsmith = userRepository.findById(user.getId()).get();

        assertEquals(2, johnsmith.getShippingAddress().getDeliveries().size());
    }
}
