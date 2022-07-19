package com.system.elements;

import com.system.elements.user.User;
import com.system.elements.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
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
        user.setFirstName("TestoweImie");
        user.setLastName("TestoweNazwisko");
        user.setEmail("email@test.com");
        user.setPhoneNumber(888888888);
        user.setAddress("Testowa 36");
        user.setCity("Testowo");
        user.setPassword("TestTest");

        repoUser.save(user);
    }
}
