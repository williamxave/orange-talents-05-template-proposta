package br.com.zupacademy.projetoproposta.dtos;

import br.com.zupacademy.projetoproposta.exceptionhandler.DocumentException;
import br.com.zupacademy.projetoproposta.models.Proposta;
import br.com.zupacademy.projetoproposta.conexoesexternas.PropostaFeign;
import br.com.zupacademy.projetoproposta.models.enums.StatusDeValidacaoApiExterna;

import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class AnalisarSolicitacaoRequest {
    private  static final Logger logger = LoggerFactory.getLogger(AnalisarSolicitacaoRequest.class);
    @Autowired
    private PropostaFeign propostaFeign;

    private String documento;
    private String nome;
    private Long idProposta;
    private StatusDeValidacaoApiExterna resultadoSolicitacao;

    public AnalisarSolicitacaoRequest(String documento, String nome, Long idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
    }

    @Deprecated
    public AnalisarSolicitacaoRequest() {
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

    public AnalisarSolicitacaoResponse validacaoDeDocumento(Proposta possivelProsta) throws DocumentException {
        logger.warn("Enviando sua proposta para validação!");
        //Recebe o a proposta, com ela cria o obj de analise
        AnalisarSolicitacaoRequest analisarSolicitacaoRequest = new AnalisarSolicitacaoRequest(
                possivelProsta.getDocumento(),
                possivelProsta.getNome(),
                possivelProsta.getId()
        );
        try {
            //Verifica se a analise está ok conforme o que pede a api externa
            AnalisarSolicitacaoResponse analisarSolicitacaoResponse = propostaFeign.enviar(analisarSolicitacaoRequest);
            logger.info("Proposta aceita com sucesso!");
            return analisarSolicitacaoResponse;
        } catch (FeignException.UnprocessableEntity e) {
            logger.error("Sua proposta foi negada. Documento está inválido!");
            //Se o documento estiver com restricao, retorna com o status de resticao
            //Pode usa o ObjecMapper para fazer isso tbm
            return new AnalisarSolicitacaoResponse(
                    possivelProsta.getDocumento(),
                    possivelProsta.getNome(),
                    possivelProsta.getId(),
                    StatusDeValidacaoApiExterna.COM_RESTRICAO
            );
        }catch (FeignException e){
            logger.error("ErrorCode: 500! Servidor do serviço não está disponivel");
          throw new DocumentException(HttpStatus.SERVICE_UNAVAILABLE, "Servidor do serviço não está disponivel!");
        }
    }
}


