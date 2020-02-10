package com.twu.biblioteca;

import java.time.Year;
import java.util.Objects;

public class Book extends Borrowable {
    private String author;

    public Book (String title, Year publicationYear, String author, boolean available) {
        super(title, publicationYear, available);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return String.format("%-30.30s %-30.30s %-30.30s", super.getTitle(), author, super.getPublicationYear());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return super.isAvailable() == book.isAvailable() &&
                Objects.equals(super.getTitle(), book.getTitle()) &&
                Objects.equals(super.getPublicationYear(), book.getPublicationYear()) &&
                Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getTitle(), super.getPublicationYear(), author, super.isAvailable());
    }
}
