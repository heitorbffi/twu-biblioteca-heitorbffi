package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Librarian {
    private BookCatalogue bookCatalogue;
    private final InputAsker inputAsker;

    public Librarian(BookCatalogue bookCatalogue, InputAsker inputAsker) {
        this.bookCatalogue = bookCatalogue;
        this.inputAsker = inputAsker;
    }

    public List<String> processRequest(MenuOptions request) {
        switch(request) {
            case LIST:
                return bookCatalogue.listAvailableBooksInfo();
            case RENT:
                return tryToRentBook();
            case RETURN:
                return tryToReturnBook();
            default:
                List<String> invalidOptionInfo = new ArrayList<>();
                invalidOptionInfo.add("Please select a valid option!");

                return invalidOptionInfo;
        }
    }

    private List<String> tryToRentBook() {
        String bookName = inputAsker.askForName();
        List<String> rentedBookInfo = new ArrayList<>();

        Book book = bookCatalogue.findByTitle(bookName);

        if (book == null) {
            rentedBookInfo.add("Sorry, that book is not in our catalogue.");
        } else if (!book.isAvailable()) {
            rentedBookInfo.add("Sorry, that book is already checked out. Check back soon.");
        } else {
            bookCatalogue.rent(book);
            rentedBookInfo.add("You have rented the book " + bookName);
            rentedBookInfo.add("Thank you! Enjoy the book");
        }

        return rentedBookInfo;
    }

    private List<String> tryToReturnBook() {
        String bookName = inputAsker.askForName();
        List<String> returnedBookInfo = new ArrayList<>();

        Book book = bookCatalogue.findByTitle(bookName);

        if (book == null) {
            returnedBookInfo.add("Sorry, that book is not in our catalogue.");
        } else if (book.isAvailable()) {
            returnedBookInfo.add("That book is already with us.");
        } else {
            bookCatalogue.giveBack(book);
            returnedBookInfo.add("Thank you for returning the book " + bookName);
        }

        return returnedBookInfo;
    }
}
