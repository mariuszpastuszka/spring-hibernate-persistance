
package com.javapersistence.part08.mapofstringsorderby;

import com.javapersistence.part08.Constants;

import javax.persistence.*;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@Entity
public class Item {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    private String name;

    public Item(String name) {
        this.name = name;
    }

    public Item() {

    }

    @ElementCollection
    @CollectionTable(name = "IMAGE")
    @MapKeyColumn(name = "FILENAME")
    @Column(name = "IMAGENAME")
    @org.hibernate.annotations.OrderBy(clause = "FILENAME desc")
    private Map<String, String> images = new LinkedHashMap<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getImages() {
        return Collections.unmodifiableMap(images);
    }

    public void putImage(String key, String value) {
        images.put(key, value);
    }
}
