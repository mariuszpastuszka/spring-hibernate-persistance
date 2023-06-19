
package com.javapersistence.part09.repositories.manytomany.ternary;

import com.javapersistence.part09.manytomany.ternary.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
