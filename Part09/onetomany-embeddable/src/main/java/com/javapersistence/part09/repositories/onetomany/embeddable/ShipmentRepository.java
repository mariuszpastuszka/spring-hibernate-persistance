
package com.javapersistence.part09.repositories.onetomany.embeddable;

import com.javapersistence.part09.onetomany.embeddable.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
}
