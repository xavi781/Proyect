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
public class Company {
    @Column(name = "company_name")
    @NotBlank(message = "Name es requerido")
    private String name;

    @Column(name = "catchPhrase")
    @NotBlank(message = "Catch phrase es requerido")
    private String catchPhrase;

    @Column(name = "bs")
    @NotBlank(message = "BS es requerido")
    private String bs;
}
