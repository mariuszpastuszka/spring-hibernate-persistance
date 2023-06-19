
package com.javapersistence.part03.metadataxmljpa.repositories;

import com.javapersistence.part03.metadataxmljpa.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {

}
