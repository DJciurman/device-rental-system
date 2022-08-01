package com.system.elements.device;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface DeviceRepository extends JpaRepository<Device, Integer> {

    @Query("SELECT d FROM Device d WHERE d.id = ?1")
    Device findDeviceById(int id);

    @Query("SELECT d FROM Device d ORDER BY d.name ASC")
    Set<Device> findAllDevicesNameASC();

    @Query("SELECT d FROM Device d WHERE d.name LIKE %?1% OR d.description LIKE %?1%")
    Set<Device> findAllDevicesByKeyWord(String keyWord);

}
