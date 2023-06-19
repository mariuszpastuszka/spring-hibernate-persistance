
package com.javapersistence.part08.mapofembeddables;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Filename {

    @Column(nullable = false) // Must be NOT NULL, part of PK!
    private String name;

    public Filename() {
    }

    public Filename(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filename filename = (Filename) o;
        return name.equals(filename.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
