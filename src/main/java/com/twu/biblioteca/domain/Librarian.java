package com.twu.biblioteca.domain;
import com.twu.biblioteca.utils.ConsolePrinter;
import com.twu.biblioteca.utils.InputAsker;
import com.twu.biblioteca.utils.RequestConverter;

import java.util.List;
import java.util.Set;

public class Librarian {
    private Catalogue<Book> bookCatalogue;
    private Catalogue<Movie> movieCatalogue;
    private Set<User> userbase;
    private final InputAsker inputAsker;
    private final ConsolePrinter consolePrinter;
    private User currentUser;

    public Librarian(Catalogue<Book> bookCatalogue, Catalogue<Movie> movieCatalogue, Set<User> userbase,
                     InputAsker inputAsker, ConsolePrinter consolePrinter) {
        this.bookCatalogue = bookCatalogue;
        this.movieCatalogue = movieCatalogue;
        this.userbase = userbase;
        this.inputAsker = inputAsker;
        this.consolePrinter = consolePrinter;
    }

    public void serveUser() {
        showMenu();

        String command = inputAsker.askForOption();

        MenuOptions option = null;
        try {
            option = RequestConverter.convertToMenuOption(command);
        } catch (IllegalArgumentException e) {
            consolePrinter.print("Invalid option! Please choose again.");
            serveUser();
        }

        processRequest(option);
    }

    private void showMenu() {
        consolePrinter.print("Choose an option from the menu below, by typing it:");
        for (MenuOptions option : MenuOptions.values()) {
            consolePrinter.print(option.toString());
        }
    }

    public void logIn() {
        UserRequestResult result = null;
        while (currentUser == null) {
            result = attemptLogIn();
            consolePrinter.print(result);
        }
    }

    private UserRequestResult attemptLogIn() {
        String userId = inputAsker.askForUserId();
        String password = inputAsker.askForPIN();

        UserRequestResult result = new UserRequestResult();

        for (User user : userbase) {
            if (user.getId().equals(userId)) {
                if (user.getPassword().equals(password)) {
                    currentUser = user;
                    result.add("You have successfully logged in");
                } else {
                    result.add("The PIN is not correct.");
                }
                return result;
            }
        }

        result.add("That username is not in our database");
        return result;
    }

    public void processRequest(MenuOptions request) {
        UserRequestResult result = new UserRequestResult();

        switch(request) {
            case LIST:
                result.add(listItems());
                break;
            case RENT:
                result.add(tryToRent());
                break;
            case RETURN:
                result.add(tryToReturn());
                break;
            case USER:
                result.add(listUserInfo());
                break;
            case QUIT:
                result.add(quitApp());
                consolePrinter.print(result);

                return;
        }

        consolePrinter.print(result);
        serveUser();
    }

    private UserRequestResult listItems() {
        UserRequestResult result = new UserRequestResult();
        List<String> infoList = null;

        CatalogueOptions catalogueType = getCatalogueTypeFromUser();

        if (catalogueType == CatalogueOptions.BOOK) {
            infoList = bookCatalogue.listAvailableItemsInfo();
        } else if (catalogueType == CatalogueOptions.MOVIE) {
            infoList = movieCatalogue.listAvailableItemsInfo();
        }

        for (String info : infoList) {
            result.add(info);
        }

        return result;
    }

    private UserRequestResult tryToRent() {
        Borrowable item = fetchItemFromCatalogue();
        UserRequestResult result = new UserRequestResult();

        if (item == null) {
            result.add("Sorry, that item is not in our catalogue.");
            return result;
        }

        if (!item.isAvailable()) {
            result.add("Sorry, that item is already checked out. Check back soon.");
            return result;
        }

        if (item instanceof Book) {
            bookCatalogue.rent(currentUser, (Book)item);
        } else {
            movieCatalogue.rent(currentUser, (Movie)item);
        }

        result.add("You have rented the item " + item.getTitle());
        result.add("Thank you! Enjoy it.");

        return result;
    }

    private UserRequestResult tryToReturn() {
        Borrowable item = fetchItemFromCatalogue();
        UserRequestResult result = new UserRequestResult();

        if (item == null) {
            result.add("Sorry, that item is not in our catalogue.");
            return result;
        }

        if (item.isAvailable()) {
            result.add("That item is already with us.");
            return result;
        }

        if (item instanceof Book) {
            bookCatalogue.giveBack(currentUser, (Book)item);
        } else {
            movieCatalogue.giveBack(currentUser, (Movie)item);
        }

        result.add("Thank you for returning the item " + item.getTitle());

        return result;
    }

    private Borrowable fetchItemFromCatalogue() {
        CatalogueOptions catalogueType = getCatalogueTypeFromUser();
        Borrowable item = null;
        String title = inputAsker.askForTitle();

        if (catalogueType == CatalogueOptions.BOOK) {
            item = bookCatalogue.findByTitle(title);
        } else if (catalogueType == CatalogueOptions.MOVIE) {
            item = movieCatalogue.findByTitle(title);
        }

        return item;
    }

    private UserRequestResult listUserInfo() {
        UserRequestResult result = new UserRequestResult();

        if (currentUser != null) {
            result.add(currentUser.getInfo());
        } else {
            result.add("No user logged in!");
        }

        return result;
    }

    private UserRequestResult quitApp() {
        UserRequestResult result = new UserRequestResult();
        result.add("Thank you for using the library, have a worthwhile day.");

        return result;
    }

    private CatalogueOptions getCatalogueTypeFromUser() {
        String command = inputAsker.askForCatalogue();

        CatalogueOptions catalogueType = null;
        try {
            catalogueType = RequestConverter.convertToCatalogueOption(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            consolePrinter.print("Invalid option! Please try again.");
            catalogueType = getCatalogueTypeFromUser();
        }

        return catalogueType;
    }
}
