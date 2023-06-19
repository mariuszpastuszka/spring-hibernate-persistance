
package com.javapersistence.part09.repositories.manytomany.linkedentity;

import com.javapersistence.part09.manytomany.linkedentity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
