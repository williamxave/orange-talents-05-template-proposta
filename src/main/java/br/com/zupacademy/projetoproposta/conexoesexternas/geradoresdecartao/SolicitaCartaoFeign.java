package br.com.zupacademy.projetoproposta.conexoesexternas.geradoresdecartao;

import br.com.zupacademy.projetoproposta.conexoesexternas.avisoviagemapiexterna.AvisoViagemRequestApiExterna;
import br.com.zupacademy.projetoproposta.conexoesexternas.avisoviagemapiexterna.AvisoViagemResponseApiExterna;
import br.com.zupacademy.projetoproposta.conexoesexternas.bloqueiodocartao.BloqueioCartaoRequest;
import br.com.zupacademy.projetoproposta.conexoesexternas.bloqueiodocartao.BloqueioCartaoResponse;
import br.com.zupacademy.projetoproposta.conexoesexternas.solicitacarteiraapiexterna.CarteiraReponseApiExterna;
import br.com.zupacademy.projetoproposta.conexoesexternas.solicitacarteiraapiexterna.CarteiraRequestApiExterna;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(name = "accounts", url = "${url.accounts.api}/api/cartoes")
public interface SolicitaCartaoFeign {

    @RequestMapping(method = RequestMethod.GET, path = "?idProposta={idProposta}",consumes = "application/json")
    SolicitaCartaoResponse solicitaCartao(@PathVariable(name = "idProposta") Long idProposta);

    @RequestMapping(method = RequestMethod.POST,path = "/{uuid}/bloqueio", consumes ="application/json", produces = "application/json")
    BloqueioCartaoResponse bloquearCartao(@PathVariable(name = "uuid") String uuid, @Valid @RequestBody BloqueioCartaoRequest bloqueioCartaoRequest);

    @RequestMapping(method = RequestMethod.POST,path = "/{uuid}/avisos",consumes ="application/json", produces = "application/json")
    AvisoViagemResponseApiExterna avisoViagemParaApiExterna(@PathVariable(name = "uuid") String uuid,
                                                            @Valid @RequestBody AvisoViagemRequestApiExterna avisoViagemRequestApiExterna);

    @RequestMapping(method = RequestMethod.POST, path = "/{uuid}/carteiras",consumes ="application/json", produces = "application/json")
    CarteiraReponseApiExterna associaCarteira(@PathVariable(name = "uuid") String uuid, @Valid @RequestBody CarteiraRequestApiExterna request);

}
