
package com.javapersistence.part06.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * Instead of <code>@Entity</code>, this component POJO is marked with <code>@Embeddable</code>. It
 * has no identifier property.
 */
@Embeddable
public class Address {

    @NotNull // Ignored for DDL generation!
    @Column(nullable = false) // Used for DDL generation!
    private String street;

    @NotNull
    @AttributeOverride(
            name = "name",
            column = @Column(name = "CITY", nullable = false)
    )
    private City city;

    /**
     * Hibernate will call this no-argument constructor to create an instance, and then
     * populate the fields directly.
     */
    public Address() {
    }

    /**
     * You can have additional (public) constructors for convenience.
     */
    public Address(String street, City city) {
        this.street = street;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
