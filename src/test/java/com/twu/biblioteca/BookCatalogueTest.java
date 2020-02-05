package com.twu.biblioteca;

import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;

public class BookCatalogueTest {

    @Test
    public void should_return_a_list_of_titles() {
        BookCatalogue bookCatalogue = new BookCatalogue();
        List<String> listOfTitles = bookCatalogue.listAvailableBooksInfo();

        assertEquals(4, listOfTitles.size());
    }

    @Test
    public void should_reduce_the_list_of_titles_if_book_is_available() {
        BookCatalogue bookCatalogue = new BookCatalogue();

        bookCatalogue.successfullyRentBook("Zen Meditation");
        List<String> listOfTitles = bookCatalogue.listAvailableBooksInfo();

        assertEquals(3, listOfTitles.size());

        bookCatalogue.successfullyRentBook("Zen Meditation");
        listOfTitles = bookCatalogue.listAvailableBooksInfo();

        assertEquals(3, listOfTitles.size());

        bookCatalogue.successfullyRentBook("Western Meditation");
        listOfTitles = bookCatalogue.listAvailableBooksInfo();

        assertEquals(3, listOfTitles.size());
    }

    @Test
    public void is_possible_to_rent_book() {
        BookCatalogue bookCatalogue = new BookCatalogue();

        Boolean rentable = bookCatalogue.successfullyRentBook("Zen Meditation");

        assertTrue(rentable);
    }

    @Test
    public void is_not_possible_to_rent_nonexistent_book() {
        BookCatalogue bookCatalogue = new BookCatalogue();

        Boolean rentable = bookCatalogue.successfullyRentBook("Western Meditation");

        assertFalse(rentable);
    }

    @Test
    public void is_not_possible_to_rent_book_twice() {
        BookCatalogue bookCatalogue = new BookCatalogue();

        bookCatalogue.successfullyRentBook("Zen Meditation");
        Boolean rentable = bookCatalogue.successfullyRentBook("Zen Meditation");

        assertFalse(rentable);
    }
}
