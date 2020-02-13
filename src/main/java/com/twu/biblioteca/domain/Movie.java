package com.twu.biblioteca.domain;

import java.time.Year;
import java.util.Objects;

public class Movie extends Borrowable {
    private String director;
    private int rating;

    public Movie(String title, Year publicationYear, String director, int rating, User user) {
        super(title, publicationYear, user);
        this.director = director;
        this.rating = rating;
    }

    public Movie (String title, Year publicationYear, String director, User user) {
        super(title, publicationYear, user);
        this.director = director;
        this.rating = 0;
    }

    public String getDirector() {
        return director;
    }

    @Override
    public String toString() {
        String ratingString;

        if (rating == 0) {
            ratingString = "not rated";
        } else {
            ratingString = String.valueOf(rating);
        }

        return String.format("%-25.25s %-25.25s %-25.25s %-25.25s", super.getTitle(), director,
                super.getPublicationYear(), ratingString);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        return super.isAvailable() == movie.isAvailable() &&
                Objects.equals(super.getTitle(), movie.getTitle()) &&
                Objects.equals(super.getPublicationYear(), movie.getPublicationYear()) &&
                Objects.equals(director, movie.getDirector());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getTitle(), super.getPublicationYear(), director, super.isAvailable(), rating);
    }

    public int getRating() {
        return rating;
    }
}