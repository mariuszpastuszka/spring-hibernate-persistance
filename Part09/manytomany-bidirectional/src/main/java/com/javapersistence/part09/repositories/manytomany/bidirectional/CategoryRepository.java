
package com.javapersistence.part09.repositories.manytomany.bidirectional;

import com.javapersistence.part09.manytomany.bidirectional.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
