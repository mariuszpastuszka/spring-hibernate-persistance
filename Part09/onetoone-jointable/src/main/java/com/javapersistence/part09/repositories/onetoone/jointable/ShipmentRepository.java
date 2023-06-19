
package com.javapersistence.part09.repositories.onetoone.jointable;

import com.javapersistence.part09.onetoone.jointable.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    @Query("select s from Shipment s inner join fetch s.auction where s.id = :id")
    Shipment findShipmentWithItem(@Param("id") Long id);
}
