
package com.javapersistence.part08.mapofstringsembeddables;

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
    @MapKeyColumn(name = "TITLE") // Optional, defaults to IMAGES_KEY
    private Map<String, Image> images = new HashMap<>();

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

    public Map<String, Image> getImages() {
        return Collections.unmodifiableMap(images);
    }

    public void putImage(String key, Image value) {
        images.put(key, value);
    }

}
