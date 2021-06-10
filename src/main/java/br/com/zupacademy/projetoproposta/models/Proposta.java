package br.com.zupacademy.projetoproposta.models;

import br.com.zupacademy.projetoproposta.enums.StatusDeValidacao;
import br.com.zupacademy.projetoproposta.validatores.CpfOuCnpf;

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

    @Enumerated(EnumType.STRING)
    private StatusDeValidacao statusDeValidacao;

    @OneToOne
    @JoinColumn(name = "cartao_id")
    private Cartao cartao;

    public Proposta(@NotBlank String nome,
                    @NotBlank String documento,
                    @NotBlank String email,
                    @NotNull @Min(value = 0) BigDecimal salario) {
        this.nome = nome;
        this.documento = documento;
        this.email = email;
        this.salario = salario;
    }

    @Deprecated
    public Proposta(){
    }

    public String getEmail() {
        return email;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public Cartao getCartao() {
        return cartao;
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

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }
}
