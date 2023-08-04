package com.api.model;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Embeddable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {
    @Column(name = "street")
    @NotBlank(message = "Street es requerido")
    private String street;

    @Column(name = "suite")
    private String suite;

    @Column(name = "city")
    @NotBlank(message = "City es requerido")
    private String city;

    @Column(name = "zipcode")
    @NotBlank(message = "Zipcode es requerido")
    private String zipcode;

    @Embedded
    @NotNull(message = "Geo es requerido")
    private Geo geo;
}
