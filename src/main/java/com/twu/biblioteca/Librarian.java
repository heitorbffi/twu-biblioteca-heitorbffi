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
                bookCatalogue.rentBook(bookName);

                List<String> rentedBookInfo = new ArrayList<>();
                rentedBookInfo.add("You have rented the book " + bookName);
                rentedBookInfo.add("Thank you! Enjoy the book");

                return rentedBookInfo;
            default:
                List<String> invalidOptionInfo = new ArrayList<>();
                invalidOptionInfo.add("Please select a valid option!");

                return invalidOptionInfo;
        }
    }
}
