
package com.javapersistence.part09.repositories.onetomany.list;

import com.javapersistence.part09.onetomany.list.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidRepository extends JpaRepository<Bid, Long> {
}
