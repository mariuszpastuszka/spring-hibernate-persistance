
package com.javapersistence.part09.repositories.onetoone.foreignkey;

import com.javapersistence.part09.onetoone.foreignkey.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
