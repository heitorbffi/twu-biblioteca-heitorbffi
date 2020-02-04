package com.twu.biblioteca;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.List;

public class MenuTest {
    @Test
    public void shouldReturnListOfOptions() {
        List<String> menuList = Menu.getMenuOptions();
        assertEquals(menuList.get(0), "List");
    }

    @Test
    public void shouldReturnAListOfTitles() {
        Menu menu = new Menu(new BookCatalogue());
        List<String> listOfTitles = menu.processRequest("list");
        assertEquals(listOfTitles.size(), 4);
    }
}
