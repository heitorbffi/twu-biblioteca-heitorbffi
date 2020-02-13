package com.twu.biblioteca.domain;

import com.twu.biblioteca.domain.User;

import java.time.Year;

public abstract class Borrowable {
    private String title;
    private Year publicationYear;
    private User user;

    public Borrowable (String title, Year publicationYear, User user){
        this.title = title;
        this.publicationYear = publicationYear;
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public Year getPublicationYear() {
        return publicationYear;
    }

    public User getUser() {
        return user;
    }

    public void rent(User user) {
        this.user = user;
    }

    public void giveBack(User user) {
        if (user.equals(this.user)) {
            this.user = null;
        }
    }

    public boolean isAvailable() {
        return user == null;
    }
}
