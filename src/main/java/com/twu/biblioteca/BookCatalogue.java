package com.twu.biblioteca;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookCatalogue {
    private static Set<Book> books;

    static {
        books = new HashSet<>();
        books.add(new Book("Zen Meditation", Year.of(1965), "Yaohui Ding"));
        books.add(new Book("Your Cat Hates You", Year.of(2018), "Cosmo Kramer"));
        books.add(new Book("Evolutionary Psychology", Year.of(1989), "David Buss"));
        books.add(new Book("Ancient History", Year.of(30), "Iulius Caesar"));
    }

    public static List<String> listAvailableBooksInfo() {
        List<String> booksInfo = new ArrayList<>();

        for (Book book : books) {
            if (book.isAvailable()) {
                String bookInfo = String.format("%-30.30s %-30.30s %-30.30s", book.getTitle(), book.getAuthor(), book.getPublicationYear());
                booksInfo.add(bookInfo);
            }
        }

        return booksInfo;
    }

    public static void rentBook(String searchedBookTitle) {
        for (Book book : books) {
            if (book.getTitle().equals(searchedBookTitle)) {
                book.rent();

                return;
            }
        }
    }
}