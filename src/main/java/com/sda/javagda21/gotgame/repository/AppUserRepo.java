package com.sda.javagda21.gotgame.repository;

import com.sda.javagda21.gotgame.config.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
    AppUser findByRole(String role);
    List <AppUser> findAllByActive (boolean active);



}


