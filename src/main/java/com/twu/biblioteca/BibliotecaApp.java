package com.twu.biblioteca;

import java.util.List;

public class BibliotecaApp {

    public static void main(String[] args) {
        System.out.println(createWelcomeMessage());
        BookCatalogue bookCatalogue = new BookCatalogue();
        Menu menu = new Menu(bookCatalogue);

        printBooksInfo(menu.processRequest("list"));
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
