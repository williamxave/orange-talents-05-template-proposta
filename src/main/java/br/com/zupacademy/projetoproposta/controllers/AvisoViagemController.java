package br.com.zupacademy.projetoproposta.controllers;

import br.com.zupacademy.projetoproposta.customexceptions.DocumentException;
import br.com.zupacademy.projetoproposta.dtos.AvisoViagemRequest;
import br.com.zupacademy.projetoproposta.models.AvisoDeViagem;
import br.com.zupacademy.projetoproposta.models.Cartao;
import br.com.zupacademy.projetoproposta.repositories.AvisoDeViagemRepository;
import br.com.zupacademy.projetoproposta.repositories.CartaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/viagem")
public class AvisoViagemController {

    private CartaoRepository cartaoRepository;
    private AvisoDeViagemRepository avisoDeViagemRepository;

    public AvisoViagemController(CartaoRepository cartaoRepository,
                                 AvisoDeViagemRepository avisoDeViagemRepository) {
        this.cartaoRepository = cartaoRepository;
        this.avisoDeViagemRepository = avisoDeViagemRepository;
    }

    @PostMapping
    public ResponseEntity<?> avisoDeViagem(@Valid @RequestBody AvisoViagemRequest avisoViagemRequest,
                                                    HttpServletRequest request) throws DocumentException {
        Cartao possivelCartao = cartaoRepository.findByUuid(avisoViagemRequest.getUuidCartao())
                                                .orElseThrow(() -> new DocumentException(HttpStatus.NOT_FOUND, "Cartão inválido"));
        AvisoDeViagem avisoDeViagem = avisoViagemRequest.toModel(request);
        avisoDeViagemRepository.save(avisoDeViagem);
        return ResponseEntity.ok().build();
    }
}
