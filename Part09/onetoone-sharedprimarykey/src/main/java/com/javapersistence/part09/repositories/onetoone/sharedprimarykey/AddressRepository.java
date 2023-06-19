
package com.javapersistence.part09.repositories.onetoone.sharedprimarykey;

import com.javapersistence.part09.onetoone.sharedprimarykey.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
