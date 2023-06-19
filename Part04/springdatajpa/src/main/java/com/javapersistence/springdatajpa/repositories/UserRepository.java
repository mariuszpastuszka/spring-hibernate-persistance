
package com.javapersistence.springdatajpa.repositories;

import com.javapersistence.springdatajpa.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
