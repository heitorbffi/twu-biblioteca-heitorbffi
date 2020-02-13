package com.twu.biblioteca;

import com.twu.biblioteca.domain.Book;
import com.twu.biblioteca.domain.User;
import org.junit.Test;

import java.time.Year;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class BookTest {

    @Test
    public void changes_available_status_to_rented() {
        User user = mock(User.class);

        Book book = new Book("BookName", Year.of(2020), "Author", null);
        assertTrue(book.isAvailable());

        book.rent(user);
        assertFalse(book.isAvailable());
    }

    @Test
    public void changes_available_status_to_returned() {
        User user = new User("000-0001", "123456", null, null, null);

        Book book = new Book("BookName", Year.of(2020), "Author", null);
        book.rent(user);

        book.giveBack(user);
        assertTrue(book.isAvailable());
    }

    @Test
    public void does_not_change_status_to_available_when_user_is_different() {
        User user = new User("000-0001", "123456", null, null, null);
        User wrongUser = new User("000-0002", "123456", null, null, null);

        Book book = new Book("BookName", Year.of(2020), "Author", null);

        book.rent(user);
        book.giveBack(wrongUser);

        assertFalse(book.isAvailable());
    }
}
