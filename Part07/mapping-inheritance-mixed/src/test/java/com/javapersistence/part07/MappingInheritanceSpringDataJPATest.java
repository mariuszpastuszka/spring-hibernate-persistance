
package com.javapersistence.part07;

import com.javapersistence.part07.configuration.SpringDataConfiguration;
import com.javapersistence.part07.model.BankAccount;
import com.javapersistence.part07.model.CreditCard;
import com.javapersistence.part07.repositories.BankAccountRepository;
import com.javapersistence.part07.repositories.CreditCardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringDataConfiguration.class})
public class MappingInheritanceSpringDataJPATest {

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Test
    void storeLoadEntities() {

        CreditCard creditCard = new CreditCard("John Smith", "123456789", "10", "2030");
        creditCardRepository.save(creditCard);

        BankAccount bankAccount = new BankAccount("Mike Johnson", "12345", "Delta Bank", "BANKXY12");
        bankAccountRepository.save(bankAccount);

        List<CreditCard> creditCards = creditCardRepository.findByOwner("John Smith");
        List<BankAccount> bankAccounts = bankAccountRepository.findByOwner("Mike Johnson");
        List<CreditCard> creditCards2 = creditCardRepository.findByCardNumber("123456789");
        List<BankAccount> bankAccounts2 = bankAccountRepository.findBySwift("BANKXY12");

        assertAll(
                () -> assertEquals(1, creditCards.size()),
                () -> assertEquals("123456789", creditCards.get(0).getCardNumber()),
                () -> assertEquals(1, bankAccounts.size()),
                () -> assertEquals("12345", bankAccounts.get(0).getAccount()),
                () -> assertEquals(1, creditCards2.size()),
                () -> assertEquals("John Smith", creditCards2.get(0).getOwner()),
                () -> assertEquals(1, bankAccounts2.size()),
                () -> assertEquals("Mike Johnson", bankAccounts2.get(0).getOwner())
        );

    }

}
