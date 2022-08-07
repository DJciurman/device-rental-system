package com.system.elements.rental;

import com.system.elements.device.Device;
import com.system.elements.message.Message;
import com.system.elements.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface RentalRepository extends JpaRepository<Rental, Integer> {

    @Query("SELECT r FROM Rental r WHERE r.id = ?1")
    Rental findRentalById(int id);

    @Query("SELECT r FROM Rental r WHERE r.user = ?1")
    Set<Rental> findAllUserRentals(User user);

}
