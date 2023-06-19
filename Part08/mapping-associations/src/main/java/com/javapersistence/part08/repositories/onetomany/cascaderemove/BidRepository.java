
package com.javapersistence.part08.repositories.onetomany.cascaderemove;

import com.javapersistence.part08.onetomany.cascaderemove.Bid;
import com.javapersistence.part08.onetomany.cascaderemove.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface BidRepository extends JpaRepository<Bid, Long> {
    Set<Bid> findByItem(Item item);
}
