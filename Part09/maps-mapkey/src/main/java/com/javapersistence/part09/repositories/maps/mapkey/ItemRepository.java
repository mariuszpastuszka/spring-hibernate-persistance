
package com.javapersistence.part09.repositories.maps.mapkey;

import com.javapersistence.part09.maps.mapkey.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
