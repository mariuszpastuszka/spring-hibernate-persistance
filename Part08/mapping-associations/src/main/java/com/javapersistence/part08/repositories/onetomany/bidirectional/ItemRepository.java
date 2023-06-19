
package com.javapersistence.part08.repositories.onetomany.bidirectional;

import com.javapersistence.part08.onetomany.bidirectional.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
