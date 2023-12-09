package com.example.maincinedrive.model.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.maincinedrive.model.services.UsuarioServices;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfiguration {
    @Autowired
    private UsuarioServices usuarioServicio;

    @Autowired
    private LoginSuccessMessage successMessage;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(usuarioServicio);
        auth.setPasswordEncoder(passwordEncoder());
        auth.setHideUserNotFoundExceptions(false);

        return auth;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests

                        .requestMatchers("/", "/css/**", "/images/**", "/js/**", "/assets/**").permitAll()
                        .requestMatchers("/cartelera/cartelera").permitAll()
                        .requestMatchers("/home", "/fragments/**", "/login-register/**").permitAll()
                        .requestMatchers("/login-register/register").permitAll()
                        .requestMatchers("/cartelera/**").permitAll()
                        .requestMatchers("/administracion/peliculas/nuevo").hasRole("ADMIN")
                        .requestMatchers("/administracion/**").hasRole("ADMIN")
                        .anyRequest().permitAll())
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .successHandler(successMessage)
                        .permitAll())
                .logout(logout -> logout
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessHandler(new LogoutSuccessHandler() {
                            @Override
                            public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                    Authentication authentication) throws IOException, ServletException {

                                if (authentication != null) {
                                    response.sendRedirect("/login?logout");

                                } else {
                                    // Si no hay una autenticación válida, no hace nada
                                }
                            }
                        })
                        .permitAll());
        return http.build();
    }

}
