package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Catalogue<T extends Borrowable> {
    public Set<T> items;

    public Catalogue(Set<T> items) {
        this.items = items;
    }

    public List<String> listAvailableItemsInfo() {
        List<String> booksInfo = new ArrayList<>();

        for (T item : items) {
            if (item.isAvailable()) {
                booksInfo.add(item.toString());
            }
        }

        return booksInfo;
    }

    public Borrowable findByTitle(String title) {
        return items.stream()
                .filter(x-> x.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .map(x-> createBorrowable(x))
                .orElse(null);
    }

    public Borrowable createBorrowable(T borrowable) {
        if (borrowable instanceof Book) {
            Book book = (Book) borrowable;

            return new Book(book.getTitle(), book.getPublicationYear(), book.getAuthor(), book.getUser());
        }

        Movie movie = (Movie) borrowable;

        return new Movie(movie.getTitle(), movie.getPublicationYear(), movie.getDirector(), movie.getRating(), movie.getUser());
    }

    public void rent(User user, T borrowable) {
        for (T item : items) {
            if (item.equals(borrowable)) {
                item.rent(user);
            }
        }
    }

    public void giveBack(User user, T borrowable) {
        for (T item : items) {
            if (item.equals(borrowable)) {
                item.giveBack(user);
            }
        }
    }
}
