package br.com.zupacademy.projetoproposta.conexoesexternas;

import br.com.zupacademy.projetoproposta.dtos.SolicitaCartaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "accounts", url = "${url.accounts.api}")
public interface SolicitaCartaoFeign {

    @RequestMapping(method = RequestMethod.GET, path = "?idProposta={idProposta}",consumes = "application/json")
    SolicitaCartaoResponse solicitaCartao(@PathVariable(name = "idProposta") Long idProposta);
}
