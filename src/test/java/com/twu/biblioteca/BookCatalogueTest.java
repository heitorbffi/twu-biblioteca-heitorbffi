package com.twu.biblioteca;

import org.junit.Test;

import java.time.Year;
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
        Book book = new Book("Zen Meditation", Year.of(1965), "Yaohui Ding", true);

        List<String> listOfTitles = bookCatalogue.listAvailableBooksInfo();
        assertEquals(4, listOfTitles.size());

        bookCatalogue.rent(book);
        listOfTitles = bookCatalogue.listAvailableBooksInfo();

        assertEquals(3, listOfTitles.size());
    }

    @Test
    public void should_not_reduce_the_list_of_titles_if_book_is_rented_twice() {
        BookCatalogue bookCatalogue = new BookCatalogue();
        Book book = new Book("Zen Meditation", Year.of(1965), "Yaohui Ding", true);

        List<String> listOfTitles = bookCatalogue.listAvailableBooksInfo();
        assertEquals(4, listOfTitles.size());

        bookCatalogue.rent(book);
        bookCatalogue.rent(book);
        listOfTitles = bookCatalogue.listAvailableBooksInfo();

        assertEquals(3, listOfTitles.size());
    }

    @Test
    public void should_not_reduce_the_list_of_titles_if_book_is_not_in_catalogue() {
        BookCatalogue bookCatalogue = new BookCatalogue();
        Book book = new Book("Western Meditation", Year.of(1965), "Yaohui Ding", true);

        List<String> listOfTitles = bookCatalogue.listAvailableBooksInfo();
        assertEquals(4, listOfTitles.size());

        bookCatalogue.rent(book);
        listOfTitles = bookCatalogue.listAvailableBooksInfo();

        assertEquals(4, listOfTitles.size());
    }

    @Test
    public void should_not_increase_the_list_of_titles_if_book_returned_is_not_in_catalogue() {
        BookCatalogue bookCatalogue = new BookCatalogue();
        Book book = new Book("Western Meditation", Year.of(1965), "Yaohui Ding", true);

        List<String> listOfTitles = bookCatalogue.listAvailableBooksInfo();
        assertEquals(4, listOfTitles.size());

        bookCatalogue.giveBack(book);
        listOfTitles = bookCatalogue.listAvailableBooksInfo();

        assertEquals(4, listOfTitles.size());
    }

    @Test
    public void should_not_increase_the_list_of_titles_if_book_is_returned_twice() {
        BookCatalogue bookCatalogue = new BookCatalogue();
        Book book = new Book("Zen Meditation", Year.of(1965), "Yaohui Ding", true);
        bookCatalogue.rent(book);

        List<String> listOfTitles = bookCatalogue.listAvailableBooksInfo();
        assertEquals(3, listOfTitles.size());

        bookCatalogue.giveBack(book);
        bookCatalogue.giveBack(book);
        listOfTitles = bookCatalogue.listAvailableBooksInfo();

        assertEquals(4, listOfTitles.size());
    }

    @Test
    public void should_return_book_by_name() {
        BookCatalogue bookCatalogue = new BookCatalogue();
        Book zenMeditation = bookCatalogue.findByTitle("Zen Meditation");
        Book expectedBook = new Book("Zen Meditation", Year.of(1965), "Yaohui Ding", true);

        assertEquals(zenMeditation, expectedBook);
    }

    @Test
    public void should_return_null_when_book_does_not_exist() {
        BookCatalogue bookCatalogue = new BookCatalogue();
        Book actualBook = bookCatalogue.findByTitle("Invalid name");

        assertNull(actualBook);
    }

    @Test
    public void should_find_book_even_if_title_is_in_lower_case() {
        BookCatalogue bookCatalogue = new BookCatalogue();
        Book actualBook = bookCatalogue.findByTitle("zen meditation");
        Book expectedBook = new Book("Zen Meditation", Year.of(1965), "Yaohui Ding", true);

        assertEquals(expectedBook, actualBook);
    }

    @Test
    public void should_show_that_book_that_was_rented_is_not_available() {
        Book book = new Book("Zen Meditation", Year.of(1965), "Yaohui Ding", true);
        BookCatalogue bookCatalogue = new BookCatalogue();
        bookCatalogue.rent(book);
        Book foundBook = bookCatalogue.findByTitle("zen meditation");

        assertFalse(foundBook.isAvailable());
    }
}
