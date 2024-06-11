package com.example.conprgKZ.Repository;

import com.example.conprgKZ.Entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<UserDetails,Integer> {

    Optional<UserDetails> findByEmail(String email);
    Optional<UserDetails> findByEmailAndPassword(String email, String Password);

}