package com.system.elements;

import com.system.elements.device.Device;
import com.system.elements.device.DeviceRepository;
import com.system.elements.employee.Employee;
import com.system.elements.employee.EmployeeRepository;
import com.system.elements.mark.Mark;
import com.system.elements.mark.MarkRepository;
import com.system.elements.message.Message;
import com.system.elements.message.MessageRepository;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import java.sql.Date;
import java.sql.Time;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ElementsRepositoryTests {

    @Autowired
    private DeviceRepository repoDevice;

    @Autowired
    private StoreRepository repoStore;

    @Autowired
    private UserRepository repoUser;

    @Autowired
    private EmployeeRepository repoEmployee;

    @Autowired
    private MessageRepository repoMessage;

    @Autowired
    private RentalRepository repoRental;

    @Autowired
    private MarkRepository repoMark;

    @Test
    public void testAddAll() {

        //User - User
        User user = new User();
        user.setFirstName("Tomasz");
        user.setLastName("Klient");
        user.setEmail("klient@test.com");
        user.setPhoneNumber(548265793);
        user.setAddress("Malinowa 39");
        user.setCity("Lublin");

        user.setPassword("klientklient");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        repoUser.save(user);


        //User - Pracownik
        user = new User();
        user.setFirstName("Stanisław");
        user.setLastName("Pracownik");
        user.setEmail("pracownik@test.com");
        user.setPhoneNumber(159456753);
        user.setAddress("Ceglana 223");
        user.setCity("Zakopane");

        user.setPassword("pracownikpracownik");

        encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        repoUser.save(user);


        //User - Administrator
        user = new User();
        user.setFirstName("Marek");
        user.setLastName("Administrator");
        user.setEmail("administrator@test.com");
        user.setPhoneNumber(648593145);
        user.setAddress("Karaibska 3");
        user.setCity("Warszawa");

        user.setPassword("administratoradministrator");

        encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        repoUser.save(user);


        //Store - Sklep 1
        Store store = new Store();
        store.setName("Sklep 1");
        store.setAddress("Warszawska 24");
        store.setCity("Kraków");

        repoStore.save(store);


        //Store - Sklep 2
        store = new Store();
        store.setName("Sklep 2");
        store.setAddress("Krakowska 42");
        store.setCity("Warszawa");

        repoStore.save(store);


        //Employee - Nadanie uprawnień pracownika
        Employee employee = new Employee();

        store = repoStore.findStoreById(1);
        employee.setStore(store);

        employee.setRole("Pracownik");

        user = repoUser.findUserByEmail("pracownik@test.com");
        employee.setUser(user);

        repoEmployee.save(employee);


        //Employee - Nadanie uprawnień administratora
        employee = new Employee();

        employee.setRole("Administrator");

        user = repoUser.findUserByEmail("administrator@test.com");
        employee.setUser(user);

        repoEmployee.save(employee);


        //Device - Sklep 1 - Klawiatura
        Device device = new Device();

        store = repoStore.findStoreById(1);
        device.setStore(store);

        device.setName("Klawiatura");
        device.setStatus("Dostępny");
        device.setDescription("Mechaniczna klawiatura");
        device.setAmount(5);

        repoDevice.save(device);


        //Device - Sklep 1 - Myszka
        device = new Device();

        store = repoStore.findStoreById(1);
        device.setStore(store);

        device.setName("Myszka");
        device.setStatus("Dostępny");
        device.setDescription("Posiada dodatkowe przyciski");
        device.setAmount(10);

        repoDevice.save(device);


        //Device - Sklep 1 - Monitor
        device = new Device();

        store = repoStore.findStoreById(1);
        device.setStore(store);

        device.setName("Monitor");
        device.setStatus("Dostępny");
        device.setDescription("Posiada zakrzywiony ekran");
        device.setAmount(2);

        repoDevice.save(device);


        //Device - Sklep 1 - Słuchawki
        device = new Device();

        store = repoStore.findStoreById(1);
        device.setStore(store);

        device.setName("Słuchawki");
        device.setStatus("Niedostępny");
        device.setDescription("W zestawie dodatkowe oprogramowanie");
        device.setAmount(0);

        repoDevice.save(device);


        //Device - Sklep 1 - Kamera
        device = new Device();

        store = repoStore.findStoreById(1);
        device.setStore(store);

        device.setName("Kamera");
        device.setStatus("Dostępny");
        device.setDescription("Idealna do internetowych rozmów");
        device.setAmount(15);

        repoDevice.save(device);


        //Device - Sklep 2 - Klawiatura
        device = new Device();

        store = repoStore.findStoreById(2);
        device.setStore(store);

        device.setName("Klawiatura");
        device.setStatus("Dostępny");
        device.setDescription("Mechaniczna klawiatura");
        device.setAmount(3);

        repoDevice.save(device);


        //Device - Sklep 2 - Myszka
        device = new Device();

        store = repoStore.findStoreById(2);
        device.setStore(store);

        device.setName("Myszka");
        device.setStatus("Dostępny");
        device.setDescription("Posiada dodatkowe przyciski");
        device.setAmount(6);

        repoDevice.save(device);


        //Device - Sklep 2 - Monitor
        device = new Device();

        store = repoStore.findStoreById(2);
        device.setStore(store);

        device.setName("Monitor");
        device.setStatus("Dostępny");
        device.setDescription("Posiada zakrzywiony ekran");
        device.setAmount(5);

        repoDevice.save(device);


        //Device - Sklep 2 - Słuchawki
        device = new Device();

        store = repoStore.findStoreById(2);
        device.setStore(store);

        device.setName("Słuchawki");
        device.setStatus("Dostępny");
        device.setDescription("W zestawie dodatkowe oprogramowanie");
        device.setAmount(1);

        repoDevice.save(device);


        //Device - Sklep 2 - Kamera
        device = new Device();

        store = repoStore.findStoreById(2);
        device.setStore(store);

        device.setName("Kamera");
        device.setStatus("Dostępny");
        device.setDescription("Idealna do internetowych rozmów");
        device.setAmount(20);

        repoDevice.save(device);


        //Message - Pracownik do Klienta
        Message message = new Message();

        user = repoUser.findUserByEmail("klient@test.com");
        message.setReceiver(user);

        user = repoUser.findUserByEmail("pracownik@test.com");
        message.setSender(user);

        message.setDescription("Dziękujemy za skorzystanie z naszych usług");

        repoMessage.save(message);


        //Rental - Klient -> Słuchawki - Sklep 1 (wypożyczenie)
        Rental rental = new Rental();

        user = repoUser.findUserByEmail("klient@test.com");
        rental.setUser(user);

        device = repoDevice.findDeviceById(4);
        rental.setDevice(device);

        rental.setAmount(2);
        rental.setRentalDate(Date.valueOf("2022-07-30"));
        rental.setRentalTime(Time.valueOf("18:30:00"));

        repoRental.save(rental);


        //Rental - Klient -> Kamera - Sklep 1 (wypożyczenie ze zwrotem)
        rental = new Rental();

        user = repoUser.findUserByEmail("klient@test.com");
        rental.setUser(user);

        device = repoDevice.findDeviceById(5);
        rental.setDevice(device);

        rental.setAmount(5);
        rental.setRentalDate(Date.valueOf("2022-07-30"));
        rental.setRentalTime(Time.valueOf("18:30:00"));

        rental.setReturnDate(Date.valueOf("2022-08-01"));
        rental.setReturnTime(Time.valueOf("18:30:00"));

        repoRental.save(rental);


        //Mark - Klient -> Ocena Mikrofonu
        Mark mark = new Mark();

        user = repoUser.findUserByEmail("klient@test.com");
        mark.setUser(user);

        device = repoDevice.findDeviceById(5);
        mark.setDevice(device);

        mark.setValue(5);
        mark.setDescription("Łatwy w użyciu sprzęt");

        repoMark.save(mark);
    }
}
