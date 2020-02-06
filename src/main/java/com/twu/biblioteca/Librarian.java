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

    public UserRequestResult processRequest(MenuOptions request) {
        switch(request) {
            case LIST:
                return listBooks();
            case RENT:
                return tryToRentBook();
            case RETURN:
                return tryToReturnBook();
            default:
                UserRequestResult result = new UserRequestResult();
                result.add("Please select a valid option!");

                return result;
        }
    }

    private UserRequestResult listBooks() {
        UserRequestResult result = new UserRequestResult();
        List<String> booksInfoList = bookCatalogue.listAvailableBooksInfo();

        for (String bookInfo : booksInfoList) {
            result.add(bookInfo);
        }

        return result;
    }

    private UserRequestResult tryToRentBook() {
        UserRequestResult result = new UserRequestResult();
        String bookName = inputAsker.askForName();

        Book book = bookCatalogue.findByTitle(bookName);

        if (book == null) {
            result.add("Sorry, that book is not in our catalogue.");
        } else if (!book.isAvailable()) {
            result.add("Sorry, that book is already checked out. Check back soon.");
        } else {
            bookCatalogue.rent(book);
            result.add("You have rented the book " + bookName);
            result.add("Thank you! Enjoy the book");
        }

        return result;
    }

    private UserRequestResult tryToReturnBook() {
        String bookName = inputAsker.askForName();
        UserRequestResult result = new UserRequestResult();

        Book book = bookCatalogue.findByTitle(bookName);

        if (book == null) {
            result.add("Sorry, that book is not in our catalogue.");
        } else if (book.isAvailable()) {
            result.add("That book is already with us.");
        } else {
            bookCatalogue.giveBack(book);
            result.add("Thank you for returning the book " + bookName);
        }

        return result;
    }
}
