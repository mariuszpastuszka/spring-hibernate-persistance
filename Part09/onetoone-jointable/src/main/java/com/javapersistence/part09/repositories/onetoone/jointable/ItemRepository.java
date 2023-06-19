
package com.javapersistence.part09.repositories.onetoone.jointable;

import com.javapersistence.part09.onetoone.jointable.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
