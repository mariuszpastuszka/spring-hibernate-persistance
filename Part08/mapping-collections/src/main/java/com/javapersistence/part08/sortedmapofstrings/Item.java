
package com.javapersistence.part08.sortedmapofstrings;

import com.javapersistence.part08.Constants;

import javax.persistence.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.SortedMap;
import java.util.TreeMap;

@Entity
public class Item {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    private String name;

    @ElementCollection
    @CollectionTable(name = "IMAGE")
    @MapKeyColumn(name = "FILENAME")
    @Column(name = "IMAGENAME")
    @org.hibernate.annotations.SortComparator(ReverseStringComparator.class)
    private SortedMap<String, String> images = new TreeMap<>();

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

    public SortedMap<String, String> getImages() {
        return Collections.unmodifiableSortedMap(images);
    }

    public void putImage(String key, String value) {
        images.put(key, value);
    }

    public static class ReverseStringComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            return b.compareTo(a); // Inverse sorting
        }
    }

}
