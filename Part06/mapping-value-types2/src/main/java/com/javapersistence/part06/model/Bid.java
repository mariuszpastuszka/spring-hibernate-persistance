
package com.javapersistence.part06.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Bid {

    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    private Long id;

    @NotNull
    private BigDecimal amount;

    @ManyToOne(optional = false, fetch = FetchType.LAZY) // NOT NULL
    @JoinColumn(name = "ITEM_ID") // Actually the default name
    private Item item;

    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
