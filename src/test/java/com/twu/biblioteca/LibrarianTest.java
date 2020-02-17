package com.twu.biblioteca;
import com.twu.biblioteca.domain.*;
import com.twu.biblioteca.utils.ConsolePrinter;
import com.twu.biblioteca.utils.InputAsker;
import org.junit.Test;

import java.time.Year;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class LibrarianTest {

    @Test
    public void should_return_a_list_of_book_titles() {
        InputAsker inputAsker = mock(InputAsker.class);
        ConsolePrinter printer = mock(ConsolePrinter.class);

        when(inputAsker.askForCatalogue()).thenReturn("book");
        when(inputAsker.askForOption()).thenReturn("quit");

        UserRequestResult expectedResult = new UserRequestResult();
        expectedResult.add(String.format("%-30.30s %-30.30s %-30.30s", "Zen Meditation", "Yaohui Ding", "1965"));
        expectedResult.add(String.format("%-30.30s %-30.30s %-30.30s", "Your Cat Hates You", "Cosmo Kramer", "2018"));
        expectedResult.add(String.format("%-30.30s %-30.30s %-30.30s", "Evolutionary Psychology", "David Buss", "1989"));
        expectedResult.add(String.format("%-30.30s %-30.30s %-30.30s", "Ancient History", "Iulius Caesar", "30"));

        Set<Book> books = new LinkedHashSet<>();

        books.add(new Book("Zen Meditation", Year.of(1965), "Yaohui Ding", null));
        books.add(new Book("Your Cat Hates You", Year.of(2018), "Cosmo Kramer", null));
        books.add(new Book("Evolutionary Psychology", Year.of(1989), "David Buss", null));
        books.add(new Book("Ancient History", Year.of(30), "Iulius Caesar", null));

        Librarian librarian = new Librarian(
                new Catalogue<Book>(books), null, null, inputAsker, printer);
        librarian.processRequest(MenuOptions.LIST);

        verify(printer, times(1)).print(expectedResult);
    }

    @Test
    public void should_return_a_list_of_movie_titles() {
        InputAsker inputAsker = mock(InputAsker.class);
        ConsolePrinter printer = mock(ConsolePrinter.class);

        when(inputAsker.askForCatalogue()).thenReturn("movie");
        when(inputAsker.askForOption()).thenReturn("quit");

        UserRequestResult expectedResult = new UserRequestResult();
        expectedResult.add(String.format("%-25.25s %-25.25s %-25.25s %-25.25s", "Bananas", "Woody Allen", "1970", "8"));
        expectedResult.add(String.format("%-25.25s %-25.25s %-25.25s %-25.25s", "7 Samurai", "Akira Kurosawa", "1957", "9"));
        expectedResult.add(String.format("%-25.25s %-25.25s %-25.25s %-25.25s", "My Home Movie", "Dad", "1994", "not rated"));

        Set<Movie> movies = new LinkedHashSet<>();
        movies.add(new Movie("Bananas", Year.of(1970), "Woody Allen", 8, null));
        movies.add(new Movie("7 Samurai", Year.of(1957), "Akira Kurosawa", 9, null));
        movies.add(new Movie("My Home Movie", Year.of(1994), "Dad", null));

        Librarian librarian = new Librarian(
                null, new Catalogue<Movie>(movies), null, inputAsker, printer);
        librarian.processRequest(MenuOptions.LIST);

        verify(printer, times(1)).print(expectedResult);
    }

    @Test
    public void should_take_book_title_for_rent_request() {
        InputAsker inputAsker = mock(InputAsker.class);
        ConsolePrinter printer = mock(ConsolePrinter.class);

        when(inputAsker.askForCatalogue()).thenReturn("book");
        when(inputAsker.askForOption()).thenReturn("quit");
        when(inputAsker.askForTitle()).thenReturn("Zen Meditation");

        Set<Book> books = new LinkedHashSet<>();

        books.add(new Book("Zen Meditation", Year.of(1965), "Yaohui Ding", null));
        books.add(new Book("Your Cat Hates You", Year.of(2018), "Cosmo Kramer", null));
        books.add(new Book("Evolutionary Psychology", Year.of(1989), "David Buss", null));
        books.add(new Book("Ancient History", Year.of(30), "Iulius Caesar", null));
        Librarian librarian = new Librarian(
                new Catalogue<Book>(books), null, null, inputAsker, printer);
        librarian.processRequest(MenuOptions.RENT);

        UserRequestResult expectedResult = new UserRequestResult();
        expectedResult.add("You have rented the item Zen Meditation");
        expectedResult.add("Thank you! Enjoy it.");

        verify(printer, times(1)).print(expectedResult);
    }

    @Test
    public void should_take_rented_book_off_list() {
        InputAsker inputAsker = mock(InputAsker.class);
        ConsolePrinter printer = mock(ConsolePrinter.class);

        when(inputAsker.askForUserId()).thenReturn("000-0001");
        when(inputAsker.askForPIN()).thenReturn("123456");
        when(inputAsker.askForCatalogue()).thenReturn("book");
        when(inputAsker.askForOption()).thenReturn("list").thenReturn("quit");
        when(inputAsker.askForTitle()).thenReturn("Zen Meditation");

        Set<Book> books = new LinkedHashSet<>();

        books.add(new Book("Zen Meditation", Year.of(1965), "Yaohui Ding", null));
        books.add(new Book("Your Cat Hates You", Year.of(2018), "Cosmo Kramer", null));
        books.add(new Book("Evolutionary Psychology", Year.of(1989), "David Buss", null));
        books.add(new Book("Ancient History", Year.of(30), "Iulius Caesar", null));

        Set<User> userbase = new HashSet<>();
        userbase.add(new User("000-0001", "123456", null, null, null));

        Librarian librarian = new Librarian(
                new Catalogue<Book>(books), null, userbase, inputAsker, printer);
        librarian.logIn();
        librarian.processRequest(MenuOptions.RENT);

        UserRequestResult expectedResult = new UserRequestResult();
        expectedResult.add(String.format("%-30.30s %-30.30s %-30.30s", "Your Cat Hates You", "Cosmo Kramer", "2018"));
        expectedResult.add(String.format("%-30.30s %-30.30s %-30.30s", "Evolutionary Psychology", "David Buss", "1989"));
        expectedResult.add(String.format("%-30.30s %-30.30s %-30.30s", "Ancient History", "Iulius Caesar", "30"));

        verify(printer, times(1)).print(expectedResult);
    }

    @Test
    public void should_put_returned_book_back_on_list() {
        InputAsker inputAsker = mock(InputAsker.class);
        ConsolePrinter printer = mock(ConsolePrinter.class);

        when(inputAsker.askForUserId()).thenReturn("000-0001");
        when(inputAsker.askForPIN()).thenReturn("123456");
        when(inputAsker.askForCatalogue()).thenReturn("book");
        when(inputAsker.askForOption()).thenReturn("return").thenReturn("list").thenReturn("quit");
        when(inputAsker.askForTitle()).thenReturn("Zen Meditation");

        Set<Book> books = new LinkedHashSet<>();

        books.add(new Book("Zen Meditation", Year.of(1965), "Yaohui Ding", null));
        books.add(new Book("Your Cat Hates You", Year.of(2018), "Cosmo Kramer", null));
        books.add(new Book("Evolutionary Psychology", Year.of(1989), "David Buss", null));
        books.add(new Book("Ancient History", Year.of(30), "Iulius Caesar", null));

        Set<User> userbase = new HashSet<>();
        userbase.add(new User("000-0001", "123456", null, null, null));

        Librarian librarian = new Librarian(
                new Catalogue<Book>(books), null, userbase, inputAsker, printer);
        librarian.logIn();
        librarian.processRequest(MenuOptions.RENT);

        UserRequestResult expectedResult = new UserRequestResult();
        expectedResult.add(String.format("%-30.30s %-30.30s %-30.30s", "Zen Meditation", "Yaohui Ding", "1965"));
        expectedResult.add(String.format("%-30.30s %-30.30s %-30.30s", "Your Cat Hates You", "Cosmo Kramer", "2018"));
        expectedResult.add(String.format("%-30.30s %-30.30s %-30.30s", "Evolutionary Psychology", "David Buss", "1989"));
        expectedResult.add(String.format("%-30.30s %-30.30s %-30.30s", "Ancient History", "Iulius Caesar", "30"));

        verify(printer, times(1)).print(expectedResult);
    }

    @Test
    public void should_log_in_user() {
        InputAsker inputAsker = mock(InputAsker.class);
        ConsolePrinter consolePrinter = mock(ConsolePrinter.class);

        when(inputAsker.askForUserId()).thenReturn("000-0001");
        when(inputAsker.askForPIN()).thenReturn("123456");

        Set<User> userbase = new HashSet<>();
        userbase.add(new User("000-0001", "123456", null, null, null));

        Librarian librarian = new Librarian(null, null, userbase, inputAsker, consolePrinter);
        librarian.logIn();

        UserRequestResult expectedResult = new UserRequestResult();
        expectedResult.add("You have successfully logged in");

        verify(consolePrinter, times(1)).print(expectedResult);
    }

    @Test
    public void should_get_user_info() {
        InputAsker inputAsker = mock(InputAsker.class);
        ConsolePrinter printer = mock(ConsolePrinter.class);

        when(inputAsker.askForUserId()).thenReturn("000-0001");
        when(inputAsker.askForPIN()).thenReturn("123456");
        when(inputAsker.askForOption()).thenReturn("quit");

        Set<User> userbase = new HashSet<>();
        userbase.add(new User("000-0001", "123456", "Librarian",
                "librarian@biblioteca.com", "(520) 589-9885"));

        Librarian librarian = new Librarian(null, null, userbase, inputAsker, printer);
        librarian.logIn();

        librarian.processRequest(MenuOptions.USER);
        UserRequestResult expectedResult = new UserRequestResult();
        String expectedUserInfo = "Name: Librarian\nEmail: librarian@biblioteca.com\nPhone: (520) 589-9885";
        expectedResult.add(expectedUserInfo);

        verify(printer, times(1)).print(expectedResult);
    }
}
