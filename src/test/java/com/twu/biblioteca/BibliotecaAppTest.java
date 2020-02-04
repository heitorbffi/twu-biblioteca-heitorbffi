package com.twu.biblioteca;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BibliotecaAppTest {
    @Test
    public void welcome_message_is_correct() {
        String expectedWelcomeMessage = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
        assertEquals(expectedWelcomeMessage, BibliotecaApp.createWelcomeMessage());
    }
}
