package com.system.elements;

import com.system.elements.device.Device;
import com.system.elements.device.DeviceRepository;
import com.system.elements.store.Store;
import com.system.elements.store.StoreRepository;
import com.system.elements.user.User;
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
public class DeviceRepositoryTests {

    @Autowired
    private DeviceRepository repoDevice;

    @Autowired
    private StoreRepository repoStore;

    @Test
    public void testAddDevice() {
        Device device = new Device();

        device.setName("Klawiatura");
        device.setAmount(5);
        device.setDescription("Fajna do klikania");
        device.setStatus("Dostępna");

        Store store = repoStore.findStoreById(1);
        device.setStore(store);

        repoDevice.save(device);
    }
}
