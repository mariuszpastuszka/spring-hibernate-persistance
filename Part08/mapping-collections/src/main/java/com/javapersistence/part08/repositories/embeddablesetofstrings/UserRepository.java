
package com.javapersistence.part08.repositories.embeddablesetofstrings;

import com.javapersistence.part08.embeddablesetofstrings.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
