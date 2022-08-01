package com.system.elements;

import com.system.elements.device.Device;
import com.system.elements.device.DeviceRepository;
import com.system.elements.mark.Mark;
import com.system.elements.mark.MarkRepository;
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
public class MarkRepositoryTests {

    @Autowired
    private DeviceRepository repoDevice;

    @Autowired
    private UserRepository repoUser;

    @Autowired
    private MarkRepository repoMark;

    @Test
    public void testAddMark() {
        Mark mark = new Mark();

        mark.setValue(5);
        mark.setDescription("Bardzo przyjemna");

        Device device = repoDevice.findDeviceById(1);

        mark.setDevice(device);

        User user = repoUser.findUserByEmail("email1@test.com");

        mark.setUser(user);

        repoMark.save(mark);
    }
}
