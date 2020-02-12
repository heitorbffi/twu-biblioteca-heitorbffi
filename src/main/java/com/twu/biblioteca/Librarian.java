package com.twu.biblioteca;
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
            } else {
                result.add("That username is not in our database");
            }
        }

        return result;
    }

    public UserRequestResult processRequest(MenuOptions request) {
        switch(request) {
            case LIST:
                return listItems();
            case RENT:
                return tryToRent();
            case RETURN:
                return tryToReturn();
            default:
                UserRequestResult result = new UserRequestResult();
                result.add("Please select a valid option!");

                return result;
        }
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
}
