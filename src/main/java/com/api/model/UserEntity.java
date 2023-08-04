package com.api.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users_entity")
public class UserEntity {
    @Id
    @Column(nullable = false, length = 100) 
    private String username;

    @Column(nullable = false, length = 200)
    private String password;

    @Column(length = 100)
    private String email;

    @Column(nullable = false)
    private boolean locked;

    @Column(nullable = false)
    private boolean disabled;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List< UserRoleEntity> roles;


    
}
