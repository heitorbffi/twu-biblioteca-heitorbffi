package com.twu.biblioteca;

import java.time.Year;
import java.util.*;

public class BookCatalogue {
    private Set<Book> books = new LinkedHashSet<>();

    public BookCatalogue() {
        books.add(new Book("Zen Meditation", Year.of(1965), "Yaohui Ding", true));
        books.add(new Book("Your Cat Hates You", Year.of(2018), "Cosmo Kramer", true));
        books.add(new Book("Evolutionary Psychology", Year.of(1989), "David Buss", true));
        books.add(new Book("Ancient History", Year.of(30), "Iulius Caesar", true));
    }

    public List<String> listAvailableBooksInfo() {
        List<String> booksInfo = new ArrayList<>();

        for (Book book : books) {
            if (book.isAvailable()) {
                String bookInfo = String.format("%-30.30s %-30.30s %-30.30s", book.getTitle(), book.getAuthor(), book.getPublicationYear());
                booksInfo.add(bookInfo);
            }
        }

        return booksInfo;
    }

    public Book findByTitle(String title) {
        return books.stream()
                .filter(x-> x.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .map(x-> new Book(x.getTitle(), x.getPublicationYear(), x.getAuthor(), x.isAvailable()))
                .orElse(null);
    }

    public void rent(Book bookToRent) {
        for (Book book : books) {
            if (book.equals(bookToRent)) {
                book.rent();
            }
        }
    }

    public void giveBack(Book bookToReturn) {
        for (Book book : books) {
            if (book.equals(bookToReturn)) {
                book.giveBack();
            }
        }
    }
}