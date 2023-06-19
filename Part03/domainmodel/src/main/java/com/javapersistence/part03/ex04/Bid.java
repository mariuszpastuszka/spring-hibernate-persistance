
package com.javapersistence.part03.ex04;

public class Bid {

    private Item item;

    public Bid() {
    }

    public Bid(Item item) {
        this.item = item;
        item.bids.add(this); // Bidirectional
    }

    public Item getItem() {
        return item;
    }

}
