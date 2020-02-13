package com.twu.biblioteca.utils;

import com.twu.biblioteca.domain.UserRequestResult;

public class ConsolePrinter {
    public void print(UserRequestResult result) {
        System.out.println(result);
    }

    public void print(String info) {
        System.out.println(info);
    }
}
