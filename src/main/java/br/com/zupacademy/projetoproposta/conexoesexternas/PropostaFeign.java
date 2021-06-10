package br.com.zupacademy.projetoproposta.conexoesexternas;

import br.com.zupacademy.projetoproposta.dtos.AnalisarSolicitacaoRequest;
import br.com.zupacademy.projetoproposta.dtos.AnalisarSolicitacaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "proposta", url = "${analise_documento.url}/api")
public interface PropostaFeign {

    @RequestMapping(method = RequestMethod.POST, value = "/solicitacao",consumes = "application/json", produces = "application/json")
    AnalisarSolicitacaoResponse enviar(AnalisarSolicitacaoRequest analisarSolicitacaoRequest);
}
