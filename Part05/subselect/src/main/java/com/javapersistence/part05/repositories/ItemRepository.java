
package com.javapersistence.part05.repositories;

import com.javapersistence.part05.model.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {

}
