package com.twu.biblioteca;

import org.junit.Test;

import java.time.Year;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BookTest {

    @Test
    public void changes_available_status_to_rented() {
        Book book = new Book("BookName", Year.of(2020), "Author");
        assertTrue(book.isAvailable());

        book.rent();
        assertFalse(book.isAvailable());
    }
}
