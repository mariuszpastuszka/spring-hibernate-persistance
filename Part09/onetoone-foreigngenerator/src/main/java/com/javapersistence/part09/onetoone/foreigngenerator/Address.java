
package com.javapersistence.part09.onetoone.foreigngenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Address {

    @Id
    @GeneratedValue(generator = "addressKeyGenerator")
    @org.hibernate.annotations.GenericGenerator(
            name = "addressKeyGenerator",
            strategy = "foreign",
            parameters =
            @org.hibernate.annotations.Parameter(
                    name = "property", value = "user"
            )
    )
    private Long id;

    @NotNull
    private String street;

    @NotNull
    private String zipcode;

    @NotNull
    private String city;

    @OneToOne(optional = false) // Create FK constraint on PK column
    @PrimaryKeyJoinColumn
    private User user;

    public Address() {
    }

    public Address(User user) {
        this.user = user;
    }

    public Address(User user, String street, String zipcode, String city) {
        this.user = user;
        this.street = street;
        this.zipcode = zipcode;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public User getUser() {
        return user;
    }
}
