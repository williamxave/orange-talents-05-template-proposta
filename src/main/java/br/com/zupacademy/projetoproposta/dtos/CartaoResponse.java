package br.com.zupacademy.projetoproposta.dtos;

import br.com.zupacademy.projetoproposta.models.Cartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CartaoResponse {

    private String id;

    private LocalDateTime emitidoEm;

    private String titular;

    private BigDecimal limite;

    public CartaoResponse(Cartao cartao) {
        this.id = cartao.getId();
        this.emitidoEm = cartao.getEmitidoEm();
        this.titular = cartao.getTitular();
        this.limite = cartao.getLimite();
    }

    public CartaoResponse() {

    }

    public String getId() {
        return id;
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
}
