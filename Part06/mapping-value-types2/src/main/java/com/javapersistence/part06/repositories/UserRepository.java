
package com.javapersistence.part06.repositories;

import com.javapersistence.part06.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
