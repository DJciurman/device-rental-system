package com.system.elements.mark;

import com.system.elements.device.Device;
import com.system.elements.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MarkRepository extends JpaRepository<Mark, Integer> {

    @Query("SELECT m FROM Mark m WHERE m.id = ?1")
    Device findMarkById(int id);

}
