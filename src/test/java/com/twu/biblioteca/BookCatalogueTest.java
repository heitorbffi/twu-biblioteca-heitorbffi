package com.twu.biblioteca;

import org.junit.Test;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BookCatalogueTest {

    @Test
    public void shouldReturnAListOfTitles() {
        BookCatalogue bookCatalogue = new BookCatalogue();
        List<String> listOfTitles = bookCatalogue.listBooksInfo();

        assertEquals(listOfTitles.size(), 4);
    }
}
