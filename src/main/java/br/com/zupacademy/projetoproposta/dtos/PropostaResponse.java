package br.com.zupacademy.projetoproposta.dtos;

import br.com.zupacademy.projetoproposta.models.Cartao;
import br.com.zupacademy.projetoproposta.models.Proposta;
import br.com.zupacademy.projetoproposta.models.enums.StatusDeValidacao;
import br.com.zupacademy.projetoproposta.repositories.VencimentoRepository;

import java.math.BigDecimal;

public class PropostaResponse {

    private String nome;

    private String documento;

    private String email;

    private BigDecimal salario;

    private StatusDeValidacao statusDeValidacao;

    private CartaoResponse cartao;

    public  PropostaResponse(Proposta proposta) {
        this.nome = proposta.getNome();
        this.documento = proposta.getDocumento();
        this.email = proposta.getEmail();
        this.salario = proposta.getSalario();
        this.statusDeValidacao = proposta.getStatusDeValidacao();
        if(this.cartao == null){
            new CartaoResponse();
        }else{
            this.cartao = new CartaoResponse(proposta.getCartao());
        }
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public StatusDeValidacao getStatusDeValidacao() {
        return statusDeValidacao;
    }

    public CartaoResponse getCartao() {
        return cartao;
    }
}
