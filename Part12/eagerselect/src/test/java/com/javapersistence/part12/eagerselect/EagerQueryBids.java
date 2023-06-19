
package com.javapersistence.part12.eagerselect;

import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EagerQueryBids {

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("part12_2");

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
    public void fetchBids() {
        storeTestData();

        {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            List<Item> items =
                    em.createQuery("select i from Item i left join fetch i.bids", Item.class)
                            .getResultList();
            // select i.*, b.*
            //  from ITEM i
            //   left outer join BID b on b.ITEM_ID = i.ID
            //  where i.ID = ?

            em.close(); // Detach all

            for (Item item : items) {
                assertTrue(item.getBids().size() > 0);
            }
        }
        {
            EntityManager em = emf.createEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Item> criteria = cb.createQuery(Item.class);

            Root<Item> i = criteria.from(Item.class);
            i.fetch("bids", JoinType.LEFT);
            criteria.select(i);

            List<Item> items = em.createQuery(criteria).getResultList();

            em.close(); // Detach all

            for (Item item : items) {
                assertTrue(item.getBids().size() > 0);
            }
        }
    }

}
