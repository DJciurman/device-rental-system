package com.system.elements;

import com.system.elements.device.Device;
import com.system.elements.device.DeviceRepository;
import com.system.elements.message.Message;
import com.system.elements.message.MessageRepository;
import com.system.elements.store.Store;
import com.system.elements.store.StoreRepository;
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
public class MessageRepositoryTests {

    @Autowired
    private UserRepository repoUser;

    @Autowired
    private MessageRepository repoMessage;

    @Test
    public void testAddMessage() {
        Message message = new Message();

        message.setDescription("Dziękujemy za wybranie naszych usług");

        User user = repoUser.findUserByEmail("email@test.com");

        message.setSender(user);

        user = repoUser.findUserByEmail("email1@test.com");

        message.setReceiver(user);

        repoMessage.save(message);
    }
}
