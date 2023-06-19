
package com.javapersistence.part08.bagofembeddables;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Image {

    @Column(nullable = true) // Can be null if we have surrogate PK!
    private String title;

    @Column(nullable = false)
    private String filename;

    private int width;

    private int height;

    public Image() {
    }

    public Image(String title, String filename, int width, int height) {
        this.title = title;
        this.filename = filename;
        this.width = width;
        this.height = height;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
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
                Objects.equals(title, image.title) &&
                filename.equals(image.filename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, filename, width, height);
    }

}
