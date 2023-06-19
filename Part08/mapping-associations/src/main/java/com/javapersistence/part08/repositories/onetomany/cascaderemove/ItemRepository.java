
package com.javapersistence.part08.repositories.onetomany.cascaderemove;

import com.javapersistence.part08.onetomany.cascaderemove.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
