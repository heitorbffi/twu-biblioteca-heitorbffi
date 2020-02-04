package com.twu.biblioteca;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LibrarianTest {
    @Test
    public void should_return_list_of_options() {
        List<String> menuList = Librarian.getMenuOptions();

        assertEquals("List", menuList.get(0));
        assertEquals("Rent", menuList.get(1));
        assertEquals("Quit", menuList.get(2));
    }

    @Test
    public void should_return_a_list_of_titles() {
        Librarian librarian = new Librarian(new BookCatalogue(), new InputAsker());
        List<String> listOfTitles = librarian.processRequest("list");

        assertEquals(4, listOfTitles.size());
    }

    @Test
    public void should_take_book_title_for_rent_request() {
        InputAsker inputAsker = mock(InputAsker.class);

        when(inputAsker.askForName()).thenReturn("Zen Meditation");

        Librarian librarian = new Librarian(new BookCatalogue(), inputAsker);

        List<String> rentRequestOutcome = librarian.processRequest("rent");

        assertEquals("You have rented the book Zen Meditation", rentRequestOutcome.get(0));
        assertEquals("Thank you! Enjoy the book", rentRequestOutcome.get(1));
    }

    @Test
    public void should_take_rented_book_off_list() {
        InputAsker inputAsker = mock(InputAsker.class);

        when(inputAsker.askForName()).thenReturn("Zen Meditation");

        Librarian librarian = new Librarian(new BookCatalogue(), inputAsker);

        librarian.processRequest("rent");
        List<String> listOfTitles = librarian.processRequest("list");

        assertEquals(3, listOfTitles.size());
    }
}
