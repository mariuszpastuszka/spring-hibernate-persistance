
package com.javapersistence.part12.nplusoneselects;

import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NPlusOneSelects {

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("part12");

    private FetchTestData storeTestData() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Long[] itemIds = new Long[3];
        Long[] userIds = new Long[3];

        User johndoe = new User("johndoe");
        em.persist(johndoe);
        userIds[0] = johndoe.getId();

        User janeroe = new User("janeroe");
        em.persist(janeroe);
        userIds[1] = janeroe.getId();

        User robertdoe = new User("robertdoe");
        em.persist(robertdoe);
        userIds[2] = robertdoe.getId();

        Item item = new Item("Item One", LocalDate.now().plusDays(1), johndoe);
        em.persist(item);
        itemIds[0] = item.getId();
        for (int i = 1; i <= 3; i++) {
            Bid bid = new Bid(item, robertdoe, new BigDecimal(9 + i));
            item.addBid(bid);
            em.persist(bid);
        }

        item = new Item("Item Two", LocalDate.now().plusDays(1), johndoe);
        em.persist(item);
        itemIds[1] = item.getId();
        for (int i = 1; i <= 1; i++) {
            Bid bid = new Bid(item, janeroe, new BigDecimal(2 + i));
            item.addBid(bid);
            em.persist(bid);
        }

        item = new Item("Item Three", LocalDate.now().plusDays(2), janeroe);
        em.persist(item);
        itemIds[2] = item.getId();
        for (int i = 1; i <= 1; i++) {
            Bid bid = new Bid(item, johndoe, new BigDecimal(3 + i));
            item.addBid(bid);
            em.persist(bid);
        }

        em.getTransaction().commit();
        em.close();

        FetchTestData testData = new FetchTestData();
        testData.items = new TestData(itemIds);
        testData.users = new TestData(userIds);
        return testData;
    }

    @Test
    public void fetchUsers() throws Exception {
        storeTestData();

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        List<Item> items = em.createQuery("select i from Item i", Item.class).getResultList();
        // select * from ITEM

        for (Item item : items) {
            // Each seller has to be loaded with an additional SELECT
            assertNotNull(item.getSeller().getUsername());
            // select * from USERS where ID = ?
        }

        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void fetchBids() throws Exception {
        storeTestData();

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        List<Item> items = em.createQuery("select i from Item i", Item.class).getResultList();
        // select * from ITEM

        for (Item item : items) {
            // Each bids collection has to be loaded with an additional SELECT
            assertTrue(item.getBids().size() > 0);
            // select * from BID where ITEM_ID = ?
        }

        em.getTransaction().commit();
        em.close();
    }

}
