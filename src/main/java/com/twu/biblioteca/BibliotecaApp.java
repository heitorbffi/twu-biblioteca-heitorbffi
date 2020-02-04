package com.twu.biblioteca;

import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {

    public static void main(String[] args) {
        System.out.println(createWelcomeMessage());
        BookCatalogue bookCatalogue = new BookCatalogue();
        Librarian librarian = new Librarian(bookCatalogue, new InputAsker());

        Scanner scanner = new Scanner(System.in);

        Boolean askForInput = true;
        while (askForInput) {
            showMenu(librarian);
            String command = scanner.next();
            askForInput = processCommand(librarian, command);
        }
    }

    private static void showMenu(Librarian librarian) {
        System.out.println("Choose an option from the menu below, by typing it:");
        for (String option : librarian.getMenuOptions()) {
            System.out.println(option);
        }
    }

    private static Boolean processCommand(Librarian librarian, String command) {
        command = command.toLowerCase();
        if (command.equals("quit")) {
            System.out.println("Thanks for using the library, have a worthwhile day.");
            return false;
        }

        printBooksInfo(librarian.processRequest(command));

        return true;
    }

    public static String createWelcomeMessage() {
        String welcomeMessage = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";

        return welcomeMessage;
    }

    public static void printBooksInfo(List<String> booksInfo) {
        for (String bookInfo : booksInfo) {
            System.out.println(bookInfo);
        }
    }
}
