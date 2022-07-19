package com.system.elements.store;

import com.system.elements.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StoreRepository extends JpaRepository<Store, Integer> {

    @Query("SELECT s FROM Store s WHERE s.id = ?1")
    Store findStoreById(int id);
}
