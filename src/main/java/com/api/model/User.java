package com.api.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners({AuditingEntityListener.class})
@Table(name = "users")
public class User extends AuditableEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Name es requerido")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Username es requerido")
    @Column(name = "username")
    private String username;

    @Email(message = "Invalido el formato email")
    @NotBlank(message = "Email es requerido")
    @Column(name = "email")
    private String email;

    @Embedded
    private Address address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "website")
    private String website;

    @Embedded
    private Company company;
}