package com.taller1Programacion.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/", "/login", "/login/**",
                                "/usuarios/nuevo", "/nosotros",
                                "/css/**", "/js/**", "/imagenes/**", "/uploads/**",
                                "/paquetes"
                        ).permitAll()
                        // para el vendedor y administrador
                        .requestMatchers("/clientes/**").hasAnyRole("VENDEDOR", "ADMINISTRADOR")
                        // Solo VENDEDOR puede crear nueva factura, el resto de ventas solo ADMIN
                        .requestMatchers("/ventas/nueva").hasAnyRole("VENDEDOR", "ADMINISTRADOR")
                        // solo para el administrador
                        .requestMatchers("/paquetes/**","/ventas/**","/usuarios/**").hasRole("ADMINISTRADOR")
                        .requestMatchers("/admin/dashboard").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login").permitAll()
                        .defaultSuccessUrl("/postLogin", true)
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}