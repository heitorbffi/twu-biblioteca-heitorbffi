package com.twu.biblioteca;

import java.util.List;

public class BibliotecaApp {

    public static void main(String[] args) {
        System.out.println(createWelcomeMessage());
        BookCatalogue bookCatalogue = new BookCatalogue();
        bookCatalogue.generateBooks();

        printTitles(bookCatalogue.listTitles());
    }

    public static String createWelcomeMessage() {
        String welcomeMessage = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";

        return welcomeMessage;
    }

    public static void printTitles(List<String> titles) {
        for (String title : titles) {
            System.out.println(title);
        }
    }
}
