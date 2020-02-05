package com.twu.biblioteca;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookCatalogue {
    private Set<Book> books = new HashSet<>();

    public BookCatalogue() {
        books.add(new Book("Zen Meditation", Year.of(1965), "Yaohui Ding"));
        books.add(new Book("Your Cat Hates You", Year.of(2018), "Cosmo Kramer"));
        books.add(new Book("Evolutionary Psychology", Year.of(1989), "David Buss"));
        books.add(new Book("Ancient History", Year.of(30), "Iulius Caesar"));
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

    public Book findByName(String name) {
        return books.stream()
                .filter(x-> x.getTitle().equalsIgnoreCase(name))
                .findFirst()
                .map(x-> new Book(x.getTitle(), x.getPublicationYear(), x.getAuthor()))
                .orElse(null);
    }

    public boolean successfullyRentBook(String searchedBookTitle) {
        for (Book book : books) {
            if (book.getTitle().equals(searchedBookTitle) && book.isAvailable()) {
                book.rent();

                return true;
            }
        }

        return false;
    }

    public void rent(Book newBook) {
        for (Book book : books) {
            if (book.getTitle().equals(newBook.getTitle())) {
                book.rent();
            }
        }
    }
}