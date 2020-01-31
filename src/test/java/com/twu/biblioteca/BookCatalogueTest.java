package com.twu.biblioteca;

import org.junit.Test;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class BookCatalogueTest {

    @Test
    public void shouldReturnAListOfTitles() {
        BookCatalogue bookCatalogue = new BookCatalogue();
        bookCatalogue.generateBooks();
        List<String> listOfTitles = bookCatalogue.listBooksInfo();

        assertEquals(listOfTitles.size(), 4);
    }
}
