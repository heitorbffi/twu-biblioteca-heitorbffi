package com.twu.biblioteca;

import org.junit.Test;

import java.time.Year;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class CatalogueTest {
    @Test
    public void should_list_books() {
        Set<Book> books = new LinkedHashSet<>();
        books.add(new Book("Zen Meditation", Year.of(1965), "Yaohui Ding", null));
        books.add(new Book("Your Cat Hates You", Year.of(2018), "Cosmo Kramer", null));
        books.add(new Book("Evolutionary Psychology", Year.of(1989), "David Buss", null));
        books.add(new Book("Ancient History", Year.of(30), "Iulius Caesar", null));

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(String.format("%-30.30s %-30.30s %-30.30s", "Zen Meditation", "Yaohui Ding", "1965"));
        expectedResult.add(String.format("%-30.30s %-30.30s %-30.30s", "Your Cat Hates You", "Cosmo Kramer", "2018"));
        expectedResult.add(String.format("%-30.30s %-30.30s %-30.30s", "Evolutionary Psychology", "David Buss", "1989"));
        expectedResult.add(String.format("%-30.30s %-30.30s %-30.30s", "Ancient History", "Iulius Caesar", "30"));

        Catalogue<Book> bookCatalogue = new Catalogue<>(books);

        assertEquals(expectedResult, bookCatalogue.listAvailableItemsInfo());
    }

    @Test
    public void should_list_movies() {
        Set<Movie> movies = new LinkedHashSet<>();
        movies.add(new Movie("Bananas", Year.of(1970), "Woody Allen", 8, null));
        movies.add(new Movie("7 Samurai", Year.of(1957), "Akira Kurosawa", 9, null));
        movies.add(new Movie("My Home Movie", Year.of(1994), "Dad", null));

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(String.format("%-25.25s %-25.25s %-25.25s %-25.25s", "Bananas", "Woody Allen", "1970", "8"));
        expectedResult.add(String.format("%-25.25s %-25.25s %-25.25s %-25.25s", "7 Samurai", "Akira Kurosawa", "1957", "9"));
        expectedResult.add(String.format("%-25.25s %-25.25s %-25.25s %-25.25s", "My Home Movie", "Dad", "1994", "not rated"));

        Catalogue<Movie> bookCatalogue = new Catalogue<>(movies);

        assertEquals(expectedResult, bookCatalogue.listAvailableItemsInfo());
    }

    @Test
    public void should_rent_movie() {
        Set<Borrowable> movies = new LinkedHashSet<>();

        Borrowable movieToRent = new Movie("Bananas", Year.of(1970), "Woody Allen", 8, null);
        movies.add(movieToRent);

        Catalogue<Borrowable> bookCatalogue = new Catalogue<>(movies);
        User user = new User("000-0001", "123456");

        bookCatalogue.rent(user, movieToRent);

        assertFalse(movieToRent.isAvailable());
    }

    @Test
    public void should_return_movie() {
        Set<Borrowable> movies = new LinkedHashSet<>();

        Borrowable movieToRent = new Movie("Bananas", Year.of(1970), "Woody Allen", 8, null);
        movies.add(movieToRent);

        Catalogue<Borrowable> bookCatalogue = new Catalogue<>(movies);
        User user = new User("000-0001", "123456");

        bookCatalogue.rent(user, movieToRent);
        bookCatalogue.giveBack(user, movieToRent);

        assertTrue(movieToRent.isAvailable());
    }
}
