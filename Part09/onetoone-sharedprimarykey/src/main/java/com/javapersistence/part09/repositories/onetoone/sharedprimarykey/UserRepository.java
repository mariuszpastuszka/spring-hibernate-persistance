
package com.javapersistence.part09.repositories.onetoone.sharedprimarykey;

import com.javapersistence.part09.onetoone.sharedprimarykey.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
