package br.com.zupacademy.projetoproposta.controllers;

import br.com.zupacademy.projetoproposta.customexceptions.DocumentException;
import br.com.zupacademy.projetoproposta.dtos.BiometriaRequest;
import br.com.zupacademy.projetoproposta.models.Biometria;
import br.com.zupacademy.projetoproposta.models.Cartao;
import br.com.zupacademy.projetoproposta.repositories.BiometriaRepository;
import br.com.zupacademy.projetoproposta.repositories.CartaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/cartao")
public class BiometriaController {

    private final CartaoRepository cartaoRepository;
    private final BiometriaRepository biometriaRepository;

    public BiometriaController(CartaoRepository cartaoRepository, BiometriaRepository biometriaRepository) {
        this.cartaoRepository = cartaoRepository;
        this.biometriaRepository = biometriaRepository;
    }

    @PostMapping("/{uuid}/biometria")
    public ResponseEntity<?> cadastraBiometria(@PathVariable String uuid,
                                               @Valid @RequestBody BiometriaRequest biometriaRequest,
                                               UriComponentsBuilder builder) throws DocumentException {
        Optional<Cartao> cartao = Optional.ofNullable(cartaoRepository.findByUuid(uuid)
                                .orElseThrow(() -> new DocumentException(HttpStatus.NOT_FOUND, "Cartão inválido")));
            Biometria biometria = biometriaRequest.toModel(cartao.get());
            cartao.get().adicionaBiometria(biometria);
            cartaoRepository.save(cartao.get());
            biometria = cartao.get().pegaAUltimaBiometria();
            URI uri = builder.path("/{uuid}/biometria").buildAndExpand(biometria.getUuid()).toUri();
            return ResponseEntity.created(uri).build();
    }
}
