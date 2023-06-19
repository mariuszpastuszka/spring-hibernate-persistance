
package com.javapersistence.part08.setofembeddables;

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
    @CollectionTable(name = "IMAGE")
    @AttributeOverride(
            name = "filename",
            column = @Column(name = "FNAME", nullable = false)
    )
    private Set<Image> images = new HashSet<>();

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

    public Set<Image> getImages() {
        return Collections.unmodifiableSet(images);
    }

    public void addImage(Image image) {
        images.add(image);
    }
}
