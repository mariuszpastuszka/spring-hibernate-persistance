
package com.javapersistence.part08.onetomany.orphanremoval;

import com.javapersistence.part08.Constants;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "bidder")
    private Set<Bid> bids = new HashSet<>();

    public User(String name) {
        this.name = name;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Bid> getBids() {
        return Collections.unmodifiableSet(bids);
    }

    public void addBid(Bid bid) {
        bids.add(bid);
    }
}
