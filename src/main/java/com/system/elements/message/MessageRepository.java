package com.system.elements.message;

import com.system.elements.device.Device;
import com.system.elements.mark.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Query("SELECT m FROM Message m WHERE m.id = ?1")
    Device findMessageById(int id);

}
