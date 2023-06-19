
package com.javapersistence.part09.repositories.onetomany.bag;

import com.javapersistence.part09.onetomany.bag.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidRepository extends JpaRepository<Bid, Long> {
}
