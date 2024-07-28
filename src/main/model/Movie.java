package main.model;

import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable {
    private int id;
    private String title;
    private String imagePath;
    private String type;

    public Movie(int id, String title, String imagePath, String type) {
        this.id = id;
        this.title = title;
        this.imagePath = imagePath;
        this.type = type;
    }

    public boolean equals(Movie movie) {
        return this.title == movie.getTitle();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
