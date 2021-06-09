package br.com.zupacademy.projetoproposta.dtos;

import br.com.zupacademy.projetoproposta.models.Cartao;
import br.com.zupacademy.projetoproposta.models.Vencimento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SolicitaCartaoResponse {

    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    private BigDecimal limite;
    private VencimentoResponse vencimento;
    private String idProposta;

    public SolicitaCartaoResponse(String id,
                                  LocalDateTime emitidoEm,
                                  String titular,
                                  BigDecimal limite,
                                  VencimentoResponse vencimento,
                                  String idProposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;
        this.vencimento = vencimento;
        this.idProposta = idProposta;
    }

    @Deprecated
    public SolicitaCartaoResponse(){
    }

    public Cartao toModel(){
       return new Cartao(
               this.id,
               this.emitidoEm,
               this.titular,
               this.limite
        );
    }
    //Gera o VencimentoResponse do cartao, ainda nao está funcionando
    public Vencimento geraVencimento(Cartao cartao){
        return new Vencimento(
                this.vencimento.getId(),
                this.vencimento.getDia(),
                this.vencimento.getDataDeCriacao(),
                cartao
        );
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

    public VencimentoResponse getVencimento() {
        return vencimento;
    }

    public String getIdProposta() {
        return idProposta;
    }
}