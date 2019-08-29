package com.sda.javagda21.gotgame.service;

import com.sda.javagda21.gotgame.config.AppUser;
import com.sda.javagda21.gotgame.repository.AppUserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService{

    AppUserRepo appUserRepo;

    public AppUserService(AppUserRepo appUserRepo) {
        this.appUserRepo = appUserRepo;
    }

    public List<AppUser>  findAll () {
        List<AppUser> appUsers = appUserRepo.findAll();
        return appUsers;
    }

}


