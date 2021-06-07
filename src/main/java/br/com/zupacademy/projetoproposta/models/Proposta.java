package br.com.zupacademy.projetoproposta.models;


import br.com.zupacademy.projetoproposta.models.enums.StatusDeValidacao;
import br.com.zupacademy.projetoproposta.validators.CpfOuCnpf;

import javax.persistence.*;
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
    @Column(columnDefinition = "enum('ELEGIVEL','NAO_ELEGIVEL')")
    @Enumerated(EnumType.STRING)
    private StatusDeValidacao statusDeValidacao;

    public Proposta(@NotBlank String nome,@NotBlank String documento,@NotBlank String email,@NotNull @Min(value = 0) BigDecimal salario) {
        this.nome = nome;
        this.documento = documento;
        this.email = email;
        this.salario = salario;
    }

    @Deprecated
    public Proposta(){
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setStatusDeValidacao(StatusDeValidacao statusDeValidacao) {
        this.statusDeValidacao = statusDeValidacao;
    }

    public StatusDeValidacao getStatusDeValidacao() {
        return statusDeValidacao;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Proposta{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", documento='" + documento + '\'' +
                ", email='" + email + '\'' +
                ", salario=" + salario +
                ", statusDeValidacao=" + statusDeValidacao +
                '}';
    }
}
