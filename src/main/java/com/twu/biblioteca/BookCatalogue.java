package com.twu.biblioteca;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookCatalogue {
    private Set<Book> books = new HashSet<>();

    public void generateBooks() {
        this.books.add(new Book("Zen Meditation", Year.of(1965), "Yaohui Ding"));
        this.books.add(new Book("Your Cat Hates You", Year.of(2018), "Cosmo Kramer"));
        this.books.add(new Book("Evolutionary Psychology", Year.of(1989), "David Buss"));
        this.books.add(new Book("Ancient History", Year.of(30), "Iulius Caesar"));
    }

    public List<String> listTitles() {
        List<String> titles = new ArrayList<>();
        for (Book book : this.books) {
            titles.add(book.getTitle());
        }

        return titles;
    }
}