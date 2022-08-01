package com.system.elements;

import com.system.elements.user.User;
import com.system.elements.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired
    private UserRepository repoUser;

    @Test
    public void testAddUser() {
        User user = new User();
        user.setFirstName("Adam");
        user.setLastName("Kowalski");
        user.setEmail("email@test.com");
        user.setPhoneNumber(888888888);
        user.setAddress("Tęczowa 36");
        user.setCity("Tarnów");

        user.setPassword("TestTest");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        repoUser.save(user);
    }

    @Test
    public void testAddAnotherUser() {
        User user = new User();
        user.setFirstName("Damian");
        user.setLastName("Pomorski");
        user.setEmail("email1@test.com");
        user.setPhoneNumber(888888888);
        user.setAddress("Wąska 385");
        user.setCity("Kołobrzeg");

        user.setPassword("TestTest");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        repoUser.save(user);
    }
}
