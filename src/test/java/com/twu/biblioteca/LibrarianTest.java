package com.twu.biblioteca;
import org.junit.Test;

import java.time.Year;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LibrarianTest {

    @Test
    public void should_return_a_list_of_book_titles() {
        InputAsker inputAsker = mock(InputAsker.class);

        when(inputAsker.askForCatalogue()).thenReturn(CatalogueOptions.BOOK);

        StringBuffer expectedResult = new StringBuffer();
        expectedResult.append(String.format("%-30.30s %-30.30s %-30.30s\n", "Zen Meditation", "Yaohui Ding", "1965"));
        expectedResult.append(String.format("%-30.30s %-30.30s %-30.30s\n", "Your Cat Hates You", "Cosmo Kramer", "2018"));
        expectedResult.append(String.format("%-30.30s %-30.30s %-30.30s\n", "Evolutionary Psychology", "David Buss", "1989"));
        expectedResult.append(String.format("%-30.30s %-30.30s %-30.30s\n", "Ancient History", "Iulius Caesar", "30"));

        Set<Book> books = new LinkedHashSet<>();

        books.add(new Book("Zen Meditation", Year.of(1965), "Yaohui Ding", true));
        books.add(new Book("Your Cat Hates You", Year.of(2018), "Cosmo Kramer", true));
        books.add(new Book("Evolutionary Psychology", Year.of(1989), "David Buss", true));
        books.add(new Book("Ancient History", Year.of(30), "Iulius Caesar", true));

        Librarian librarian = new Librarian(
                new Catalogue<Book>(books), new Catalogue<Movie>(new LinkedHashSet<Movie>()), inputAsker);
        UserRequestResult result = librarian.processRequest(MenuOptions.LIST);

        assertEquals(expectedResult.toString(), result.toString());
    }

    @Test
    public void should_return_a_list_of_movie_titles() {
        InputAsker inputAsker = mock(InputAsker.class);

        when(inputAsker.askForCatalogue()).thenReturn(CatalogueOptions.MOVIE);

        StringBuffer expectedResult = new StringBuffer();
        expectedResult.append(String.format("%-25.25s %-25.25s %-25.25s %-25.25s\n", "Bananas", "Woody Allen", "1970", "8"));
        expectedResult.append(String.format("%-25.25s %-25.25s %-25.25s %-25.25s\n", "7 Samurai", "Akira Kurosawa", "1957", "9"));
        expectedResult.append(String.format("%-25.25s %-25.25s %-25.25s %-25.25s\n", "My Home Movie", "Dad", "1994", "not rated"));

        Set<Movie> movies = new LinkedHashSet<>();
        movies.add(new Movie("Bananas", Year.of(1970), "Woody Allen", true, 8));
        movies.add(new Movie("7 Samurai", Year.of(1957), "Akira Kurosawa", true, 9));
        movies.add(new Movie("My Home Movie", Year.of(1994), "Dad", true));

        Librarian librarian = new Librarian(
                new Catalogue<Book>(new LinkedHashSet<Book>()), new Catalogue<Movie>(movies), inputAsker);
        UserRequestResult result = librarian.processRequest(MenuOptions.LIST);

        assertEquals(expectedResult.toString(), result.toString());
    }

    @Test
    public void should_take_book_title_for_rent_request() {
        InputAsker inputAsker = mock(InputAsker.class);

        when(inputAsker.askForTitle()).thenReturn("Zen Meditation");
        when(inputAsker.askForCatalogue()).thenReturn(CatalogueOptions.BOOK);

        Set<Book> books = new LinkedHashSet<>();

        books.add(new Book("Zen Meditation", Year.of(1965), "Yaohui Ding", true));
        books.add(new Book("Your Cat Hates You", Year.of(2018), "Cosmo Kramer", true));
        books.add(new Book("Evolutionary Psychology", Year.of(1989), "David Buss", true));
        books.add(new Book("Ancient History", Year.of(30), "Iulius Caesar", true));
        Librarian librarian = new Librarian(
                new Catalogue<Book>(books), new Catalogue<Movie>(new LinkedHashSet<Movie>()), inputAsker);
        UserRequestResult result = librarian.processRequest(MenuOptions.RENT);

        StringBuffer expectedResult = new StringBuffer();
        expectedResult.append("You have rented the item Zen Meditation\n");
        expectedResult.append("Thank you! Enjoy it.\n");

        assertEquals(expectedResult.toString(), result.toString());
    }

    @Test
    public void should_take_rented_book_off_list() {
        InputAsker inputAsker = mock(InputAsker.class);

        when(inputAsker.askForTitle()).thenReturn("Zen Meditation");
        when(inputAsker.askForCatalogue()).thenReturn(CatalogueOptions.BOOK);

        Set<Book> books = new LinkedHashSet<>();

        books.add(new Book("Zen Meditation", Year.of(1965), "Yaohui Ding", true));
        books.add(new Book("Your Cat Hates You", Year.of(2018), "Cosmo Kramer", true));
        books.add(new Book("Evolutionary Psychology", Year.of(1989), "David Buss", true));
        books.add(new Book("Ancient History", Year.of(30), "Iulius Caesar", true));
        Librarian librarian = new Librarian(
                new Catalogue<Book>(books), new Catalogue<Movie>(new LinkedHashSet<Movie>()), inputAsker);
        librarian.processRequest(MenuOptions.RENT);
        UserRequestResult result = librarian.processRequest(MenuOptions.LIST);

        StringBuffer expectedResult = new StringBuffer();
        expectedResult.append(String.format("%-30.30s %-30.30s %-30.30s\n", "Your Cat Hates You", "Cosmo Kramer", "2018"));
        expectedResult.append(String.format("%-30.30s %-30.30s %-30.30s\n", "Evolutionary Psychology", "David Buss", "1989"));
        expectedResult.append(String.format("%-30.30s %-30.30s %-30.30s\n", "Ancient History", "Iulius Caesar", "30"));

        assertEquals(expectedResult.toString(), result.toString());
    }

    @Test
    public void should_put_returned_book_back_on_list() {
        InputAsker inputAsker = mock(InputAsker.class);

        when(inputAsker.askForTitle()).thenReturn("Zen Meditation");
        when(inputAsker.askForCatalogue()).thenReturn(CatalogueOptions.BOOK);

        Set<Book> books = new LinkedHashSet<>();

        books.add(new Book("Zen Meditation", Year.of(1965), "Yaohui Ding", true));
        books.add(new Book("Your Cat Hates You", Year.of(2018), "Cosmo Kramer", true));
        books.add(new Book("Evolutionary Psychology", Year.of(1989), "David Buss", true));
        books.add(new Book("Ancient History", Year.of(30), "Iulius Caesar", true));
        Librarian librarian = new Librarian(
                new Catalogue<Book>(books), new Catalogue<Movie>(new LinkedHashSet<Movie>()), inputAsker);

        librarian.processRequest(MenuOptions.RENT);
        librarian.processRequest(MenuOptions.RETURN);

        StringBuffer expectedResult = new StringBuffer();
        expectedResult.append(String.format("%-30.30s %-30.30s %-30.30s\n", "Zen Meditation", "Yaohui Ding", "1965"));
        expectedResult.append(String.format("%-30.30s %-30.30s %-30.30s\n", "Your Cat Hates You", "Cosmo Kramer", "2018"));
        expectedResult.append(String.format("%-30.30s %-30.30s %-30.30s\n", "Evolutionary Psychology", "David Buss", "1989"));
        expectedResult.append(String.format("%-30.30s %-30.30s %-30.30s\n", "Ancient History", "Iulius Caesar", "30"));

        UserRequestResult result = librarian.processRequest(MenuOptions.LIST);

        assertEquals(expectedResult.toString(), result.toString());
    }
}
