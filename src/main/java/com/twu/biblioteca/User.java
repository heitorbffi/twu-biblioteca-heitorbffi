package com.twu.biblioteca;

import java.util.Objects;

public class User {
    private final String id;
    private final String PIN;

    public User(String id, String PIN) {
        this.id = id;
        this.PIN = PIN;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return PIN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
