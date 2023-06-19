
package com.javapersistence.part11.timestamp;

import com.javapersistence.part11.Constants;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Item {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    @Version
    // Optional: @org.hibernate.annotations.Type(type = "dbtimestamp")
    private LocalDateTime lastUpdated;

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

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // ...
}
