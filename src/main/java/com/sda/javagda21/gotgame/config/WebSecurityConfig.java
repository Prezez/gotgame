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
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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
                .antMatchers("/chatAll").permitAll()
                .antMatchers("/sendMessage").permitAll()
                .antMatchers("/chatRegister").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/main-menu").permitAll()
//                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .csrf()
                .disable();
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/resources/**").anyRequest();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @EventListener(ApplicationReadyEvent.class)
    public void get() {
        AppUser appUserJan = new AppUser("Jan", passwordEncoder().encode("123"), "ROLE_USER", false);
        AppUser appUserJim = new AppUser("Jim", passwordEncoder().encode("123"), "ROLE_USER", false);
        appUserRepo.save(appUserJan);
        appUserRepo.save(appUserJim);
//        AppUser appUserAdmin = new AppUser("AdminJan", passwordEncoder().encode("AdminJan"), "ROLE_ADMIN");
//        appUserRepo.save(appUserAdmin);
    }

}


