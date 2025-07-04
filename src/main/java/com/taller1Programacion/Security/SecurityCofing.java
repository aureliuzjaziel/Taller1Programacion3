package com.taller1Programacion.Security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityCofing {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Solo ADMIN puede gestionar usuarios y paquetes
                        .requestMatchers("/usuarios/**", "/paquetes").hasRole("ADMINISTRADOR")
                        // Ambos roles pueden gestionar clientes y ventas
                        .requestMatchers("/clientes/**", "/ventas").hasAnyRole("ADMINISTRADOR", "VENDEDOR")
                        // Acceso libre a páginas públicas (index, login, registro, recursos estáticos)
                        .requestMatchers("/", "/index", "/registroUsuario", "/css/**", "/js/**", "/images/**").permitAll()
                        // Cualquier otra petición requiere autenticación
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout(logout -> logout.permitAll());
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}