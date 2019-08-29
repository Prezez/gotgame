package com.sda.javagda21.gotgame.config;

import com.sda.javagda21.gotgame.repository.AppUserRepo;
import com.sda.javagda21.gotgame.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;
    private AppUserRepo appUserRepo;

    @Autowired
    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, AppUserRepo appUserRepo) {
        this.userDetailsService = userDetailsService;
        this.appUserRepo = appUserRepo;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/test1").permitAll()
                .antMatchers("/test2").hasRole("USER")
                .antMatchers("/test3").hasRole("ADMIN")
                .antMatchers("/chatAll").permitAll()
                .antMatchers("/sendMessage").permitAll()
                .antMatchers("/chatRegister").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/main-menu").permitAll()
                .antMatchers("/resources/**").permitAll()
//                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .csrf()
                .disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @EventListener(ApplicationReadyEvent.class)
    public void get() {
//        AppUser appUserUser = new AppUser("UserJan", passwordEncoder().encode("UserJan"), "ROLE_USER");
//        AppUser appUserAdmin = new AppUser("AdminJan", passwordEncoder().encode("AdminJan"), "ROLE_ADMIN");
//        appUserRepo.save(appUserUser);
//        appUserRepo.save(appUserAdmin);
    }

}


