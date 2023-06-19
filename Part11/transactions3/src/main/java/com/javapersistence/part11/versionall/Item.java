
package com.javapersistence.part11.versionall;

import com.javapersistence.part11.Constants;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@org.hibernate.annotations.OptimisticLocking(
        type = org.hibernate.annotations.OptimisticLockType.ALL)
@org.hibernate.annotations.DynamicUpdate
public class Item {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    @NotNull
    private String name;

    public Item() {
    }

    public Item(String name) {
        this.name = name;
    }

    public Long getId() { // Optional but useful
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // ...
}
