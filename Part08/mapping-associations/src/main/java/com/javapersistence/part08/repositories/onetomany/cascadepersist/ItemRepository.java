
package com.javapersistence.part08.repositories.onetomany.cascadepersist;

import com.javapersistence.part08.onetomany.cascadepersist.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
