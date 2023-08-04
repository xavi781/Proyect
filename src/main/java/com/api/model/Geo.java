package com.api.model;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Geo {
    @Column(name = "lat")
    @NotBlank(message = "Latitud es requerido")
    private String lat;

    @Column(name = "lng")
    @NotBlank(message = "Longitud es requerido")
    private String lng;
}
