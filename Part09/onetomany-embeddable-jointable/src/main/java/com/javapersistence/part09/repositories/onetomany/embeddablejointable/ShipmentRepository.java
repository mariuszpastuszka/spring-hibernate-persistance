
package com.javapersistence.part09.repositories.onetomany.embeddablejointable;

import com.javapersistence.part09.onetomany.embeddablejointable.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
}
