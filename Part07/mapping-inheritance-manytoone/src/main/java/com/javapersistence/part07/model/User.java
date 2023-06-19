
package com.javapersistence.part07.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    private Long id;

    @NotNull
    private String username;

    @ManyToOne
    private BillingDetails defaultBilling;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BillingDetails getDefaultBilling() {
        return defaultBilling;
    }

    public void setDefaultBilling(BillingDetails defaultBilling) {
        this.defaultBilling = defaultBilling;
    }

}
