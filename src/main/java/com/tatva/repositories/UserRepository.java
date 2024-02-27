package com.tatva.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tatva.entities.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    List<Users> findByEmail(String email);

	Users findByUsername(String username);

    
}
