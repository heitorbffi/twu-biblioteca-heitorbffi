package com.twu.biblioteca;

public class UserRequestResult {
    private StringBuffer resultMessage = new StringBuffer();

    public void add(String message) {
        resultMessage.append(message + "\n");
    }

    @Override
    public String toString() {
        return resultMessage.toString();
    }
}
