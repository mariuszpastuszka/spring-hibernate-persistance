
package com.javapersistence.part07;

import com.javapersistence.part07.model.BankAccount;
import com.javapersistence.part07.model.BillingDetails;
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

            List<BillingDetails> billingDetails =
                    em.createQuery("select bd from BillingDetails bd", BillingDetails.class)
                            .getResultList();

            List<CreditCard> creditCards =
                    em.createQuery("select cc from CreditCard cc", CreditCard.class)
                            .getResultList();

            em.getTransaction().commit();

            assertAll(
                    () -> assertEquals(2, billingDetails.size()),
                    () -> assertEquals(1, creditCards.size())
            );
        } finally {
            em.close();
            emf.close();
        }
    }
}
