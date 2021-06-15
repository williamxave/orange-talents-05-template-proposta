package br.com.zupacademy.projetoproposta.models;

import br.com.zupacademy.projetoproposta.enums.TipoDeCarteira;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Lob
    private String uuid = UUID.randomUUID().toString().replace("-","");

    @NotNull
    @ManyToOne
    @JoinColumn(name = "cartao_id")
    private Cartao cartao;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoDeCarteira carteira;

    public Carteira(@NotBlank @Email String email,
                    @NotBlank Cartao cartao,
                    @NotNull TipoDeCarteira carteira) {
        this.email = email;
        this.cartao = cartao;
        this.carteira = carteira;
    }

    @Deprecated
    public Carteira(){
    }

    public Long getId() {
        return id;
    }

    public TipoDeCarteira getCarteira() {
        return carteira;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return "Carteira{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", uuid='" + uuid + '\'' +
                ", cartao=" + cartao +
                ", carteira=" + carteira +
                '}';
    }
}
