package br.com.zupacademy.projetoproposta.controllers;

import br.com.zupacademy.projetoproposta.customexceptions.DocumentException;
import br.com.zupacademy.projetoproposta.dtos.BloqueioRequest;
import br.com.zupacademy.projetoproposta.enums.StatusDeBloqueio;
import br.com.zupacademy.projetoproposta.exceptionhandler.classesauxiliares.CampoDeMessagem;
import br.com.zupacademy.projetoproposta.models.BloqueioCartao;
import br.com.zupacademy.projetoproposta.models.Cartao;
import br.com.zupacademy.projetoproposta.repositories.BloqueioCartaoRepository;
import br.com.zupacademy.projetoproposta.repositories.CartaoRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/cartao/bloqueio")
@RestController
public class BloqueioDeCartaoController {

    private CartaoRepository cartaoRepository;

    private BloqueioRequest bloqueioRequest;

    private BloqueioCartaoRepository bloqueioCartaoRepository;


    public BloqueioDeCartaoController(CartaoRepository cartaoRepository,
                                      BloqueioRequest bloqueioRequest,
                                      BloqueioCartaoRepository bloqueioCartaoRepository) {
        this.cartaoRepository = cartaoRepository;
        this.bloqueioRequest = bloqueioRequest;
        this.bloqueioCartaoRepository = bloqueioCartaoRepository;
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
            possivelCartao.setStatusDeBloqueio(StatusDeBloqueio.BLOQUEADO);
            BloqueioCartao bloqueioCartao = bloqueioRequest.toModel(request);
            bloqueioCartaoRepository.save(bloqueioCartao);
            possivelCartao.addBloqueioAoCartao(bloqueioCartao);
            cartaoRepository.save(possivelCartao);
            return ResponseEntity.ok().build();
    }
}
