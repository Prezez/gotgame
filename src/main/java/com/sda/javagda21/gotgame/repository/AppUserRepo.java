package com.sda.javagda21.gotgame.repository;

import com.sda.javagda21.gotgame.config.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
    AppUser findByRole(String role);
}
