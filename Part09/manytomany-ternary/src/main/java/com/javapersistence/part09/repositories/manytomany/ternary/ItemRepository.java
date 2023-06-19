
package com.javapersistence.part09.repositories.manytomany.ternary;

import com.javapersistence.part09.manytomany.ternary.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
