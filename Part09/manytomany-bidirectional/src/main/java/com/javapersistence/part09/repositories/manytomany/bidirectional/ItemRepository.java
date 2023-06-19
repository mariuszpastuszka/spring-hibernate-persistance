
package com.javapersistence.part09.repositories.manytomany.bidirectional;

import com.javapersistence.part09.manytomany.bidirectional.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
