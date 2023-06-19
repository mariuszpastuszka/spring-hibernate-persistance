
package com.javapersistence.part02.repositories;

import com.javapersistence.part02.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {

}
