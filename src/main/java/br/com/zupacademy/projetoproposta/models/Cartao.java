package br.com.zupacademy.projetoproposta.models;

import br.com.zupacademy.projetoproposta.enums.StatusDeBloqueio;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Cartao {

    @Lob
    private String uuid = UUID.randomUUID().toString().replace("-","");

    @Id
    @NotBlank
    @Column(unique = true)
    private String id;

    @NotNull
    private LocalDateTime emitidoEm;

    @NotBlank
    private String titular;

    @NotNull
    @Min(0)
    private BigDecimal limite;

    @OneToMany(mappedBy = "cartao",cascade = CascadeType.MERGE)
    private List<Biometria> biometrias = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private StatusDeBloqueio statusDeBloqueio;

    @OneToMany
    private List<BloqueioCartao> bloqueioCartoes = new ArrayList<>();

    @Deprecated
    public Cartao(){
    }
    public Cartao(String id,
                  LocalDateTime emitidoEm,
                  String titular,
                  BigDecimal limite
    ) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;
    }

    public List<BloqueioCartao> getBloqueioCartaos() {
        return bloqueioCartoes;
    }
    public void addBloqueioAoCartao(BloqueioCartao bloqueioCartao) {
         bloqueioCartoes.add(bloqueioCartao);
    }

    public StatusDeBloqueio getStatusDeBloqueio() {
        return statusDeBloqueio;
    }

    public void setStatusDeBloqueio(StatusDeBloqueio statusDeBloqueio) {
        this.statusDeBloqueio = statusDeBloqueio;
    }

    public Biometria pegaAUltimaBiometria(){
        return biometrias.get(biometrias.size() - 1);
    }

    public void adicionaBiometria(Biometria novaBiometria){
        biometrias.add(novaBiometria);
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public String getId() {
        return id;
    }

    public void setBiometria(List<Biometria> biometrias) {
        this.biometrias = biometrias;
    }

    public String getUuid() {
        return uuid;
    }

    public List<Biometria> getBiometrias() {
        return biometrias;
    }
}
