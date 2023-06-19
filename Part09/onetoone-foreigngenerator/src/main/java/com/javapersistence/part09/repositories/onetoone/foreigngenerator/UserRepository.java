
package com.javapersistence.part09.repositories.onetoone.foreigngenerator;

import com.javapersistence.part09.onetoone.foreigngenerator.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
