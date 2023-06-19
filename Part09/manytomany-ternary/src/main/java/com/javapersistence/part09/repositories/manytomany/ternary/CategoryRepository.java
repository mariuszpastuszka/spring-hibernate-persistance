
package com.javapersistence.part09.repositories.manytomany.ternary;

import com.javapersistence.part09.manytomany.ternary.Category;
import com.javapersistence.part09.manytomany.ternary.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("select c from Category c join c.categorizedItems ci where ci.item = :itemParameter")
    List<Category> findCategoryWithCategorizedItems(@Param("itemParameter") Item itemParameter);
}
