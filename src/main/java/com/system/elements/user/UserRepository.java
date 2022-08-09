package com.system.elements.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findUserByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.id = ?1")
    User findUserById(int id);
}
