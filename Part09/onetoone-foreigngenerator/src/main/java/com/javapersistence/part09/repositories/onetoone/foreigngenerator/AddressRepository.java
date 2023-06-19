
package com.javapersistence.part09.repositories.onetoone.foreigngenerator;

import com.javapersistence.part09.onetoone.foreigngenerator.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
