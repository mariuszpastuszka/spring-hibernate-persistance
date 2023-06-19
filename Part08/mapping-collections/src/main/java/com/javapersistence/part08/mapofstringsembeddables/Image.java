
package com.javapersistence.part08.mapofstringsembeddables;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Image {

    @Column(nullable = true) // Can be null, not part of PK!
    private String filename;

    private int width;

    private int height;

    public Image() {
    }

    public Image(String filename, int width, int height) {
        this.filename = filename;
        this.width = width;
        this.height = height;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String title) {
        this.filename = title;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    // Whenever value-types are managed in collections, overriding equals/hashCode is a good idea!

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return width == image.width &&
                height == image.height &&
                Objects.equals(filename, image.filename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filename, width, height);
    }

}
