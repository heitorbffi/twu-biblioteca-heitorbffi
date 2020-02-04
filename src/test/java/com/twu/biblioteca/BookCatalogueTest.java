package com.twu.biblioteca;

import org.junit.Test;
import java.util.List;

import static org.junit.Assert.assertEquals;

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

        bookCatalogue.rentBook("Zen Meditation");
        List<String> listOfTitles = bookCatalogue.listAvailableBooksInfo();

        assertEquals(3, listOfTitles.size());

        bookCatalogue.rentBook("Zen Meditation");
        listOfTitles = bookCatalogue.listAvailableBooksInfo();

        assertEquals(3, listOfTitles.size());

        bookCatalogue.rentBook("Western Meditation");
        listOfTitles = bookCatalogue.listAvailableBooksInfo();

        assertEquals(3, listOfTitles.size());
    }
}
