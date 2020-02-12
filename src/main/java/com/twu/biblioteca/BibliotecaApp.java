package com.twu.biblioteca;

import java.time.Year;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class BibliotecaApp {

    public static void main(String[] args) {
        System.out.println(createWelcomeMessage());

        Catalogue<Book> bookCatalogue = createBookCatalogue();
        Catalogue<Movie> movieCatalogue = createMovieCatalogue();
        Set<User> userbase = new HashSet<>();
        userbase.add(new User("000-0001", "123456"));
        Librarian librarian = new Librarian(bookCatalogue, movieCatalogue, userbase, new InputAsker(), new ConsolePrinter());

        librarian.logIn();

        Scanner scanner = new Scanner(System.in);

        Boolean askForInput = true;
        while (askForInput) {
            showMenu();
            String command = scanner.next();
            askForInput = processCommand(librarian, command);
        }
    }

    private static Catalogue<Book> createBookCatalogue() {
        Set<Book> books = new LinkedHashSet<>();

        books.add(new Book("Zen Meditation", Year.of(1965), "Yaohui Ding", null));
        books.add(new Book("Your Cat Hates You", Year.of(2018), "Cosmo Kramer", null));
        books.add(new Book("Evolutionary Psychology", Year.of(1989), "David Buss", null));
        books.add(new Book("Ancient History", Year.of(30), "Iulius Caesar", null));

        return new Catalogue<>(books);
    }

    private static Catalogue<Movie> createMovieCatalogue() {
        Set<Movie> movies = new LinkedHashSet<>();

        movies.add(new Movie("Bananas", Year.of(1970), "Woody Allen", 8, null));
        movies.add(new Movie("7 Samurai", Year.of(1957), "Akira Kurosawa", 9, null));
        movies.add(new Movie("My Home Movie", Year.of(1994), "Dad", null));

        return new Catalogue<>(movies);
    }

    private static void showMenu() {
        System.out.println("Choose an option from the menu below, by typing it:");
        for (MenuOptions option : MenuOptions.values()) {
            System.out.println(option);
        }
    }

    //task this to librarian
    private static boolean processCommand(Librarian librarian, String command) {
        command = command.toLowerCase();
        if (MenuOptions.valueOf(command.toUpperCase()) == MenuOptions.QUIT) {
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
