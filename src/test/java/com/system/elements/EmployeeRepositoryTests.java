package com.system.elements;

import com.system.elements.device.Device;
import com.system.elements.device.DeviceRepository;
import com.system.elements.employee.Employee;
import com.system.elements.employee.EmployeeRepository;
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
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository repoEmployee;

    @Autowired
    private UserRepository repoUser;

    @Autowired
    private StoreRepository repoStore;

    @Test
    public void testAddEmployee() {

        Employee employee = new Employee();

        Store store = repoStore.findStoreById(1);

        employee.setStore(store);
        employee.setRole("Pracownik");

        User user = repoUser.findUserByEmail("email@test.com");

        employee.setUser(user);

        repoEmployee.save(employee);
    }
}
