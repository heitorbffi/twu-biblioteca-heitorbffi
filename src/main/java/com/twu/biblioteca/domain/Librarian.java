package com.twu.biblioteca.domain;
import com.twu.biblioteca.utils.ConsolePrinter;
import com.twu.biblioteca.utils.InputAsker;

import java.util.List;
import java.util.Scanner;
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

        Scanner scanner = new Scanner(System.in);
        String command = scanner.next();

        ConsolePrinter printer = new ConsolePrinter();
        MenuOptions option = null;

        try {
            option = MenuOptions.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            printer.print("Invalid option! Please choose again.");
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

    // should be private maybe?
    public void processRequest(MenuOptions request) {
        UserRequestResult result = new UserRequestResult();
        ConsolePrinter printer = new ConsolePrinter();

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
                printer.print(result);

                return;
        }

        printer.print(result);
        serveUser();
    }

    private UserRequestResult listItems() {
        UserRequestResult result = new UserRequestResult();
        List<String> infoList = null;
        CatalogueOptions catalogueType = inputAsker.askForCatalogue();

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

    //shares a lot with method above. abstract and avoid repeating.
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
        CatalogueOptions catalogueType = inputAsker.askForCatalogue();
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
}
