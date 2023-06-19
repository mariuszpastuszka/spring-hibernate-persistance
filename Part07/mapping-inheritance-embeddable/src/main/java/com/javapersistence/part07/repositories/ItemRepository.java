
package com.javapersistence.part07.repositories;

import com.javapersistence.part07.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
