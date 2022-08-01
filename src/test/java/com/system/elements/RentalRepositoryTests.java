package com.system.elements;

import com.system.elements.device.Device;
import com.system.elements.device.DeviceRepository;
import com.system.elements.rental.Rental;
import com.system.elements.rental.RentalRepository;
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

import java.sql.Date;
import java.sql.Time;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RentalRepositoryTests {

    @Autowired
    private DeviceRepository repoDevice;

    @Autowired
    private UserRepository repoUser;

    @Autowired
    private RentalRepository repoRental;

    @Test
    public void testAddDevice() {

        Rental rental = new Rental();

        Device device = repoDevice.findDeviceById(1);

        rental.setDevice(device);
        rental.setAmount(3);

        User user = repoUser.findUserByEmail("email1@test.com");

        rental.setUser(user);
        rental.setRentalDate(Date.valueOf("2022-07-30"));
        rental.setRentalTime(Time.valueOf("18:30:00"));

        repoRental.save(rental);
    }
}
