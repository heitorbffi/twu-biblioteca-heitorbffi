package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Librarian {
    public static List<String> menuOptions;
    static {
        menuOptions = new ArrayList<>();
        menuOptions.add("List");
        menuOptions.add("Rent");
        menuOptions.add("Quit");
    }
    private BookCatalogue bookCatalogue;
    private final InputAsker inputAsker;

    public Librarian(BookCatalogue bookCatalogue, InputAsker inputAsker) {
        this.bookCatalogue = bookCatalogue;
        this.inputAsker = inputAsker;
    }

    public static List<String> getMenuOptions() {
        return menuOptions;
    }

    public List<String> processRequest(String request) {
        switch(request) {
            case "list":
                return bookCatalogue.listAvailableBooksInfo();
            case "rent":
                String bookName = inputAsker.askForName();
                List<String> rentedBookInfo = new ArrayList<>();

                if (bookCatalogue.successfullyRentBook(bookName)) {
                    rentedBookInfo = new ArrayList<>();
                    rentedBookInfo.add("You have rented the book " + bookName);
                    rentedBookInfo.add("Thank you! Enjoy the book");
                } else {
                    rentedBookInfo.add("Sorry, that book is unavailable");
                }

                return rentedBookInfo;
            default:
                List<String> invalidOptionInfo = new ArrayList<>();
                invalidOptionInfo.add("Please select a valid option!");

                return invalidOptionInfo;
        }
    }
}
