package br.com.zupacademy.projetoproposta.controllers;

import br.com.zupacademy.projetoproposta.conexoesexternas.bloqueiodocartao.BloqueioCartaoRequest;
import br.com.zupacademy.projetoproposta.conexoesexternas.bloqueiodocartao.BloqueioCartaoResponse;
import br.com.zupacademy.projetoproposta.conexoesexternas.geradoresdecartao.SolicitaCartaoFeign;
import br.com.zupacademy.projetoproposta.customexceptions.DocumentException;
import br.com.zupacademy.projetoproposta.dtos.BloqueioRequest;
import br.com.zupacademy.projetoproposta.enums.StatusDeBloqueio;
import br.com.zupacademy.projetoproposta.exceptionhandler.classesauxiliares.CampoDeMessagem;
import br.com.zupacademy.projetoproposta.models.BloqueioCartao;
import br.com.zupacademy.projetoproposta.models.Cartao;
import br.com.zupacademy.projetoproposta.repositories.BloqueioCartaoRepository;
import br.com.zupacademy.projetoproposta.repositories.CartaoRepository;

import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/cartao/bloqueio")
@RestController
public class BloqueioDeCartaoController {
    private static  final Logger logger = LoggerFactory.getLogger(BloqueioDeCartaoController.class);

    private CartaoRepository cartaoRepository;

    private BloqueioRequest bloqueioRequest;

    private BloqueioCartaoRepository bloqueioCartaoRepository;

    private SolicitaCartaoFeign solicitaCartaoFeign;


    public BloqueioDeCartaoController(CartaoRepository cartaoRepository,
                                      BloqueioRequest bloqueioRequest,
                                      BloqueioCartaoRepository bloqueioCartaoRepository,
                                      SolicitaCartaoFeign solicitaCartaoFeign
                                       ) {
        this.cartaoRepository = cartaoRepository;
        this.bloqueioRequest = bloqueioRequest;
        this.bloqueioCartaoRepository = bloqueioCartaoRepository;
        this.solicitaCartaoFeign = solicitaCartaoFeign;
    }

    @PostMapping("/{uuid}")
    public ResponseEntity<?> bloqueioDeCartao (@PathVariable("uuid") String uuid,
                                               HttpServletRequest request
                                                    )throws  DocumentException {
        Cartao possivelCartao = cartaoRepository.findByUuid(uuid).get();
        if(possivelCartao.getStatusDeBloqueio() == StatusDeBloqueio.BLOQUEADO){
            return ResponseEntity.unprocessableEntity().body(new CampoDeMessagem("Documento", "O cartão já está bloqueado"));
        }
        if (possivelCartao == null) {
            return ResponseEntity.notFound().build();
        }
            logger.info("Conectando com api externa");
            bloqueiaCartaoApiExterna(possivelCartao);
            logger.info("Cartao bloqueado na api");
            possivelCartao.setStatusDeBloqueio(StatusDeBloqueio.BLOQUEADO);
            BloqueioCartao bloqueioCartao = bloqueioRequest.toModel(request);
            bloqueioCartaoRepository.save(bloqueioCartao);
            possivelCartao.addBloqueioAoCartao(bloqueioCartao);
            cartaoRepository.save(possivelCartao);
            return ResponseEntity.ok().build();
    }

    private void bloqueiaCartaoApiExterna(Cartao cartao){
        logger.info("Conectado com api externa");
        try{
            BloqueioCartaoResponse bloqueioCartaoResponse = solicitaCartaoFeign.bloquearCartao(cartao.getUuid(),
                                                            new BloqueioCartaoRequest("api-proposta"));

        }catch (FeignException e){
            new  DocumentException(HttpStatus.INTERNAL_SERVER_ERROR, "Não foi possivel bloquear o cartão");
        }
    }
}
