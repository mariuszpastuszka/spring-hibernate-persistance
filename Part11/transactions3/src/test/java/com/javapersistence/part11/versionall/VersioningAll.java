
package com.javapersistence.part11.versionall;

import org.junit.jupiter.api.Test;

import javax.persistence.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class VersioningAll {

    static EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("part11");

    @Test
    void firstCommitWins() throws ExecutionException, InterruptedException {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Item someItem = new Item("Some Item");
        em.persist(someItem);
        em.getTransaction().commit();
        em.close();
        final Long ITEM_ID = someItem.getId();

        EntityManager em1 = emf.createEntityManager();
        em1.getTransaction().begin();

        // Load an item and change its name
        Item item = em1.find(Item.class, ITEM_ID);
        item.setName("New Name");

        // The concurrent second unit of work doing the same
        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                EntityManager em2 = emf.createEntityManager();
                em2.getTransaction().begin();

                Item item1 = em2.find(Item.class, ITEM_ID);

                item1.setName("Other Name");

                em2.getTransaction().commit();
                em2.close();

            } catch (Exception ex) {
                // This shouldn't happen, this commit should win!
                throw new RuntimeException("Concurrent operation failure: " + ex, ex);
            }
            return null;
        }).get();

        assertThrows(OptimisticLockException.class, () -> em1.flush());

    }

}
