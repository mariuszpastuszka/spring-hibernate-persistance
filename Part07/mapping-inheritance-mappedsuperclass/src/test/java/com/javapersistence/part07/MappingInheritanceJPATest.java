
package com.javapersistence.part07;

import com.javapersistence.part07.model.BankAccount;
import com.javapersistence.part07.model.CreditCard;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MappingInheritanceJPATest {

    @Test
    public void storeLoadEntities() {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("part07.mapping_inheritance");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            CreditCard creditCard = new CreditCard("John Smith", "123456789", "10", "2030");
            BankAccount bankAccount = new BankAccount("Mike Johnson", "12345", "Delta Bank", "BANKXY12");
            em.persist(creditCard);
            em.persist(bankAccount);

            em.getTransaction().commit();
            em.refresh(creditCard);
            em.refresh(bankAccount);

            em.getTransaction().begin();

            List<CreditCard> creditCards =
                    em.createQuery("select cc from CreditCard cc", CreditCard.class)
                            .getResultList();

            List<BankAccount> bankAccounts =
                    em.createQuery("select ba from BankAccount ba", BankAccount.class)
                            .getResultList();

            em.getTransaction().commit();

            assertAll(
                    () -> assertEquals(1, creditCards.size()),
                    () -> assertEquals("123456789", creditCards.get(0).getCardNumber()),
                    () -> assertEquals(1, bankAccounts.size()),
                    () -> assertEquals("12345", bankAccounts.get(0).getAccount())
            );
        } finally {
            em.close();
            emf.close();
        }
    }
}
