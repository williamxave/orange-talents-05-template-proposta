package br.com.zupacademy.projetoproposta.models;

import br.com.zupacademy.projetoproposta.dtos.AnalisarSolicitacaoRequest;
import br.com.zupacademy.projetoproposta.dtos.AnalisarSolicitacaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "proposta", url = "http://localhost:9999/api")
public interface PropostaFeign {

    @RequestMapping(method = RequestMethod.POST, value = "/solicitacao",consumes = "application/json", produces = "application/json")
    AnalisarSolicitacaoResponse enviar(AnalisarSolicitacaoRequest analisarSolicitacaoRequest);
}
