
package com.javapersistence.part08.listofstrings;

import com.javapersistence.part08.Constants;

import javax.persistence.*;
import java.util.*;

@Entity
public class Item {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    private String name;

    @ElementCollection
    @CollectionTable(name = "IMAGE") // Default, actually
    @OrderColumn // Enables persistent order, Defaults to IMAGES_ORDER
    @Column(name = "FILENAME") // Defaults to IMAGES
    private List<String> images = new ArrayList<>();

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

    public List<String> getImages() {
        return Collections.unmodifiableList(images);
    }

    public void addImage(String image) {
        images.add(image);
    }

}
