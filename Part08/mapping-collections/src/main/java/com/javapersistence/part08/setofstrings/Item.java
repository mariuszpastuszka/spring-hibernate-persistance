
package com.javapersistence.part08.setofstrings;

import com.javapersistence.part08.Constants;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Item {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    private String name;

    @ElementCollection
    @CollectionTable(
            name = "IMAGE", // Defaults to ITEM_IMAGES
            joinColumns = @JoinColumn(name = "ITEM_ID")) // Default, actually
    @Column(name = "FILENAME") // Defaults to IMAGES
    private Set<String> images = new HashSet<>(); // Initialize field here

    public Item(String name) {
        this.name = name;
    }

    public Item() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<String> getImages() {
        return Collections.unmodifiableSet(images);
    }

    public void addImage(String image) {
        images.add(image);
    }

}
