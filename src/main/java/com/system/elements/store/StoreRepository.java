package com.system.elements.store;

import com.system.elements.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface StoreRepository extends JpaRepository<Store, Integer> {

    @Query("SELECT s FROM Store s WHERE s.id = ?1")
    Store findStoreById(int id);

    @Query("SELECT s FROM Store s ORDER BY s.name DESC")
    Set<Store> findAllStoresNameDESC();

    @Query("SELECT s FROM Store s")
    Set<Store> findAllStores();
}
