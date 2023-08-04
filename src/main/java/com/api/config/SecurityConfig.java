package com.api.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity( securedEnabled = true)
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain FilterChain (HttpSecurity http) throws Exception{      //configuración de seguridad para las peticiones HTTP en la aplicación.
        http.csrf().disable()
            .cors().and()    // solicitudes desde dominios diferentes.
            .authorizeRequests()   //Inicia la configuración de autorización basada en las solicitudes HTTP.
            .antMatchers("/auth/**").permitAll()
            .antMatchers(HttpMethod.GET, "/users/**").hasAnyRole("ADMIN", "CUSTOMER" )
            .antMatchers(HttpMethod.PUT, "/users/**").hasRole("ADMIN"  )
            .antMatchers(HttpMethod.POST, "/users/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
            .anyRequest().authenticated()   //Cualquier otra solicitud HTTP no mencionada anteriormente debe estar autenticada para acceder.
            .and()
            .httpBasic();    //Configura la autenticación HTTP básica.
    
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
