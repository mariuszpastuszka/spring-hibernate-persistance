
package com.javapersistence.part09.onetomany.list;

import com.javapersistence.part09.Constants;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Item {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    @NotNull
    private String name;

    @OneToMany
    @JoinColumn(
            name = "ITEM_ID",
            nullable = false
    )
    @OrderColumn(
            name = "BID_POSITION", // Defaults to BIDS_ORDER
            nullable = false
    )
    private List<Bid> bids = new ArrayList<>();

    public Item() {
    }

    public Item(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Bid> getBids() {
        return Collections.unmodifiableList(bids);
    }

    public void addBid(Bid bid) {
        bids.add(bid);
    }

}
