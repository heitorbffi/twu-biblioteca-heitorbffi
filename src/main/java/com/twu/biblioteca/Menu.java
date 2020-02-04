package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    public static List<String> menuOptions;
    static {
        menuOptions = new ArrayList<>();
        menuOptions.add("List");
    }
    private BookCatalogue bookCatalogue;

    public Menu(BookCatalogue bookCatalogue) {
        this.bookCatalogue = bookCatalogue;
    }

    public static List<String> getMenuOptions() {
        return menuOptions;
    }

    public List<String> processRequest(String request) {
        switch(request) {
            case "list":
                return bookCatalogue.listBooksInfo();
            default:
                List<String> invalidOption = new ArrayList<>();
                invalidOption.add("Please select a valid option!");

                return invalidOption;
        }
    }
}
