
package com.javapersistence.part09.repositories.onetomany.embeddablejointable;

import com.javapersistence.part09.onetomany.embeddablejointable.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
