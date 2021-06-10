package br.com.zupacademy.projetoproposta.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String uuid = UUID.randomUUID().toString().replace("-","");

    @NotBlank
    @Lob
    private String fingerPrint;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "cartao_id")
    private Cartao cartao;

    @NotNull
    private LocalDateTime dataCriacaoBiometria =  LocalDateTime.now();

    @Deprecated
    public Biometria(){
    }

    public Biometria(@NotBlank String fingerPrint,@NotNull Cartao cartao) {
        this.fingerPrint = fingerPrint;
        this.cartao = cartao;
        this.dataCriacaoBiometria = dataCriacaoBiometria;
    }

    public Long getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public String getBiometria() {
        return fingerPrint;
    }
}
