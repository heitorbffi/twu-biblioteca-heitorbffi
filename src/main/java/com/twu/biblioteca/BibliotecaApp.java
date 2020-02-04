package com.twu.biblioteca;

import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {

    public static void main(String[] args) {
        System.out.println(createWelcomeMessage());
        BookCatalogue bookCatalogue = new BookCatalogue();
        Menu menu = new Menu(bookCatalogue);

        Scanner scanner = new Scanner(System.in);

        Boolean askForInput = true;
        while (askForInput) {
            showMenu(menu);
            String command = scanner.next();
            askForInput = processCommand(menu, command);
        }
    }

    private static void showMenu(Menu menu) {
        System.out.println("Choose an option from the menu below, by typing it:");
        for (String option : menu.getMenuOptions()) {
            System.out.println(option);
        }
    }

    private static Boolean processCommand(Menu menu, String command) {
        command = command.toLowerCase();
        if (command.equals("quit")) {
            System.out.println("Thanks for using the library, have a worthwhile day.");
            return false;
        }

        printBooksInfo(menu.processRequest(command));

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
