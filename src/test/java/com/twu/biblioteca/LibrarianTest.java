package com.twu.biblioteca;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LibrarianTest {

    @Test
    public void should_return_a_list_of_titles() {
        Librarian librarian = new Librarian(new BookCatalogue(), new InputAsker());
        List<String> listOfTitles = librarian.processRequest(MenuOptions.LIST);

        assertEquals(4, listOfTitles.size());
    }

    @Test
    public void should_take_book_title_for_rent_request() {
        InputAsker inputAsker = mock(InputAsker.class);

        when(inputAsker.askForName()).thenReturn("Zen Meditation");

        Librarian librarian = new Librarian(new BookCatalogue(), inputAsker);

        List<String> rentRequestOutcome = librarian.processRequest(MenuOptions.RENT);

        assertEquals("You have rented the book Zen Meditation", rentRequestOutcome.get(0));
        assertEquals("Thank you! Enjoy the book", rentRequestOutcome.get(1));
    }

    @Test
    public void should_take_rented_book_off_list() {
        InputAsker inputAsker = mock(InputAsker.class);

        when(inputAsker.askForName()).thenReturn("Zen Meditation");

        Librarian librarian = new Librarian(new BookCatalogue(), inputAsker);
        List<String> listOfTitles = librarian.processRequest(MenuOptions.LIST);

        assertEquals(4, listOfTitles.size());

        librarian.processRequest(MenuOptions.RENT);
        listOfTitles = librarian.processRequest(MenuOptions.LIST);

        assertEquals(3, listOfTitles.size());
    }

    @Test
    public void should_put_returned_book_back_on_list() {
        InputAsker inputAsker = mock(InputAsker.class);

        when(inputAsker.askForName()).thenReturn("Zen Meditation");

        Librarian librarian = new Librarian(new BookCatalogue(), inputAsker);

        librarian.processRequest(MenuOptions.RENT);
        List<String> listOfTitles = librarian.processRequest(MenuOptions.LIST);
        assertEquals(3, listOfTitles.size());

        librarian.processRequest(MenuOptions.RETURN);
        listOfTitles = librarian.processRequest(MenuOptions.LIST);

        assertEquals(4, listOfTitles.size());
    }
}
