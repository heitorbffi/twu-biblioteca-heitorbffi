package com.twu.biblioteca;

import java.util.Scanner;

public class InputAsker {
    public String askForName() {
        System.out.println("Type in the name of the book");
        Scanner scanner = new Scanner(System.in);
        String bookName = scanner.nextLine();

        return bookName;
    }
}
