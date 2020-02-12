package com.twu.biblioteca;

import java.util.Scanner;

public class InputAsker {
    public String askForTitle() {
        System.out.println("Type in the name of the item.");
        Scanner scanner = new Scanner(System.in);
        String bookName = scanner.nextLine();

        return bookName;
    }

    public CatalogueOptions askForCatalogue() {
        System.out.println("Please type BOOK if this is regarding books, or MOVIE if this is regarding movies.");
        Scanner scanner = new Scanner(System.in);
        String catalogueTypeString = scanner.nextLine();

        CatalogueOptions catalogueType = CatalogueOptions.valueOf(catalogueTypeString.toUpperCase());

        return catalogueType;
    }

    public String askForUserId() {
        System.out.println("What is your user ID?");
        Scanner scanner = new Scanner(System.in);

        //validate it with regex, insert in while loop
        return scanner.nextLine();
    }

    public String askForPIN() {
        System.out.println("What is your user PIN?");
        Scanner scanner = new Scanner(System.in);

        //validate it with regex, insert in while loop
        return scanner.nextLine();
    }
}
