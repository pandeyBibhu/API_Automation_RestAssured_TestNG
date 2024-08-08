package com.restAssured.pojo;

import java.util.List;

public class AddLibraryPojo {
    private String name;
    private String isbn;
    private String aisle;
    private String author;
    private List<College> college;

    // No-argument constructor
    public AddLibraryPojo() {}

    // Parameterized constructor (optional, but useful)
    public AddLibraryPojo(String name, String isbn, String aisle, String author, List<College> college) {
        this.name = name;
        this.isbn = isbn;
        this.aisle = aisle;
        this.author = author;
        this.college = college;
    }

    // Getter and Setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAisle() {
        return aisle;
    }

    public void setAisle(String aisle) {
        this.aisle = aisle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<College> getCollege() {
        return college;
    }

    public void setCollege(List<College> college) {
        this.college = college;
    }
}
