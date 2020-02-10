package com.twu.biblioteca;

import java.time.Year;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class BibliotecaApp {

    public static void main(String[] args) {
        System.out.println(createWelcomeMessage());
        Catalogue bookCatalogue = createBookCatalogue();
        Librarian librarian = new Librarian(bookCatalogue, new InputAsker());

        Scanner scanner = new Scanner(System.in);

        Boolean askForInput = true;
        while (askForInput) {
            showMenu();
            String command = scanner.next();
            askForInput = processCommand(librarian, command);
        }
    }

    private static Catalogue createBookCatalogue() {
        Set<Book> books = new LinkedHashSet<>();

        books.add(new Book("Zen Meditation", Year.of(1965), "Yaohui Ding", true));
        books.add(new Book("Your Cat Hates You", Year.of(2018), "Cosmo Kramer", true));
        books.add(new Book("Evolutionary Psychology", Year.of(1989), "David Buss", true));
        books.add(new Book("Ancient History", Year.of(30), "Iulius Caesar", true));

        return new Catalogue<Book>(books);
    }

    private static void showMenu() {
        System.out.println("Choose an option from the menu below, by typing it:");
        for (MenuOptions option : MenuOptions.values()) {
            System.out.println(option);
        }
    }

    private static boolean processCommand(Librarian librarian, String command) {
        command = command.toLowerCase();
        if (command.equals("quit")) {
            System.out.println("Thanks for using the library, have a worthwhile day.");
            return false;
        }

        System.out.println(librarian.processRequest(MenuOptions.valueOf(command.toUpperCase())));

        return true;
    }

    public static String createWelcomeMessage() {
        String welcomeMessage = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";

        return welcomeMessage;
    }
}
