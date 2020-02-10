package com.twu.biblioteca;

import java.time.Year;
import java.util.Objects;

public class Borrowable {
    private String title;
    private Year publicationYear;
    private boolean available;

    public Borrowable (String title, Year publicationYear, boolean available){
        this.title = title;
        this.publicationYear = publicationYear;
        this.available = available;
    }


    public String getTitle() {
        return title;
    }

    public Year getPublicationYear() {
        return publicationYear;
    }

    public void rent() {
        available = false;
    }

    public void giveBack() {
        available = true;
    }

    public boolean isAvailable() {
        return available;
    }
}
