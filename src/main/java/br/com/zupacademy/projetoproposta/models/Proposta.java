package br.com.zupacademy.projetoproposta.models;


import br.com.zupacademy.projetoproposta.validators.CpfOuCnpf;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    @CpfOuCnpf
    private String documento;

    @Email
    @NotBlank
    private String email;

    @NotNull
    @Min(value = 0)
    private BigDecimal salario;

    public Proposta(@NotBlank String nome,@NotBlank String documento,@NotBlank String email,@NotNull @Min(value = 0) BigDecimal salario) {
        this.nome = nome;
        this.documento = documento;
        this.email = email;
        this.salario = salario;
    }

    @Deprecated
    public Proposta(){
    }

    public Long getId() {
        return id;
    }
}
