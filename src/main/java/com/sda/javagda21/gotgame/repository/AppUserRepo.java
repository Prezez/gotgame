package com.sda.javagda21.gotgame.repository;

import com.sda.javagda21.gotgame.Config.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepo extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
