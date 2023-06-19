
package com.javapersistence.part07.repositories;

import com.javapersistence.part07.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
