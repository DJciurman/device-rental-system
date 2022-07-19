package com.system.elements;

import com.system.elements.store.Store;
import com.system.elements.store.StoreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class StoreRepositoryTests {

    @Autowired
    private StoreRepository repoStore;

    @Test
    public void testAddStore() {
        Store store = new Store();
        store.setName("Testowy");
        store.setAddress("Testowa 328");
        store.setCity("Testowo");

        repoStore.save(store);
    }
}
