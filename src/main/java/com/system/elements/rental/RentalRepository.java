package com.system.elements.rental;

import com.system.elements.device.Device;
import com.system.elements.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RentalRepository extends JpaRepository<Rental, Integer> {

    @Query("SELECT r FROM Rental r WHERE r.id = ?1")
    Device findRentalById(int id);

}
