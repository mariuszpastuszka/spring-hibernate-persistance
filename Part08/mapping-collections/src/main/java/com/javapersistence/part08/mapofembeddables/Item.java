
package com.javapersistence.part08.mapofembeddables;

import com.javapersistence.part08.Constants;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Item {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    private String name;

    @ElementCollection
    @CollectionTable(name = "IMAGE")
    private Map<Filename, Image> images = new HashMap<>();

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

    public void setImages(Map<Filename, Image> images) {
        this.images = images;
    }

    public Map<Filename, Image> getImages() {
        return Collections.unmodifiableMap(images);
    }

    public void putImage(Filename key, Image value) {
        images.put(key, value);
    }
}
