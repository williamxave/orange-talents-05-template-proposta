package br.com.zupacademy.projetoproposta.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Entity
public class Vencimento {

    @Id
    @NotBlank
    private String id;

    @NotNull
    private Integer dia;

    @NotNull
    private LocalDateTime dataDeCriacao;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "cartao_id")
    private Cartao cartao;

    @Deprecated
    public Vencimento() {
    }

    public Vencimento(@NotBlank String id,
                      @NotNull Integer dia,
                      @NotNull LocalDateTime dataDeCriacao,
                      @NotNull Cartao cartao) {
        this.id = id;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
        this.cartao = cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public Integer getDia() {
        return dia;
    }

    public LocalDateTime getCriacao() {
        return dataDeCriacao;
    }

    public Cartao getDataDeCriacao() {
        return cartao;
    }

}
