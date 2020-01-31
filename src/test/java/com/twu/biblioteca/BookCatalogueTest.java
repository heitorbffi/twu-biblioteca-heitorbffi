package com.twu.biblioteca;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class BookCatalogueTest {

    @Test
    public void shouldReturnAListOfTitles() {
        List<String> expectedListOfTitles = new ArrayList<>();
        expectedListOfTitles.add("Zen Meditation");
        expectedListOfTitles.add("Your Cat Hates You");
        expectedListOfTitles.add("Evolutionary Psychology");
        expectedListOfTitles.add("Ancient History");

        BookCatalogue bookCatalogue = new BookCatalogue();
        bookCatalogue.generateBooks();
        List<String> listOfTitles = bookCatalogue.listTitles();

        assertThat(listOfTitles, hasItem("Zen Meditation"));
        assertThat(listOfTitles, hasItem("Your Cat Hates You"));
        assertThat(listOfTitles, hasItem("Evolutionary Psychology"));
        assertThat(listOfTitles, hasItem("Ancient History"));
        assertEquals(listOfTitles.size(), 4);
    }
}
