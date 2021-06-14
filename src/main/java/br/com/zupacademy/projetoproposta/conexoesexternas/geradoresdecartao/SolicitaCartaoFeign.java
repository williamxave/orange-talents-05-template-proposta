package br.com.zupacademy.projetoproposta.conexoesexternas.geradoresdecartao;

import br.com.zupacademy.projetoproposta.conexoesexternas.bloqueiodocartao.BloqueioCartaoRequest;
import br.com.zupacademy.projetoproposta.conexoesexternas.bloqueiodocartao.BloqueioCartaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(name = "accounts", url = "${url.accounts.api}/api/cartoes")
public interface SolicitaCartaoFeign {

    @RequestMapping(method = RequestMethod.GET, path = "?idProposta={idProposta}",consumes = "application/json")
    SolicitaCartaoResponse solicitaCartao(@PathVariable(name = "idProposta") Long idProposta);

    @PostMapping(path = "/{uuid}/bloqueio", consumes ="application/json", produces = "application/json")
    BloqueioCartaoResponse bloquearCartao(@PathVariable(name = "uuid") String uuid, @Valid @RequestBody BloqueioCartaoRequest bloqueioCartaoRequest);

}
