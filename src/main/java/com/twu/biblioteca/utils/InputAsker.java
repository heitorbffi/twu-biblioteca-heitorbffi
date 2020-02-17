package com.twu.biblioteca.utils;

import com.twu.biblioteca.domain.CatalogueOptions;

import java.util.Scanner;

public class InputAsker {
    public String askForOption() {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        return command;
    }

    public String askForTitle() {
        System.out.println("Type in the name of the item.");
        Scanner scanner = new Scanner(System.in);
        String bookName = scanner.nextLine();

        return bookName;
    }

    public String askForCatalogue() {
        System.out.println("Please type BOOK if this is regarding books, or MOVIE if this is regarding movies.");
        Scanner scanner = new Scanner(System.in);
        String catalogueType = scanner.nextLine();

        return catalogueType;
    }

    public String askForUserId() {
        System.out.println("What is your user ID?");
        Scanner scanner = new Scanner(System.in);

        return scanner.nextLine();
    }

    public String askForPIN() {
        System.out.println("What is your user PIN?");
        Scanner scanner = new Scanner(System.in);

        return scanner.nextLine();
    }
}
