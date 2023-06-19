
package com.javapersistence.part07;

import com.javapersistence.part07.model.BankAccount;
import com.javapersistence.part07.model.BillingDetails;
import com.javapersistence.part07.model.CreditCard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MappingInheritanceHibernateTest {

    private static SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure().addAnnotatedClass(BankAccount.class).addAnnotatedClass(CreditCard.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
                applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    @Test
    public void storeLoadEntities() {

        try (SessionFactory sessionFactory = createSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            CreditCard creditCard = new CreditCard("John Smith", "123456789", "10", "2030");
            BankAccount bankAccount = new BankAccount("Mike Johnson", "12345", "Delta Bank", "BANKXY12");

            session.persist(creditCard);
            session.persist(bankAccount);

            session.getTransaction().commit();

            session.refresh(creditCard);
            session.refresh(bankAccount);

            session.beginTransaction();

            List<BillingDetails> billingDetails =
                    session.createQuery("select bd from BillingDetails bd", BillingDetails.class)
                            .getResultList();

            session.getTransaction().commit();

            assertAll(
                    () -> assertEquals(2, billingDetails.size())
            );

        }
    }


}
