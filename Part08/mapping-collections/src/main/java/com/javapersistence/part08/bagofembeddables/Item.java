
package com.javapersistence.part08.bagofembeddables;

import com.javapersistence.part08.Constants;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Entity
public class Item {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    private String name;

    @ElementCollection
    @CollectionTable(name = "IMAGE")
    @GenericGenerator(name = "sequence_gen", strategy = "sequence")
    @org.hibernate.annotations.CollectionId( // Surrogate PK allows duplicates!
            columns = @Column(name = "IMAGE_ID"),
            type = @org.hibernate.annotations.Type(type = "long"),
            generator = "sequence_gen")
    private Collection<Image> images = new ArrayList<>();

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

    public Collection<Image> getImages() {
        return Collections.unmodifiableCollection(images);
    }

    public void addImage(Image image) {
        images.add(image);
    }
}
