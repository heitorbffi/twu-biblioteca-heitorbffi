package com.twu.biblioteca;

import java.time.Year;

public class Book {
    String title;
    Year publicationYear;
    String author;
    boolean available;

    public Book (String title, Year publicationYear, String author) {
        this.title = title;
        this.publicationYear = publicationYear;
        this.author = author;
        this.available = true;
    }

    public void rent() {
        available = false;
    }

    public boolean isAvailable() {
        return available;
    }
}