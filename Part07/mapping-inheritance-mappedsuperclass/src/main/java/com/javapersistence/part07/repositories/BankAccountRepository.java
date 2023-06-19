
package com.javapersistence.part07.repositories;

import com.javapersistence.part07.model.BankAccount;

import java.util.List;

public interface BankAccountRepository extends BillingDetailsRepository<BankAccount, Long> {
    List<BankAccount> findBySwift(String swift);
}
