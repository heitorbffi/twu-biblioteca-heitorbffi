package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Librarian {
    private Catalogue catalogue;
    private final InputAsker inputAsker;

    public Librarian(Catalogue catalogue, InputAsker inputAsker) {
        this.catalogue = catalogue;
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
        List<String> booksInfoList = catalogue.listAvailableItemsInfo();

        for (String bookInfo : booksInfoList) {
            result.add(bookInfo);
        }

        return result;
    }

    private UserRequestResult tryToRentBook() {
        UserRequestResult result = new UserRequestResult();
        String title = inputAsker.askForName();

        Borrowable book = catalogue.findByTitle(title);

        if (book == null) {
            result.add("Sorry, that book is not in our catalogue.");
        } else if (!book.isAvailable()) {
            result.add("Sorry, that book is already checked out. Check back soon.");
        } else {
            catalogue.rent(book);
            result.add("You have rented the book " + title);
            result.add("Thank you! Enjoy the book");
        }

        return result;
    }

    private UserRequestResult tryToReturnBook() {
        String bookName = inputAsker.askForName();
        UserRequestResult result = new UserRequestResult();

        Borrowable book = catalogue.findByTitle(bookName);

        if (book == null) {
            result.add("Sorry, that book is not in our catalogue.");
        } else if (book.isAvailable()) {
            result.add("That book is already with us.");
        } else {
            catalogue.giveBack(book);
            result.add("Thank you for returning the book " + bookName);
        }

        return result;
    }
}
