package br.com.zupacademy.projetoproposta.conexoesexternas.validadorespropostas;

import br.com.zupacademy.projetoproposta.models.Proposta;
import br.com.zupacademy.projetoproposta.enums.StatusDeValidacao;
import br.com.zupacademy.projetoproposta.enums.StatusDeValidacaoApiExterna;

public class AnalisarSolicitacaoResponse {

    private String documento;
    private String nome;
    private Long idProposta;
    private StatusDeValidacaoApiExterna resultadoSolicitacao;

    @Deprecated
    public  AnalisarSolicitacaoResponse(){

    }
    public AnalisarSolicitacaoResponse(String documento, String nome, Long idProposta, StatusDeValidacaoApiExterna resultadoSolicitacao) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
        this.resultadoSolicitacao = resultadoSolicitacao;
    }

    // Faz a alteração do status externo para o da aplicação
    public void alteraStatusDaProposta(Proposta possivelProposta) {
        if (this.resultadoSolicitacao.equals(StatusDeValidacaoApiExterna.SEM_RESTRICAO)) {
            possivelProposta.setStatusDeValidacao(StatusDeValidacao.ELEGIVEL);
        }else{
            possivelProposta.setStatusDeValidacao(StatusDeValidacao.NAO_ELEGIVEL);
        }
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdProposta() {
        return idProposta;
    }

    public StatusDeValidacaoApiExterna getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }
}
