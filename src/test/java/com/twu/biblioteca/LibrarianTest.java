package com.twu.biblioteca;
import org.junit.Test;

import java.time.Year;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LibrarianTest {

    @Test
    public void should_return_a_list_of_titles() {
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

        Librarian librarian = new Librarian(new Catalogue(books), new InputAsker());
        UserRequestResult result = librarian.processRequest(MenuOptions.LIST);

        assertEquals(expectedResult.toString(), result.toString());
    }

    @Test
    public void should_take_book_title_for_rent_request() {
        InputAsker inputAsker = mock(InputAsker.class);
        when(inputAsker.askForName()).thenReturn("Zen Meditation");

        Set<Book> books = new LinkedHashSet<>();

        books.add(new Book("Zen Meditation", Year.of(1965), "Yaohui Ding", true));
        books.add(new Book("Your Cat Hates You", Year.of(2018), "Cosmo Kramer", true));
        books.add(new Book("Evolutionary Psychology", Year.of(1989), "David Buss", true));
        books.add(new Book("Ancient History", Year.of(30), "Iulius Caesar", true));
        Librarian librarian = new Librarian(new Catalogue(books), inputAsker);
        UserRequestResult result = librarian.processRequest(MenuOptions.RENT);

        StringBuffer expectedResult = new StringBuffer();
        expectedResult.append("You have rented the book Zen Meditation\n");
        expectedResult.append("Thank you! Enjoy the book\n");

        assertEquals(expectedResult.toString(), result.toString());
    }

    @Test
    public void should_take_rented_book_off_list() {
        InputAsker inputAsker = mock(InputAsker.class);

        when(inputAsker.askForName()).thenReturn("Zen Meditation");

        Set<Book> books = new LinkedHashSet<>();

        books.add(new Book("Zen Meditation", Year.of(1965), "Yaohui Ding", true));
        books.add(new Book("Your Cat Hates You", Year.of(2018), "Cosmo Kramer", true));
        books.add(new Book("Evolutionary Psychology", Year.of(1989), "David Buss", true));
        books.add(new Book("Ancient History", Year.of(30), "Iulius Caesar", true));
        Librarian librarian = new Librarian(new Catalogue(books), inputAsker);
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

        when(inputAsker.askForName()).thenReturn("Zen Meditation");

        Set<Book> books = new LinkedHashSet<>();

        books.add(new Book("Zen Meditation", Year.of(1965), "Yaohui Ding", true));
        books.add(new Book("Your Cat Hates You", Year.of(2018), "Cosmo Kramer", true));
        books.add(new Book("Evolutionary Psychology", Year.of(1989), "David Buss", true));
        books.add(new Book("Ancient History", Year.of(30), "Iulius Caesar", true));
        Librarian librarian = new Librarian(new Catalogue(books), inputAsker);

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
