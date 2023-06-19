
package com.javapersistence.part09.repositories.onetomany.embeddable;

import com.javapersistence.part09.onetomany.embeddable.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
