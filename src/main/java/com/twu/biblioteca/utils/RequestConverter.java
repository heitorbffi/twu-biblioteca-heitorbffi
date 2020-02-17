package com.twu.biblioteca.utils;

import com.twu.biblioteca.domain.CatalogueOptions;
import com.twu.biblioteca.domain.MenuOptions;

public class RequestConverter {
    public static MenuOptions convertToMenuOption(String command) throws IllegalArgumentException {
        MenuOptions option = MenuOptions.valueOf(command.toUpperCase());

        return option;
    }

    public static CatalogueOptions convertToCatalogueOption(String command) throws IllegalArgumentException {
        CatalogueOptions option = CatalogueOptions.valueOf(command.toUpperCase());

        return option;
    }
}
