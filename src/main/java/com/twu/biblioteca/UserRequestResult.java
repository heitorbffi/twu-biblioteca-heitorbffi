package com.twu.biblioteca;

import java.util.Objects;

public class UserRequestResult {
    private StringBuffer resultMessage = new StringBuffer();

    public void add(String message) {
        resultMessage.append(message + "\n");
    }

    public void add(StringBuffer message) {
        resultMessage.append(message + "\n");
    }

    @Override
    public String toString() {
        return resultMessage.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRequestResult that = (UserRequestResult) o;
        return Objects.equals(resultMessage.toString(), that.resultMessage.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(resultMessage);
    }
}
