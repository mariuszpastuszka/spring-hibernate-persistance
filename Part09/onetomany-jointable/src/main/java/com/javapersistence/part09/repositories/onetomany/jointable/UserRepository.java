
package com.javapersistence.part09.repositories.onetomany.jointable;

import com.javapersistence.part09.onetomany.jointable.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
