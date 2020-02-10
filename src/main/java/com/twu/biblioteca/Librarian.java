package com.twu.biblioteca;
import java.util.List;

public class Librarian {
    private Catalogue bookCatalogue;
    private Catalogue movieCatalogue;
    private final InputAsker inputAsker;

    public Librarian(Catalogue bookCatalogue, Catalogue movieCatalogue, InputAsker inputAsker) {
        this.bookCatalogue = bookCatalogue;
        this.movieCatalogue = movieCatalogue;
        this.inputAsker = inputAsker;
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
        } else if (!item.isAvailable()) {
            result.add("Sorry, that item is already checked out. Check back soon.");
        } else {
            bookCatalogue.rent(item);
            result.add("You have rented the item " + item.getTitle());
            result.add("Thank you! Enjoy it.");
        }

        return result;
    }

    private UserRequestResult tryToReturn() {
        Borrowable item = fetchItemFromCatalogue();
        UserRequestResult result = new UserRequestResult();

        if (item == null) {
            result.add("Sorry, that item is not in our catalogue.");
        } else if (item.isAvailable()) {
            result.add("That item is already with us.");
        } else {
            bookCatalogue.giveBack(item);
            result.add("Thank you for returning the item " + item.getTitle());
        }

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
