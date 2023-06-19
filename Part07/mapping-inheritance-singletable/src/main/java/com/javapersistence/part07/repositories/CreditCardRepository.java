
package com.javapersistence.part07.repositories;

import com.javapersistence.part07.model.CreditCard;

import java.util.List;

public interface CreditCardRepository extends BillingDetailsRepository<CreditCard, Long> {
    List<CreditCard> findByExpYear(String expYear);
}
