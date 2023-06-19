
package com.javapersistence.part09.repositories.onetomany.jointable;

import com.javapersistence.part09.onetomany.jointable.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
