package com.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.model.UserEntity;
import com.api.model.UserRoleEntity;
import com.api.repository.UserSecurityRepository;

@Service
public class UserSecurityService implements UserDetailsService {

    private final UserSecurityRepository userSecurityRepository;

    @Autowired
    public UserSecurityService(UserSecurityRepository userSecurityRepository){
        this.userSecurityRepository=userSecurityRepository;
    }


    // Método de la interfaz UserDetailsService para cargar los detalles del usuario por su nombre de usuario.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       UserEntity userEntity = this.userSecurityRepository.findById(username)
                .orElseThrow(( ) -> new UsernameNotFoundException("User"+ username + "not found"));
                    
        String [] roles= userEntity.getRoles().stream().map(UserRoleEntity::getRole).toArray(String []::new);       

        return User.builder()
        .username(userEntity.getUsername())
        .password(userEntity.getPassword())
        .authorities(this.grantedAuthorities(roles))
        .accountLocked(userEntity.isLocked())
        .disabled(userEntity.isDisabled())
        .build();
    }
    // Obtiene las autoridades adicionales basadas en el rol del usuario
    private String[] getAuthorities (String role){
        if ("ADMIN".equals(role) || "CUSTOMER".equals(role)){
            return new String[] {"random_order"};
        }

         // Devuelve un array vacío si el rol no es "ADMIN" ni "CUSTOMER".
        return new String[] {};
    }


// Construye una lista de GrantedAuthority basada en los roles del usuario.
    private List<GrantedAuthority> grantedAuthorities(String[] roles){
        List<GrantedAuthority> authorities = new ArrayList<>(roles.length);
        for (String role: roles){
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));     // Agrega una autoridad con el formato "ROLE_rol" al inicio de la lista de autoridades.

            for (String authority: this.getAuthorities(role) ) {
                authorities.add(new SimpleGrantedAuthority(authority));         // Obtiene las autoridades adicionales para el rol y las agrega a la lista de autoridades.
                
            }
        }
        return authorities;
    }
    
}
