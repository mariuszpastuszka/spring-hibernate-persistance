
package com.javapersistence.part09.repositories.manytomany.linkedentity;

import com.javapersistence.part09.manytomany.linkedentity.CategorizedItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorizedItemRepository extends JpaRepository<CategorizedItem, CategorizedItem.Id> {
}
