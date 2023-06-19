
package com.javapersistence.part11.repositories;

import com.javapersistence.part11.concurrency.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Integer>, LogRepositoryCustom {
}
