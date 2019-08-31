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

    public AppUser findByUsername(String userName) {
        AppUser user = appUserRepo.findByUsername(userName);
        return user;
    }

    public List<AppUser> findAllByActive (boolean active){
        List<AppUser> appUsers = appUserRepo.findAllByActive(active);
        return appUsers;
    }

}


