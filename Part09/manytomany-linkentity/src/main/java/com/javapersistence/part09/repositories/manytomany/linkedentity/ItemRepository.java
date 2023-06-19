
package com.javapersistence.part09.repositories.manytomany.linkedentity;

import com.javapersistence.part09.manytomany.linkedentity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
