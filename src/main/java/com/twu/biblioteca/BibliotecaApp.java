package com.twu.biblioteca;

import com.twu.biblioteca.domain.*;
import com.twu.biblioteca.utils.ConsolePrinter;
import com.twu.biblioteca.utils.InputAsker;

import java.time.Year;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class BibliotecaApp {
    public static void main(String[] args) {
        System.out.println(createWelcomeMessage());

        Catalogue<Book> bookCatalogue = createBookCatalogue();
        Catalogue<Movie> movieCatalogue = createMovieCatalogue();
        Set<User> userbase = createUserBase();
        Librarian librarian = new Librarian(bookCatalogue, movieCatalogue, userbase, new InputAsker(), new ConsolePrinter());

        librarian.logIn();
        librarian.serveUser();
    }

    private static Set<User> createUserBase() {
        Set<User> userbase = new HashSet<>();
        userbase.add(new User("000-0001", "123456",
                "LibrarianBot", "librarian@biblioteca.com", "(800) 000-0001"));
        userbase.add(new User("999-9999", "999999",
                "Heitor F. Inhaquites", "heitor.inhaquites@gmail.com", "(520) 589-9885"));

        return userbase;
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

    public static String createWelcomeMessage() {
        String welcomeMessage = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";

        return welcomeMessage;
    }
}
