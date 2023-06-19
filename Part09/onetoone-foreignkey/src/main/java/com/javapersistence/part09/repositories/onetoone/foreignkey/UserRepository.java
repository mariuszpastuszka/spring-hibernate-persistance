
package com.javapersistence.part09.repositories.onetoone.foreignkey;

import com.javapersistence.part09.onetoone.foreignkey.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u inner join fetch u.shippingAddress where u.id = :id")
    User findUserWithAddress(@Param("id") Long id);
}
