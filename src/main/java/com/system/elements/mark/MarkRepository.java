package com.system.elements.mark;

import com.system.elements.device.Device;
import com.system.elements.employee.Employee;
import com.system.elements.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MarkRepository extends JpaRepository<Mark, Integer> {

    @Query("SELECT m FROM Mark m WHERE m.id = ?1")
    Mark findMarkById(int id);

    @Query("SELECT m FROM Mark m WHERE m.user = ?1 AND m.device = ?2")
    Mark findMarkByUserAndDevice(User user, Device device);

}
