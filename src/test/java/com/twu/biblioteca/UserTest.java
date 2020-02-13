package com.twu.biblioteca;

import com.twu.biblioteca.domain.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {
    @Test
    public void should_return_user_info() {
        User user = new User("000-0001", "123456", "Librarian",
                "librarian@biblioteca.com", "(520) 589-9885");
        StringBuffer expectedUserInfo = new StringBuffer();
        expectedUserInfo.append("Name: Librarian\nEmail: librarian@biblioteca.com\nPhone: (520) 589-9885");

        assertEquals(expectedUserInfo.toString(), user.getInfo().toString());
    }
}
