package com.system.elements.device;

import com.system.elements.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface DeviceRepository extends JpaRepository<Device, Integer> {

    @Query("SELECT d FROM Device d WHERE d.id = ?1")
    Device findDeviceById(int id);

    @Query("SELECT d FROM Device d ORDER BY d.name ASC")
    Set<Device> findAllDevicesOrderByNameASC();

    @Query("SELECT d FROM Device d WHERE (d.name LIKE %?1% OR d.description LIKE %?1%) AND d.store = ?2 ORDER BY d.name ASC")
    Set<Device> findAllDevicesWhereKeyWordInNameOrStoreOrderByNameASC(String keyWord, Store store);

    @Query("SELECT d FROM Device d WHERE d.store = ?1 ORDER BY d.name ASC")
    Set<Device> findAllDevicesWhereStoreInStoreOrderByNameASC(Store store);

}
