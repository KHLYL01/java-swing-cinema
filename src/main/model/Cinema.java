package main.model;

import java.io.Serializable;
import java.util.List;

public class Cinema implements Serializable {
    private String name;


    public Cinema(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
