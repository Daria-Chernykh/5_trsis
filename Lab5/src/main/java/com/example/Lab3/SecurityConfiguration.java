
package com.example.Lab3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private static final String DISPATCHER_SERVLET_PATH = "/"; // Adjust if different

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> {
                    authorize
                            .requestMatchers(new AntPathRequestMatcher("/js/**")).permitAll()
                            .requestMatchers(new AntPathRequestMatcher("/css/**")).permitAll()
                            .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
                            .requestMatchers(new AntPathRequestMatcher("/main", DISPATCHER_SERVLET_PATH)).permitAll() //Correctly handles /main
                            .anyRequest().authenticated();
                })
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .usernameParameter("login")
                        .passwordParameter("pass")
                        .permitAll()
                        .successHandler((request, response, authentication) -> {
                            try {
                                response.sendRedirect("/main");
                            } catch (IOException e) {

                                e.printStackTrace();
                            }
                        })
                )
                .logout(logout -> logout.permitAll()
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login"));

        return http.build();
    }

    //@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, PasswordEncoder passwordEncoder) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder.encode("password"))
                .roles("ALL")
                .and()
                .withUser("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ALL");
    }
}

