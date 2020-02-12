package com.twu.biblioteca;
import java.util.Objects;

public class User {
    private final String id;
    private String PIN;
    private String name;
    private String email;
    private String phone;

    public User(String id, String PIN, String name, String email, String phone) {
        this.id = id;
        this.PIN = PIN;
        this.name = name;
        this.email = email;
        this.phone = phone;
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

    public StringBuffer getInfo() {
        StringBuffer info = new StringBuffer();
        info.append("Name: ").append(name).append("\n");
        info.append("Email: ").append(email).append("\n");
        info.append("Phone: ").append(phone);

        return info;
    }
}
