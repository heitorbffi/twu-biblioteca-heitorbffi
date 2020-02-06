package com.twu.biblioteca;

import java.time.Year;
import java.util.Objects;

public class Book {
    private String title;
    private Year publicationYear;
    private String author;
    private boolean available;

    public Book (String title, Year publicationYear, String author, boolean available) {
        this.title = title;
        this.publicationYear = publicationYear;
        this.author = author;
        this.available = available;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
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

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", publicationYear=" + publicationYear +
                ", author='" + author + '\'' +
                ", available=" + available +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return available == book.available &&
                Objects.equals(title, book.title) &&
                Objects.equals(publicationYear, book.publicationYear) &&
                Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, publicationYear, author, available);
    }
}
